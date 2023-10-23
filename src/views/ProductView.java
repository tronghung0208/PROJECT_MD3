package views;

import service.ProductService;
import service.userFileService.ProductFileService;

import static views.UserView.displayAdminMenu;
import static views.UserView.sc;

public class ProductView {
    private static ProductService productService = new ProductService();

    static {
        ProductFileService productFileService = new ProductFileService();
        productFileService = new ProductFileService();
    }

    public static void productManagement() {
        do {
            System.out.println("**********QUẢN LÍ SẢN PHẨM**********");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Chỉnh sửa thông tin sản phẩm");
            System.out.println("4. Tìm kiếm sản phẩm theo tên");
            System.out.println("5. Ẩn sản phẩm theo mã sản phẩm");
            System.out.println("6. Ẩn nhiều sản phẩm theo danh mục");
            System.out.println("7. Thoát khỏi màn hình quản lí sản phẩm");
            System.out.println("Lựa chọn của bạn");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    productService.showProduct();
                    break;
                case 2:
                    productService.addProduct();
                    break;
                case 3:
                    productService.updateProduct();
                    break;
                case 4:
                    productService.displayProductListByCatalogName();
                    break;
                case 5:
                    productService.changeProductStatus();
                    break;
                case 6:
                    productService.changeProductsStatus();
                    break;
                case 7:
                    displayAdminMenu();
                    break;
                default:

            }
        } while (true);
    }


}
