package com.example.aqqhome.model;

import java.util.List;

public class ApiResponse {
    private boolean success;
    private String message;
    private List<roommodel2> records;
    private String IsManagerServiceUsed;
    private String IsGarbageServiceUsed;

    public ApiResponse(boolean success, String message, List<roommodel2> records, String isManagerServiceUsed, String isGarbageServiceUsed) {
        this.success = success;
        this.message = message;
        this.records = records;
        IsManagerServiceUsed = isManagerServiceUsed;
        IsGarbageServiceUsed = isGarbageServiceUsed;
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

    public List<roommodel2> getRecords() {
        return records;
    }

    public void setRecords(List<roommodel2> records) {
        this.records = records;
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
}
