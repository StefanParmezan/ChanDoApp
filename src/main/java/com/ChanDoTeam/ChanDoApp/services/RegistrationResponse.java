package com.ChanDoTeam.ChanDoApp.services;

import com.ChanDoTeam.ChanDoApp.models.User;

public class RegistrationResponse {
    private User user;
    private String successMessage;
    private String errorMessage;

    public RegistrationResponse(User user, String successMessage, String errorMessage) {
        this.user = user;
        this.successMessage = successMessage;
        this.errorMessage = errorMessage;
    }

    public User getUser () {
        return user;
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