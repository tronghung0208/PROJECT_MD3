package service.validation;

import constant.Constant;
import model.User;
import views.UserView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static service.userFileService.UserFileService.getAllUser;
import static views.UserView.sc;

public class UserRegisterValidate {

    // VALIDATE USER NAME
    public static String validateUserName() {
        String userNameInput;
        while (true) {
            System.out.println("Nhập vào tên tài khoản");
            userNameInput = sc.nextLine();
            if (!userNameInput.isEmpty()) {
                if (userNameInput.length() > 5) {
                    return userNameInput;
                } else {
                    System.out.println("\033[1;31mTên tài khoản phải lớn hơn 5 kí tự, xin mời nhập lại\u001B[0m");
                }
            } else {
                System.out.println("\033[1;31mTên không được để trống, xin mời nhập tên tài khoản\u001B[0m");
            }
        }
    }

    // VALIDATE EMAIL

    public static String validateEmail() {
        String emailInput;
        while (true) {
            System.out.println("Nhập vào email");
            emailInput = sc.nextLine();
            if (!emailInput.isEmpty()) {
                if (checkRegexEmail(emailInput)) {
                    if (!isEmailExists(emailInput)) {
                        return emailInput;
                    } else {
                        System.out.println("\033[1;31mEmail đã được sử dụng, xin mời nhập lại\u001B[0m");
                    }
                } else {
                    System.out.println("\033[1;31mEmail không đúng định dạng, xi mời nhập lại\u001B[0m");
                }

            } else {
                System.out.println("\033[1;31mEmail không được để trống\u001B[0m");
            }
        }

    }

    public static boolean checkRegexEmail(String email) {
        String regex = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*$";

        if (!email.matches(regex)) {
            return false;
        }

        return true;

    }

    public static boolean isEmailExists(String email) {
        List<User> userList=getAllUser();
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    // VALIDATE PASSWORD
    public static String validatePassword() {
        String passInput;
        while (true) {
            System.out.println("Nhập mật khẩu");
            passInput = sc.nextLine();
            if (!passInput.isEmpty()) {
                if (checkRegexPassword(passInput)) {
                    return passInput;
                } else {
                    System.out.println("\033[1;31mMật khẩu phải chứa ít nhất một chữ số [0-9].\n" +
                            "Mật khẩu phải chứa ít nhất một ký tự Latin viết thường [az].\n" +
                            "Mật khẩu phải chứa ít nhất một ký tự Latin viết hoa [AZ].\n" +
                            "Mật khẩu phải chứa ít nhất một ký tự đặc biệt như ! @ # & ( ).\n" +
                            "Mật khẩu phải có độ dài ít nhất 8 ký tự và tối đa 20 ký tự.\u001B[0m");
                }

            } else {
                System.out.println("\033[1;31mMật khẩu không được để trống\u001B[0m");
            }
        }
    }

    public static boolean checkRegexPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()*\\[\\]{}:;',?/*~$^+=<>]).{8,20}$";
        if (!password.matches(regex)) {
            return false;
        }
        return true;
    }


// VALIDATE PHONE

    public static String validatePhone() {
        String phoneInput;
        while (true) {
            System.out.println("Nhập số điện thoại");
            phoneInput = sc.nextLine();
            if (!phoneInput.isEmpty()) {
                if (checkRegexPhoneNumber(phoneInput)) {
                    if (!isPhoneExists(phoneInput)) {
                        return phoneInput;
                    }else {
                        System.out.println("\033[1;31mSố điện thoại đã được sử dụng\u001B[0m");
                    }
                } else {
                    System.out.println("\033[1;31mSĐT không đúng định dạng của số đt việt nam\u001B[0m");
                }

            } else {
                System.out.println("\033[1;31mSố điện thoại không được để trống\u001B[0m");
            }
        }
    }

    public static boolean checkRegexPhoneNumber(String password) {
        String regex = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";
        if (!password.matches(regex)) {
            return false;
        }
        return true;
    }

    public static boolean isPhoneExists(String phone) {
        List<User> userList=getAllUser();
        for (User user :userList) {
            if (user.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    // VALIDATE GENDER
    public static boolean validateGender() {
        do {
            System.out.println("1. Nam");
            System.out.println("2. Nữ");
            System.out.println("3. giới tính khác");
            System.out.println("Mời chọn giới tính");
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        return true;
                    case 2:
                        return false;
                    default:
                        System.out.println("\033[1;31mLựa chọn không hợp lệ; mời chọn lại giới tính\u001B[0m");
                }
            } catch (NumberFormatException e) {
                System.out.println("\033[1;31mLựa chọn không hợp lệ; mời chọn lại giới tính bằng cách nhập số.\u001B[0m");
            }
        } while (true);
    }

    // VALIDATE DATE

    public static String validateDate() {

        Date birthDay;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setLenient(false);
        while (true) {
            System.out.println("Nhập vào ngày tháng năm sinh");
            String inputDate = sc.nextLine();
            try {
                birthDay = simpleDateFormat.parse(inputDate);

                return simpleDateFormat.format(birthDay);
            } catch (ParseException e) {
                System.out.println("\033[1;31mNgày tháng năm không hợp lệ. Hãy nhập lại theo định dạng dd/MM/yyyy.\u001B[0m");
            }
        }
    }

    public static boolean validateRole() {
        do {
            System.out.println("1. USER");
            System.out.println("2. ADMIN");
            System.out.println("Mời chọn quyền sủa dụng tài khoản");
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        return true;
                    case 2:
                        return false;
                    default:
                        System.out.println("\033[1;31mLựa chọn không hợp lệ; mời chọn lại quyền sủa dụng\u001B[0m");
                }
            } catch (NumberFormatException e) {
                System.out.println("\033[1;31mLựa chọn không hợp lệ; mời chọn lại quyền sử dụng bằng cách nhập số\u001B[0m");
            }
        } while (true);
    }
    public static boolean validateStatus() {
        return Constant.Status.ACTIVE.getStatus();
    }

}
