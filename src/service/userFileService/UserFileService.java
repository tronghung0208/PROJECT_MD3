package service.userFileService;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserFileService {

    public static File userFile= new File("data/user.dat");;


    public UserFileService() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        try {
            if (!userFile.exists()) {
                userFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi khởi tạo file");
        }
    }

    // Ghi dữ liệu vào file
    public static void saveToFileUser(List<User> userList) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(userFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(userList);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Lỗi ghi file !");
        }
    }
    public static List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(userFile);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            list = (List<User>) inputStream.readObject();
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public User getUserByEmail(String email) {
        List<User> allUser = getAllUser();
        for (User user : allUser) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }


}
