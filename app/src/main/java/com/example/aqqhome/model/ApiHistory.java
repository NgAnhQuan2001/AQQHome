package com.example.aqqhome.model;

import java.util.List;

public class ApiHistory {
    private boolean success;
    private String message;
    private List<bankmodel> records;

    public ApiHistory(boolean success, String message, List<bankmodel> records) {
        this.success = success;
        this.message = message;
        this.records = records;
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

    public List<bankmodel> getRecords() {
        return records;
    }

    public void setRecords(List<bankmodel> records) {
        this.records = records;
    }
}
