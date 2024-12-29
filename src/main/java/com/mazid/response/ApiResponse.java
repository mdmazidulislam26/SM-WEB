package com.mazid.response;

// ApiResponse class is used to send generic responses to the client.
public class ApiResponse {

    // Message describing the result of the operation.
    private String message;

    // Status indicating whether the operation was successful (true) or failed (false).
    private boolean status;

    // Default constructor.
    public ApiResponse(){}

    // Parameterized constructor to initialize message and status.
    public ApiResponse(String message, boolean status) {
        super();
        this.message = message;
        this.status = status;
    }

    // Getter for message.
    public String getMessage() {
        return message;
    }

    // Setter for message.
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter for status.
    public boolean isStatus() {
        return status;
    }

    // Setter for status.
    public void setStatus(boolean status) {
        this.status = status;
    }
}
