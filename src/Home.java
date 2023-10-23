import service.UserService;
import service.userFileService.UserFileService;
import views.UserView;

public class Home {

    public static void main(String[] args) {

        UserFileService userFileService=new UserFileService();
        UserService userService=new UserService(userFileService);
        UserView userView =new UserView(userService);
        UserView.registerAndLogin();
    }
}
