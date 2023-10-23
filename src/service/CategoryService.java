package service;

import constant.Constant;
import model.Category;
import service.userFileService.CategoryFileService;

import java.util.ArrayList;
import java.util.List;

import static service.userFileService.CategoryFileService.getAllCategory;
import static service.userFileService.CategoryFileService.getCategory;
import static views.UserView.sc;

public class CategoryService {
    private final CategoryFileService categoryFileService;
    public static List<Category> categoryList;
    public CategoryService(CategoryFileService categoryFileService) {
        this.categoryFileService = categoryFileService;
    }

    // THÊM MỚI DANH MỤC

    public void addCategory() {
        categoryList = getAllCategory();
        Category category = new Category();
        category.setCatalogId(idCategoryImcrement());
        category.inputCategory();
        System.out.println("Đăng kí thành công, vui lòng đăng nhập");
        categoryList.add(category);
        categoryFileService.saveToFileCategory(categoryList);
        if (!categoryList.isEmpty()) {
            for (Category category1 : categoryList) {
                category1.toString();
            }
        }
    }

    static long idCategoryImcrement() {
        int max = 0;
        if (categoryList.isEmpty()) {
            return 1;
        } else {
            for (Category category : categoryList) {
                if (category.getCatalogId() > max) {
                    max = (int) category.getCatalogId();
                }
            }
            return max + 1;
        }
    }

    // Hiển thị tất cả danh mục
    public static void showCategory() {
        categoryList = getAllCategory();

        for (Category category : categoryList
        ) {
            System.out.println(category);
        }

    }


    public void updateCategory() {
        categoryList = getAllCategory();
        Category category = getCategory(categoryList);
        if (category != null) {
            category.inputCategory();
            categoryFileService.saveToFileCategory(categoryList);
        } else {
            System.out.println("Danh mục bạn muốn chỉnh sửa không có trong danh sách");
        }
    }


    public List<Category> getCategoryListByCatalogName(String catalogName) {
        categoryList = getAllCategory();
        List<Category> filteredCategory = new ArrayList<>();
        for (Category category : categoryList) {
            if (category.getCatalogName().toLowerCase().contains(catalogName.toLowerCase())) {
                filteredCategory.add(category);
            }
        }
        return filteredCategory;
    }


    public void displayCategoryListByCatalogName() {
        System.out.println("Nhập từ khoá tên bạn muốn tìm kiếm:");
        String catalogName = sc.nextLine();
        List<Category> filteredCategory =getCategoryListByCatalogName(catalogName) ;
        for (Category category : filteredCategory) {
            System.out.println(category);
        }
    }


    public static void setStatusByCatalogName(boolean status, long catalogId) {
        categoryList = getAllCategory();
        for (Category category : categoryList) {
            if (category.getCatalogId()==catalogId) {
                category.setStatus(status);
                break;
            }
        }
        CategoryFileService.saveToFileCategory(categoryList);
    }


    public void changeCategoryStatus() {
        System.out.println("Hãy nhập mã danh mục bạn muốn thay đổi trạng thái");
        long categoryId = Long.parseLong(sc.nextLine());
        while (true) {
            System.out.println("Hãy nhập trạng thái bạn muốn thay đổi cho danh mục (1: Ẩn, 2: Hiện)");
            try {
                int statusInt = Integer.parseInt(sc.nextLine());

                if (statusInt == 1 || statusInt == 2) {
                    setStatusByCatalogName(statusInt == 1 ? Constant.Status.ACTIVE.getStatus() : Constant.Status.BLOCK.getStatus(), categoryId);
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

}
