package service.userFileService;
import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static views.UserView.sc;

public class ProductFileService {
    public static File productFile = new File("data/product.dat");

    public ProductFileService() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        productFile = new File("data/product.dat");
        try {
            if (!productFile.exists()) {
                productFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi khởi tạo file");
        }
    }
    public static void saveToFileProduct(List<Product> products) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(productFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(products);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Có lỗi xảy ra khi thêm mới user");
        }
    }

    public static List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(productFile);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            products = (List<Product>) inputStream.readObject();
            inputStream.close();
        } catch (EOFException e) {
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file");
        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi khi đọc đối tượng từ file");
        }
        return products;
    }

    public static Product getProduct(List<Product> productList) {
        try {
            System.out.println("Nhập vào mã sản phẩm cần chỉnh sửa");
            long productId = Long.parseLong(sc.nextLine());
            for (Product product : productList
            ) {
                if (product != null && product.getProductId() == productId) {
                    return product;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Nhập lỗi");
        }
        return null;
    }
}
