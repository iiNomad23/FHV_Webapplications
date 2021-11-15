package com.example.guestbook;

public class GuestBookEntry {
    private String name;
    private String email;
    private String text;

    public GuestBookEntry(String name, String email, String text) {
        this.name = name;
        this.email = email;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
