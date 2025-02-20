package com.ChanDoTeam.ChanDoApp.services;

public class HabitResponse {
    private String successMessage;
    private String errorMessage;

    public HabitResponse(String successMessage, String errorMessage) {
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
        return errorMessage == null && successMessage != null;
    }
}
