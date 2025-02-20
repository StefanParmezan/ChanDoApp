package com.ChanDoTeam.ChanDoApp.services;

public class LoginResponse {
    private String successMessage;
    private String errorMessage;

    public LoginResponse(String successMessage, String errorMessage) {
        this.successMessage = successMessage;
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    // Метод isSuccess() теперь проверяет наличие сообщения об ошибке
    public boolean isSuccess() {
        return errorMessage == null && successMessage != null;
    }
}