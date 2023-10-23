package service.validation;

import constant.Constant;
import model.Product;
import service.userFileService.CategoryFileService;
import service.userFileService.ProductFileService;
import java.util.ArrayList;
import java.util.List;

import static service.ProductService.displayCategory;
import static views.UserView.sc;

public class ProductValidate {
    private static final CategoryFileService categoryFileService = new CategoryFileService();
    private static final ProductFileService productFileService = new ProductFileService();
    private static List<Product> productList = new ArrayList<>();

    // lấy Mã danh mục từ danh sách danh mục
    public static long getCategoryIdToCategory() {
        do {
            displayCategory();
            System.out.println("Nhập vào mã danh mục");
            long inputCategoryId = Long.parseLong(sc.nextLine());
            Long categoryId = CategoryFileService.getIdByCategoryId(inputCategoryId);
            if (categoryId != null) {
                return categoryId;
            } else {
                System.out.println("Mã danh mục không có trong danh sách");
            }
        } while (true);
    }




    // VALIDATE PRODUCT NAME
    public static String productNameValidate() {
        do {
            System.out.println("Nhập tên sản phẩm");
            String productName = sc.nextLine();
            if (!productName.isEmpty()) {
                if (!isProductNameExists(productName)) {
                    return productName;
                } else {
                    System.out.println("Tên sản đã có trong danh sách");
                }
            } else {
                System.out.println("Tên sản phẩm không được để trống");
            }
        } while (true);

    }

    public static boolean isProductNameExists(String productName) {
        productList = ProductFileService.getAllProduct();
        for (Product product : productList) {
            if (product.getProductName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    // MÔ TẢ SẢN PHẨM
    public static String productDescriptionValidate() {
        do {
            System.out.println("Nhập mô tả về sản phẩm");
            String productDescription = sc.nextLine();
            if (!productDescription.isEmpty()) {
                return productDescription;
            } else {
                System.out.println("Mô tả về sản phẩm không được để trống");
            }
        } while (true);

    }

    public static double initPriceValidate() {
        do {
            System.out.println("Nhập vào đơn giá của sản phẩm");
            double price = Double.parseDouble(sc.nextLine());
            if (price > 0) {
                return price;
            } else {
                System.out.println("Đơn giá của sản phẩm phải lớn hơn 0");
            }
        } while (true);
    }

    // SỐ LƯỢNG SẢN PHẨM TRONG KHO
    public static int stockValidate() {
        do {
            try {
                System.out.println("Nhập vào số lượng sản phẩm");
                int stockInput = Integer.parseInt(sc.nextLine());
                if (stockInput > 0) {
                    return stockInput;
                } else {
                    System.out.println("Số lượng sản phẩm phải lớn hơn 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Hãy nhập một số nguyên hợp lệ.");
                // Đọc và loại bỏ dòng nhập không hợp lệ
                sc.nextLine();
            }
        } while (true);
    }

    public static boolean validateProductStatus() {
        return Constant.Status.ACTIVE.getStatus();
    }


}