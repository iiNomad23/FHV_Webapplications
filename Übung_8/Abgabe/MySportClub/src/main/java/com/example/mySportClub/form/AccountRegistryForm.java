package com.example.mySportClub.form;

public class AccountRegistryForm {
    private final String userId;
    private final boolean validLogin;

    public AccountRegistryForm(String userId, boolean validLogin) {
        this.userId = userId;
        this.validLogin = validLogin;
    }

    public String userId() {
        return userId;
    }

    public boolean isValidLogin() {
        return validLogin;
    }
}
