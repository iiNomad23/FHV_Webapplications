package com.example.mySportClub.form;

public class LoginForm {
    private final String userId;
    private final String password;

    private boolean validLogin;

    public LoginForm(String userId, String password, boolean validLogin) {
        this.userId = userId;
        this.password = password;
        this.validLogin = validLogin;
    }

    public String userId() {
        return userId;
    }

    public String password() {
        return password;
    }

    public boolean isValidLogin() {
        return validLogin;
    }

    public void loginSuccessful() {
        this.validLogin = true;
    }
}
