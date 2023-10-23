package service;

import model.OrdersDetail;
import model.Product;
import model.User;
import service.userFileService.IsUserLoginFile;
import service.userFileService.ProductFileService;
import service.userFileService.UserFileService;

import java.util.List;

import static service.UserService.productList;
import static service.userFileService.ProductFileService.getAllProduct;
import static service.userFileService.UserFileService.getAllUser;
import static service.userFileService.UserFileService.saveToFileUser;
import static views.UserView.sc;

public class CartService {

    private UserFileService ProductFileService;
    private UserService userService;
    Product product = new Product();

    public void addCart() {
        UserFileService UserFileService = null;
        userService = new UserService(UserFileService);
        userService.showProductToUsser();
        User user = IsUserLoginFile.getUserIsLogin();
        List<OrdersDetail> carts = user.getCart();
        System.out.println("Nhập mã sản phẩm bạn muốn thêm vào giỏ");
        long productId;
        try {
            productId = Long.parseLong(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Sai định dạng mã sản phẩm. Vui lòng nhập lại.");
            return;
        }
        Product product = null;

        // Tìm sản phẩm theo mã sản phẩm
        for (Product p : productList) {
            if (p.getProductId() == productId && p.isStatus()) {
                product = p;
                break;
            }
        }
        if (product != null) {
            int quantityOder = -1;
            while (quantityOder <= 0) {
                try {
                    System.out.println("Nhập số lượng sản phẩm cần mua");
                    quantityOder = Integer.parseInt(sc.nextLine());
                    if (quantityOder <= 0) {
                        System.out.println("Số lượng phải lớn hơn 0. Vui lòng nhập lại.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Sai định dạng số lượng. Vui lòng nhập lại.");
                }
            }
            // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
            boolean isProductInCart = false;
            for (OrdersDetail orderDetail : carts) {
                if (orderDetail.getProductId() == productId) {
                    // Tăng số lượng lên quantityOder
                    orderDetail.setQuantity(orderDetail.getQuantity() + quantityOder);
                    isProductInCart = true;
                    break;
                }
            }
            if (!isProductInCart) {
                // Tạo một đối tượng OrdersDetail và thêm vào giỏ hàng
                OrdersDetail orderDetail = new OrdersDetail(product.getProductId(), user.getUserId(), product.getProductName(), product.getUnitPrice(), quantityOder);
                carts.add(orderDetail);
            }
            user.setCart(carts);
            UserFileService userFileService = new UserFileService();
            saveCartToUser(user);
            for (OrdersDetail o:carts
                 ) {
                System.out.println(o.toString());
            }

            System.out.println("Sản phẩm đã được thêm vào giỏ hàng.");
        } else {
            System.out.println("Sản phẩm không có trong danh sách hoặc đã bị vô hiệu hóa.");
        }


    }

    private Product product(long id) {
        List<Product> productList = getAllProduct();
        for (Product product : productList
        ) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return null;
    }

    public void saveCartToUser(User user) {
        List<User> allUsers = getAllUser();
        for (User u : allUsers) {
            if (u.getUserId() == user.getUserId()) {
                // Cập nhật giỏ hàng của người dùng
                u.setCart(user.getCart());
                saveToFileUser(allUsers);
                break;
            }
        }
    }


}
