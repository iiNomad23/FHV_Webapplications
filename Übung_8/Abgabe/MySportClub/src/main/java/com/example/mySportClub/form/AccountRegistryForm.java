package com.example.mySportClub.form;

public class AccountRegistryForm {
    private final String userId;
    private final String message;

    private final boolean registrySuccessful;
    private final boolean sameUser;

    public AccountRegistryForm(String userId, String message, boolean registrySuccessful, boolean sameUser) {
        this.userId = userId;
        this.registrySuccessful = registrySuccessful;
        this.message = message;
        this.sameUser = sameUser;
    }

    public String userId() {
        return this.userId;
    }

    public String message() {
        return this.message;
    }

    public boolean isRegistrySuccessful() {
        return this.registrySuccessful;
    }

    public boolean isSameUser() {
        return this.sameUser;
    }
}
