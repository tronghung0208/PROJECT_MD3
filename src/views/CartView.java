package views;

import static views.UserView.displayAdminMenu;
import static views.UserView.sc;

public class CartView {
    public void cartMenu(){
        do {
            System.out.println("**********GIỎ HÀNG CỦA BẠN**********");
            System.out.println("1. Thay đổi số lượng đặt hàng");
            System.out.println("2. Xóa sản phẩm trong giỏ hàng");
            System.out.println("3. Đặt hàng");
            System.out.println("4. Hiển thị danh sách trong giỏ hàng");
            System.out.println("5. Thoát khỏi màn hình quản lí sản phẩm");
            System.out.println("Lựa chọn của bạn");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:
                    displayAdminMenu();
                    break;
                default:

            }
        } while (true);
    }
}
