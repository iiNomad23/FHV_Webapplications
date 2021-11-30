package com.example.mySportClub.domain;

public class User {
    private final String userId;
    private final String password;

    private final String firstName;
    private final String lastName;

    public User(String userId, String password, String firstName, String lastName) {
        this.userId = userId;
        this.password = password;

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String userId() {
        return userId;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String password() {
        return password;
    }
}
