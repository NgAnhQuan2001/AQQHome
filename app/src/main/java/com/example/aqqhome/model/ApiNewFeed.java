package com.example.aqqhome.model;

import java.util.List;

public class ApiNewFeed {
    private boolean success;
    private String message;
    private List<newfeedmodel> records;

    public ApiNewFeed(boolean success, String message, List<newfeedmodel> records) {
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

    public List<newfeedmodel> getRecords() {
        return records;
    }

    public void setRecords(List<newfeedmodel> records) {
        this.records = records;
    }

}
