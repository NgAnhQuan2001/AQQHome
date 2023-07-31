package com.example.aqqhome.model;

public class feemodel {
    private String ApartmentID;
    private String ManagementFee;
    private String GarbageFee;
    private String ParkingCardFee;

    private boolean success;
    private String message;

    public feemodel(String apartmentID, String managementFee, String garbageFee, String parkingCardFee, boolean success, String message) {
        ApartmentID = apartmentID;
        ManagementFee = managementFee;
        GarbageFee = garbageFee;
        ParkingCardFee = parkingCardFee;
        this.success = success;
        this.message = message;
    }

    public String getApartmentID() {
        return ApartmentID;
    }

    public void setApartmentID(String apartmentID) {
        ApartmentID = apartmentID;
    }

    public String getManagementFee() {
        return ManagementFee;
    }

    public void setManagementFee(String managementFee) {
        ManagementFee = managementFee;
    }

    public String getGarbageFee() {
        return GarbageFee;
    }

    public void setGarbageFee(String garbageFee) {
        GarbageFee = garbageFee;
    }

    public String getParkingCardFee() {
        return ParkingCardFee;
    }

    public void setParkingCardFee(String parkingCardFee) {
        ParkingCardFee = parkingCardFee;
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
