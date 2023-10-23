package service;

import constant.Constant;
import model.Category;
import model.Product;
import service.userFileService.ProductFileService;
import java.util.ArrayList;
import java.util.List;
import static service.userFileService.CategoryFileService.getAllCategory;
import static service.userFileService.ProductFileService.*;
import static views.UserView.sc;

public class ProductService {
    private ProductFileService productFileService = new ProductFileService();
    private static List<Product> productList = new ArrayList<>();

    public ProductService() {
        this.productFileService = productFileService;
    }

    public void addProduct() {
        productList = getAllProduct();
        Product product = new Product();
        product.setProductId(idProductImcrement());
        product.inputProductData();
        System.out.println("Thêm mới sản phẩm thành công");
        productList.add(product);
        productFileService.saveToFileProduct(productList);
        if (!productList.isEmpty()) {
            for (Product product1 : productList) {
                System.out.println(product1.toString());
            }
        }
    }

    static long idProductImcrement() {
        int max = 0;
        if (productList.isEmpty()) {
            return 1;
        } else {
            for (Product product : productList) {
                if (product.getProductId() > max) {
                    max = (int) product.getProductId();
                }
            }
            return max + 1;
        }
    }

    public void showProduct() {
        productList = getAllProduct();
        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }

    //Hiển thị danh sách danh mục để lựa chọn

    public static void displayCategory() {
        List<Category> categoryList = getAllCategory();
        for (Category category : categoryList) {
            System.out.println("\u001B[33mMã danh mục: \u001B[0m" + category.getCatalogId() + " \u001B[33mTên danh mục: \u001B[0m" + category.getCatalogName());
        }
    }

    // CHỈNH SỬA THÔNG TIN SẢN PHẨM
    public void updateProduct() {
        productList = getAllProduct();
        Product product = getProduct(productList);
        if (product != null) {
            product.inputProductData();
            productFileService.saveToFileProduct(productList);
        } else {
            System.out.println("sản phẩm bạn muốn chỉnh sửa không có trong danh sách");
        }
    }

    // LẤY RA LIST SẢN PHẨM TÌM KIẾM THEO TỪ KHÓA
    public List<Product> getCategoryListByProductName(String productName) {
        productList = getAllProduct();
        List<Product> filteredProduct = new ArrayList<>();
        for (Product product : productList) {
            if (product.getProductName().toLowerCase().contains(productName.toLowerCase())) {
                filteredProduct.add(product);
            }
        }
        return filteredProduct;
    }

    public void displayProductListByCatalogName() {
        System.out.println("Nhập từ khoá tên bạn muốn tìm kiếm:");
        String productName = sc.nextLine();
        List<Product> filteredProduct =getCategoryListByProductName(productName) ;
        for (Product product : filteredProduct) {
            System.out.println(product.toString());
        }
    }

//    THAY ĐỔI TRẠNG THÁI CỦA SẢN PHẨM

    public static void setStatusByProductid(boolean status, long productId) {
        productList = getAllProduct();
        for (Product product : productList) {
            if (product.getProductId()==productId) {
                product.setStatus(status);
                break;
            }
        }
       ProductFileService.saveToFileProduct(productList);
    }
    public void changeProductStatus() {
        System.out.println("Hãy nhập mã sản phẩm bạn muốn thay đổi trạng thái");
        long productId = Long.parseLong(sc.nextLine());
        while (true) {
            System.out.println("Hãy nhập trạng thái bạn muốn thay đổi cho sản phẩm (1: Ẩn, 2: Hiện)");
            try {
                int statusInt = Integer.parseInt(sc.nextLine());
                if (statusInt == 1 || statusInt == 2) {
                    setStatusByProductid(statusInt == 1 ? Constant.Status.BLOCK.getStatus() : Constant.Status.ACTIVE.getStatus(), productId);
                    System.out.println("\u001B[32mThay đổi trạng thái thành công!\u001B[0m");
                    break;
                } else {
                    System.out.println("\u001B[31mLựa chọn không hợp lệ. Hãy chọn 1 hoặc 2.\u001B[0m");
                }
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mLựa chọn không hợp lệ. Hãy chọn 1 hoặc 2.\u001B[0m");
            }
        }
    }

    // Thay đổi trạng thái nhiều sản phẩm theo danh mục
    public static void setStatusByCategoryId(boolean status, long categoryId) {
        productList = getAllProduct();
        for (Product product : productList) {
            if (product.getCategoryId()==categoryId) {
                product.setStatus(status);
            }
        }
        ProductFileService.saveToFileProduct(productList);
    }
    public void changeProductsStatus() {
        System.out.println("Hãy nhập mã sản phẩm bạn muốn thay đổi trạng thái");
        long categoryId = Long.parseLong(sc.nextLine());
        while (true) {
            System.out.println("Hãy nhập trạng thái bạn muốn thay đổi cho sản phẩm (1: Ẩn, 2: Hiện)");
            try {
                int statusInt = Integer.parseInt(sc.nextLine());
                if (statusInt == 1 || statusInt == 2) {
                    setStatusByCategoryId(statusInt == 1 ? Constant.Status.BLOCK.getStatus() : Constant.Status.ACTIVE.getStatus(), categoryId);
                    System.out.println("\u001B[32mThay đổi trạng thái thành công!\u001B[0m");
                    break;
                } else {
                    System.out.println("\u001B[31mLựa chọn không hợp lệ. Hãy chọn 1 hoặc 2.\u001B[0m");
                }
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mLựa chọn không hợp lệ. Hãy chọn 1 hoặc 2.\u001B[0m");
            }
        }
    }
}
