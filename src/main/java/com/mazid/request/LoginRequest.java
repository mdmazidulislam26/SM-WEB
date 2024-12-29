package com.mazid.request;

public class LoginRequest {
    // The email of the user attempting to log in
    private String email;

    // The password of the user attempting to log in
    private String password;

    // Default constructor
    public LoginRequest(){}

    // Parameterized constructor to initialize email and password
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }
}
