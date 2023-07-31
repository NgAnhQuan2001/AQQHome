package com.example.aqqhome.model;

public class servicesmodel {
    private String RoomID;
    private String IsManagerServiceUsed;
    private String IsGarbageServiceUsed;
    private String NumberOfParkingCardsUsed;

    public servicesmodel(String roomID, String isManagerServiceUsed, String isGarbageServiceUsed, String numberOfParkingCardsUsed) {
        RoomID = roomID;
        IsManagerServiceUsed = isManagerServiceUsed;
        IsGarbageServiceUsed = isGarbageServiceUsed;
        NumberOfParkingCardsUsed = numberOfParkingCardsUsed;
    }

    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String roomID) {
        RoomID = roomID;
    }

    public String getIsManagerServiceUsed() {
        return IsManagerServiceUsed;
    }

    public void setIsManagerServiceUsed(String isManagerServiceUsed) {
        IsManagerServiceUsed = isManagerServiceUsed;
    }

    public String getIsGarbageServiceUsed() {
        return IsGarbageServiceUsed;
    }

    public void setIsGarbageServiceUsed(String isGarbageServiceUsed) {
        IsGarbageServiceUsed = isGarbageServiceUsed;
    }

    public String getNumberOfParkingCardsUsed() {
        return NumberOfParkingCardsUsed;
    }

    public void setNumberOfParkingCardsUsed(String numberOfParkingCardsUsed) {
        NumberOfParkingCardsUsed = numberOfParkingCardsUsed;
    }
}
