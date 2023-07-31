package com.example.aqqhome.model;

public class usermodel2 {
    private String UserID;
    private String PhoneNumber;
    private String Email;
    private String FullName;
    private String Money;
    private String message;
    private boolean success;
    private String uid;


    private String Type;
    private String Manager;

    public usermodel2(String userID, String phoneNumber, String email, String fullName, String money, String message, boolean success, String uid, String type, String manager) {
        UserID = userID;
        PhoneNumber = phoneNumber;
        Email = email;
        FullName = fullName;
        Money = money;
        this.message = message;
        this.success = success;
        this.uid = uid;
        Type = type;
        Manager = manager;
    }

    public String getManager() {
        return Manager;
    }

    public void setManager(String manager) {
        Manager = manager;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        Money = money;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
