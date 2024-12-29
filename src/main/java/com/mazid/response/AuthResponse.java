package com.mazid.response;

// AuthResponse class is used to send authentication-related responses to the client
public class AuthResponse {

    // JWT token generated for authentication
    public String token;

    // A message indicating the status of the authentication process
    private String message;

    // Default constructor
    public AuthResponse(){}

    // Parameterized constructor to initialize token and message
    public AuthResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    // Getter for token
    public String getToken() {
        return token;
    }

    // Setter for token
    public void setToken(String token) {
        this.token = token;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Setter for message
    public void setMessage(String message) {
        this.message = message;
    }
}
