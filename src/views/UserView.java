package views;

import constant.Constant;
import model.User;
import service.CartService;
import service.UserService;
import service.userFileService.IsUserLoginFile;
import service.userFileService.UserFileService;
import java.util.List;
import java.util.Scanner;
import static service.UserService.*;
import static service.userFileService.UserFileService.getAllUser;
import static views.CategoryView.categoryManagement;
import static views.ProductView.productManagement;

public class UserView {

    static {
        UserFileService userFileService = new UserFileService();
        userFileService = new UserFileService();
        IsUserLoginFile userLoginFile=new IsUserLoginFile();
        userLoginFile=new IsUserLoginFile();
    }

    private static UserService userService;
    private  static CartService cartService=new CartService();

    public UserView(UserService userService) {
        this.userService = userService;
    }

    public static Scanner sc = new Scanner(System.in);

    public static void registerAndLogin() {
        do {
            try {
                System.out.println("1. Đăng nhập");
                System.out.println("2. Đăng kí");
                System.out.println("Nhập lựa chọn");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn phải là một số nguyên. Vui lòng chọn lại.");
                sc.nextLine(); // Đọc và loại bỏ dòng nhập không hợp lệ.
            }
        } while (true);
    }

    public static void displayAdminMenu() {
        boolean isLogin = true;
        do {
            System.out.println("**********ADMIN MENU**********");
            System.out.println("1. Quản lý người dùng");
            System.out.println("2. Quản lý danh mục");
            System.out.println("3. Quản lý sản phẩm");
            System.out.println("4. Quản lý hoá đơn");
            System.out.println("5. Đăng xuất");
            System.out.println("********************");
            System.out.println("Hãy đưa ra lựa chọn của bạn:");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    userManagement();
                    break;
                case 2:
                    categoryManagement();
                    break;
                case 3:
                    productManagement();
                    break;
                case 4:
                    // TODO
                    break;
                case 5:
                    IsUserLoginFile.deleteDataInFile();
                    registerAndLogin();
                    break;
                default:
                    isLogin = false;

            }
        } while (isLogin);
        registerAndLogin();
    }

    private static void userManagement() {
        do {
            System.out.println("**********QUẢN LÍ NGƯỜI DÙNG**********");
            System.out.println("1. Hiển thị danh sách người dùng");
            System.out.println("2. Tìm kiếm người dùng theo tên");
            System.out.println("3. Khoá/mở khoá người dùng");
            System.out.println("4. Thoát màn hình quản lý user");
            System.out.println("Lựa chọn của bạn");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    displayUserList();
                    break;
                case 2:
                    displayUserListByUsername();
                    break;
                case 3:
                    changeUserStatus();
                case 4:
                    displayAdminMenu();
                default:
                    return;
            }
        } while (true);
    }


    // Hiển thị danh sách người dùng
    private static void displayUserList() {
        List<User> listUser = getAllUser();
        System.out.println("Danh sách người dùng sắp xếp theo tên:");
        for (User user : listUser) {
            System.out.println(user.toString());
        }
    }

    //    tìm kiếm người dùng theo tên
    private static void displayUserListByUsername() {
        System.out.println("Nhập từ khoá tên bạn muốn tìm kiếm:");
        String username = sc.nextLine();
        List<User> filteredUsers = getUserListByUsername(username);
        for (User user : filteredUsers) {
            System.out.println(user);
        }
    }


    // thay đổi trạng thái của user
    private static void changeUserStatus() {
        System.out.println("Hãy nhập username bạn muốn thay đổi trạng thái");
        String email = sc.nextLine();
        while (true) {
            System.out.println("Hãy nhập trạng thái bạn muốn set cho user (1: ACTIVE, 2: BLOCK)");
            try {
                int statusInt = Integer.parseInt(sc.nextLine());

                if (statusInt == 1 || statusInt == 2) {
                    setStatusByEmail(statusInt == 1 ? Constant.Status.ACTIVE.getStatus() : Constant.Status.BLOCK.getStatus(), email);
                    System.out.println("Thay đổi trạng thái thành công!");
                    break;
                } else {
                    System.out.println("\033[0;31mLựa chọn không hợp lệ. Hãy chọn 1 hoặc 2.\u001B[0m");
                }
            } catch (NumberFormatException e) {
                System.out.println("\033[0;31mLựa chọn không hợp lệ. Hãy chọn 1 hoặc 2.\u001B[0m");
            }
        }
    }


    public static void homeUserMenu() {
        do {
            System.out.println("**********User Menu**********");
            System.out.println("1. Tìm kiếm sản phẩm");
            System.out.println("2. Hiển thị sản phẩm nổi bật");
            System.out.println("3. Danh sách sản phẩm");
            System.out.println("4. Thêm vào giỏ hàng");
            System.out.println("5. Hiển thị giỏ hàng");
            System.out.println("6. Đăng xuất");
            System.out.println("Hãy ra lựa chọn của bạn");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    userService.displayProductListByCatalogName();
                    break;
                case 2:
                    userService.showProductoutstanding();
                    break;
                case 3:
                    userService.showProductToUsser();
                case 4:
                    cartService.addCart();
                    break;
                case 5:

                    break;
                default:
                    return;
            }
        } while (true);
    }



}
