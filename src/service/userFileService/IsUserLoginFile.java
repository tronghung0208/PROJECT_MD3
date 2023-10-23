package service.userFileService;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IsUserLoginFile {
    public static File userIsLoginFile;

    public IsUserLoginFile() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        userIsLoginFile = new File("data/user_is_login.dat");
        try {
            if (!userIsLoginFile.exists()) {
                userIsLoginFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi khởi tạo file");
        }
    }

    public static User getUserIsLogin() {
        User user = new User();
        try {
            FileInputStream fileInputStream = new FileInputStream(userIsLoginFile);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            user = (User) inputStream.readObject();
        } catch (EOFException e) {
        } catch (IOException e) {
            System.out.println("Không có dữ liệu trong file");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static void saveToFileUserIsLogin(User user) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(userIsLoginFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(user);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Lỗi ghi file !");
        }
    }

    public static void deleteDataInFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(userIsLoginFile, false);
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("Lỗi khi xóa dữ liệu khỏi tệp!");
        }
    }
}
