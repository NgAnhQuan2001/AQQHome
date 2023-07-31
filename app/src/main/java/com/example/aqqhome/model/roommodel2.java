package com.example.aqqhome.model;

public class roommodel2 {
    private String Code;
    private String RoomName;
    private String RoomID;
    private String NumberOfFloors;
    private String Area;
    private String ParkingCardNumber;
    private String ApartmentID;
    private String Debt;
    private boolean success;
    private String message;

    private String amount;

    public roommodel2(String code, String roomName, String roomID, String numberOfFloors, String area, String parkingCardNumber, String apartmentID, String debt, boolean success, String message, String amount) {
        Code = code;
        RoomName = roomName;
        RoomID = roomID;
        NumberOfFloors = numberOfFloors;
        Area = area;
        ParkingCardNumber = parkingCardNumber;
        ApartmentID = apartmentID;
        Debt = debt;
        this.success = success;
        this.message = message;
        this.amount = amount;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String roomID) {
        RoomID = roomID;
    }

    public String getNumberOfFloors() {
        return NumberOfFloors;
    }

    public void setNumberOfFloors(String numberOfFloors) {
        NumberOfFloors = numberOfFloors;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getParkingCardNumber() {
        return ParkingCardNumber;
    }

    public void setParkingCardNumber(String parkingCardNumber) {
        ParkingCardNumber = parkingCardNumber;
    }

    public String getApartmentID() {
        return ApartmentID;
    }

    public void setApartmentID(String apartmentID) {
        ApartmentID = apartmentID;
    }

    public String getDebt() {
        return Debt;
    }

    public void setDebt(String debt) {
        Debt = debt;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

