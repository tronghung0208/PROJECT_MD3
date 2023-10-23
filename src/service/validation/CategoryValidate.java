package service.validation;

import constant.Constant;
import model.Category;
import service.userFileService.CategoryFileService;
import java.util.List;
import static views.UserView.sc;

public class CategoryValidate {
    public static String catalogNameValidate() {
        do {
            System.out.println("Nhập tên danh mục");
            String categoryName = sc.nextLine();
            if (!categoryName.isEmpty()) {
                if (!isCategoryNameExists(categoryName)) {
                    return categoryName;
                } else {
                    System.out.println("Tên danh mục đã được sử dụng, mời nhập lại");
                }
            } else {
                System.out.println("Tên không được để trống");
            }
        } while (true);

    }

    public static boolean isCategoryNameExists(String categoryName) {
        List<Category> categoryList = CategoryFileService.getAllCategory();
        for (Category category : categoryList) {
            if (category.getCatalogName().equals(categoryName)) {
                return true;
            }
        }
        return false;
    }

    public static String catalogDescriptionValidate() {
        do {
            System.out.println("Nhập mô tả về danh mục");
            String categoryDescription = sc.nextLine();
            if (!categoryDescription.isEmpty()) {
                    return categoryDescription;
            } else {
                System.out.println("Mô tả về danh mục không được để trống");
            }
        } while (true);

    }

    public static boolean validateCategoryStatus() {
        return Constant.Status.ACTIVE.getStatus();
    }
}
