package com.example.aqqhome.model;

public class roommodel {
    private String ApartmentID;
    private String PropertyManager;
    private String Address;
    private String ManagerPhoneNumber;
    private String NameApartment;
    private String RoomName;
    private String Area ;
    private String NumberOfFloors;

    private String Fund;
    private boolean success;
    private String message;

    public roommodel(String apartmentID, String propertyManager, String address, String managerPhoneNumber, String nameApartment, String roomName, String area, String numberOfFloors, String fund, boolean success, String message) {
        ApartmentID = apartmentID;
        PropertyManager = propertyManager;
        Address = address;
        ManagerPhoneNumber = managerPhoneNumber;
        NameApartment = nameApartment;
        RoomName = roomName;
        Area = area;
        NumberOfFloors = numberOfFloors;
        Fund = fund;
        this.success = success;
        this.message = message;
    }

    public String getApartmentID() {
        return ApartmentID;
    }

    public void setApartmentID(String apartmentID) {
        ApartmentID = apartmentID;
    }

    public String getPropertyManager() {
        return PropertyManager;
    }

    public void setPropertyManager(String propertyManager) {
        PropertyManager = propertyManager;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getManagerPhoneNumber() {
        return ManagerPhoneNumber;
    }

    public void setManagerPhoneNumber(String managerPhoneNumber) {
        ManagerPhoneNumber = managerPhoneNumber;
    }

    public String getNameApartment() {
        return NameApartment;
    }

    public void setNameApartment(String nameApartment) {
        NameApartment = nameApartment;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getNumberOfFloors() {
        return NumberOfFloors;
    }

    public void setNumberOfFloors(String numberOfFloors) {
        NumberOfFloors = numberOfFloors;
    }

    public String getFund() {
        return Fund;
    }

    public void setFund(String fund) {
        Fund = fund;
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
