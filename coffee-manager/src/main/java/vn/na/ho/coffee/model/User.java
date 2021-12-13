package vn.na.ho.coffee.model;


import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {
    private int id;
    private String fullName;
    private String username;
    private String birthDay;
    private String phoneNumber;
    private String address;
    private UserStatus status;
    private Role role;
    private String password;


    public User(int id, String username, String fullName, String birthDay, String phoneNumber, String address) {
        this.id = id;
        this.fullName = fullName;
        this.username=username;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public User(int id, String username, String fullName, UserStatus status, Role role, String birthDay, String phoneNumber, String address) {
        this.id = id;
        this.fullName = fullName;
        this.username=username;
        this.status = status;
        this.role = role;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.address = address;

    }

    public User(String username, String fullName, UserStatus status, Role role, String birthDay, String phoneNumber, String address) {
        this.fullName = fullName;
        this.username=username;
        this.status = status;
        this.role = role;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.address = address;

    }

    public User(String fullName, String username, String birthDay, String phoneNumber, String address) {
        this.fullName = fullName;
        this.username = username;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public User(String fullName, String username, String birthDay, String phoneNumber, String address, String password) {
        this.fullName = fullName;
        this.username = username;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
    }

    public User(int id, String fullName, String username, UserStatus status, String birthDay, String phoneNumber, String address) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.status=status;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%d,%s,%s,%s",
                id, fullName,
                status.getValue(), role.getValue(),
                phoneNumber, address);
    }

    public static void transferFields(User oldUser, User newUser) {
        oldUser.id = newUser.id;
        oldUser.status = newUser.status;
        oldUser.fullName = newUser.fullName;
        oldUser.birthDay = newUser.birthDay;
        oldUser.phoneNumber = newUser.phoneNumber;
        oldUser.address = newUser.address;

    }


}
