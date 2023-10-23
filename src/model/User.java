package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static service.validation.UserRegisterValidate.*;


public class User implements Serializable {

    private static int nextId = 1;
    private int userId;
    private String userName;
    private String email;
    private String password;
    private String phone;
    private boolean gender;
    private String date;
    private boolean role;
    private boolean status;
    private List<OrdersDetail> cart;

    public User() {
    }

    public User(String userName, String password, String email, String phone, boolean gender, Date date, boolean role, boolean status, List<OrdersDetail> cart) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.date = String.valueOf(date);
        this.role = role;
        this.status = status;
        this.cart = cart;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = String.valueOf(date);
    }

    public boolean getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = true;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        User.nextId = nextId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public List<OrdersDetail> getCart() {
        return cart;
    }

    public void setCart(List<OrdersDetail> cart) {
        this.cart = cart;
    }

    public void inputData() {
        this.userName = validateUserName();
        this.email = validateEmail();
        this.password = validatePassword();
        this.phone = validatePhone();
        this.gender = validateGender();
        this.date = validateDate();
        this.role = validateRole();
        this.status = validateStatus();
        this.cart=new ArrayList<>();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("userId=").append(userId);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", gender=").append(gender);
        sb.append(", date='").append(date).append('\'');
        sb.append(", role=").append(role);
        sb.append(", status=").append(status);
        sb.append(", cart=").append(cart);
        sb.append('}');
        return sb.toString();
    }


}
