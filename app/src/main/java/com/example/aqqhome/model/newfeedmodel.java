package com.example.aqqhome.model;

public class newfeedmodel {
    private String NewID;
    private String Text;


    private String NewTime;
    private String Name;
    private String ApartmentID;
    private String RoomName;
    private String UserID;
    private boolean success;
    private String message;


    public newfeedmodel(String newID, String text, String newTime, String name, String apartmentID, String roomName, String userID, boolean success, String message) {
        NewID = newID;
        Text = text;
        NewTime = newTime;
        Name = name;
        ApartmentID = apartmentID;
        RoomName = roomName;
        UserID = userID;
        this.success = success;
        this.message = message;
    }

    public String getNewID() {
        return NewID;
    }

    public void setNewID(String newID) {
        NewID = newID;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getNewTime() {
        return NewTime;
    }

    public void setNewTime(String newTime) {
        NewTime = newTime;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getApartmentID() {
        return ApartmentID;
    }

    public void setApartmentID(String apartmentID) {
        ApartmentID = apartmentID;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

