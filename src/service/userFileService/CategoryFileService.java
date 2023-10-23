package service.userFileService;

import model.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static views.UserView.sc;

public class CategoryFileService {
    public static File categoryFile;

    public CategoryFileService() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        categoryFile = new File("data/category.dat");
        try {
            if (!categoryFile.exists()) {
                categoryFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi khởi tạo file");
        }
    }

    public static void saveToFileCategory(List<Category> categories) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(categoryFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(categories);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Có lỗi xảy ra khi thêm mới user");
        }
    }

    public static List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(categoryFile);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            categories = (List<Category>) inputStream.readObject();
            inputStream.close();
        } catch (EOFException e) {
            // EOFException thường xuất hiện khi file rỗng, có thể xử lý tùy theo nhu cầu.
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file");
        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi khi đọc đối tượng từ file");
        }

        return categories;
    }

    public static Category getCategory(List<Category> categoryList) {
        try {
            System.out.println("Nhập vào mã danh mục cần chỉnh sửa");
            long categoryId = Long.parseLong(sc.nextLine());
            for (Category category : categoryList
            ) {
                if (category != null && category.getCatalogId() == categoryId) {
                    return category;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Nhập lỗi");
        }
        return null;
    }

public static Long getIdByCategoryId(long id){
        List<Category> categoryList=getAllCategory();
    for (Category category:categoryList
         ) {
        if (category.getCatalogId()==id){
            return category.getCatalogId();
        }
    }
    return null;
}

}