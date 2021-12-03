package com.example.mySportClub.form;

public class LoginForm {
    private final String userId;
    private final String password;

    public LoginForm(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String userId() {
        return userId;
    }

    public String password() {
        return password;
    }
}
