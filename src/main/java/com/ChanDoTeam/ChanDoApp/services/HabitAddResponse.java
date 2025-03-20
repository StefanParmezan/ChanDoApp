package com.ChanDoTeam.ChanDoApp.services;

public class HabitAddResponse {
    private String successMessage;
    private String errorMessage;

    public HabitAddResponse(String successMessage, String errorMessage) {
        this.successMessage = successMessage;
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isSuccess() {
        return errorMessage == null;
    }
}