package service;

import constant.Constant;
import model.Product;
import model.User;
import service.userFileService.IsUserLoginFile;
import service.userFileService.UserFileService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static service.userFileService.ProductFileService.getAllProduct;
import static service.userFileService.UserFileService.*;
import static views.UserView.*;

public class UserService {
    private static UserFileService userFileService;
    public static List<User> userList;
    public static List<Product> productList;

    public UserService(UserFileService userFileService) {
        this.userFileService = userFileService;
    }


    // ------- ĐĂNG KÍ USER--------
    public static void register() {
        userList = getAllUser();
        User newUser = new User();
        newUser.setUserId(idImcrement());
        newUser.inputData();
        System.out.println("Đăng kí thành công, vui lòng đăng nhập");
        userList.add(newUser);
        saveToFileUser(userList);
        for (User user1 : userList) {
            System.out.println(user1.toString());
            ;
        }
    }


    //-----------ID TỰ TĂNG------------
    static int idImcrement() {
        int max = 0;
        if (userList.isEmpty()) {
            return 1;
        } else {
            for (User user : userList) {
                if (user.getUserId() > max) {
                    max = user.getUserId();
                }
            }
            return max + 1;
        }
    }


    //-------------LẤY RA USER ĐỂ ĐĂNG NHẬP------------
    public static User userLogin(String email, String password) {
        userFileService=new UserFileService();
        User user= new User();
         user = userFileService.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    //-------------ĐĂNG NHẬP----------------
    public static void login() {
        System.out.println("Hãy thực hiện đăng nhập");
        System.out.println("Email:");
        String email = sc.nextLine();
        System.out.println("Password:");
        String password = sc.nextLine();
        User user = userLogin(email, password);
        if (user != null) {
            IsUserLoginFile.saveToFileUserIsLogin(user);
            if (user.getStatus()) {
                if (!user.getRole()) {
                    displayAdminMenu();
                } else {
                    homeUserMenu();
                }
            } else {
                System.out.println("\033[0;31mTài khoản của bạn đã bị khóa\u001B[0m");
            }
        } else {
            System.out.println("\033[0;31mĐăng nhập thất bại\u001B[0m");
            registerAndLogin();
        }
    }


    // Tìm kiến User theo từ khóa nhập vào
    public static List<User> getUserListByUsername(String username) {
        List<User> allUsers = getAllUser();
        List<User> filteredUsers = new ArrayList<>();
        for (User user : allUsers) {
            if (user.getUserName().toLowerCase().contains(username.toLowerCase())) {
                filteredUsers.add(user);
            }
        }

        return filteredUsers;
    }

    public static List<User> getSortedUserList() {
        List<User> sortedUsers = userFileService.getAllUser();
        Collections.sort(sortedUsers, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getUserName().compareTo(o2.getUserName());
            }
        });

        return sortedUsers;
    }

    public static void setStatusByEmail(boolean status, String email) {
        List<User> allUser = getAllUser();
        for (User user : allUser) {
            if (user.getEmail().equals(email)) {
                user.setStatus(status);
                break;
            }
        }
        saveToFileUser(allUser);
    }


//    Phần dành cho USER thông thường

// DANH SÁCH SẢN PHẨM

    public void showProductToUsser() {
        productList = getAllProduct();
        if (productList == null) {
            System.out.println("Không thể đọc dữ liệu sản phẩm.");
            return;
        }
        for (Product product : productList) {
            if (product.isStatus() == true)
                System.out.println(product.toString1());
        }
    }

    // HIỂN THỊ SẢN PHẨM NỔI BẬT
    public void showProductoutstanding() {
        productList = getAllProduct();
        for (Product product : productList
        ) {
            if (product.getCategoryId() == 1 && product.isStatus() == true)
                System.out.println(product.toString1());
        }
    }

    //TÌM KIẾM SẢN PHẨM

    public List<Product> searchListByProductName(String productName) {
        productList = getAllProduct();
        List<Product> searchProduct = new ArrayList<>();
        for (Product product : productList) {
            if (product.getProductName().toLowerCase().contains(productName.toLowerCase()) && product.isStatus() == true) {
                searchProduct.add(product);
            }
        }
        return searchProduct;
    }

    public void displayProductListByCatalogName() {
        System.out.println("Nhập từ khoá tên bạn muốn tìm kiếm:");
        String productName = sc.nextLine();
        List<Product> searchProduct = searchListByProductName(productName);
        for (Product product : searchProduct) {
            System.out.println(product.toString1());
        }
    }
}
