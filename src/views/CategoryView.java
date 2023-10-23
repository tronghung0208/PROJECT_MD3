package views;

import service.CategoryService;
import service.userFileService.CategoryFileService;

import static views.UserView.displayAdminMenu;
import static views.UserView.sc;

public class CategoryView {
    private static CategoryService categoryService;

    static {
        CategoryFileService categoryFileService = new CategoryFileService();
        categoryService = new CategoryService(categoryFileService);
    }

    public static void categoryManagement() {
        do {
            System.out.println("**********QUẢN LÍ DANH MỤC**********");
            System.out.println("1. Hiển thị danh sách danh mục");
            System.out.println("2. Thêm mới danh mục");
            System.out.println("3. Chỉnh sửa thông tin danh mục");
            System.out.println("4. Tìm kiếm danh mục theo tên");
            System.out.println("5. Ẩn danh mục theo mã danh mục");
            System.out.println("6. Thoát khỏi màn hình category");
            System.out.println("Lựa chọn của bạn");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    categoryService.showCategory();
                    break;
                case 2:
                    categoryService.addCategory();
                    break;
                case 3:
                    categoryService.updateCategory();
                    break;
                case 4:
                    categoryService.displayCategoryListByCatalogName();
                    break;
                case 5:
                    categoryService.changeCategoryStatus();
                    break;
                case 6:
                    displayAdminMenu();
                    break;
                default:
                    return;
            }
        } while (true);
    }


}
