package com.karsang.taskmanageractivity.ServerResponse;

public class SignUpResponse {

    private String status;
    private String token;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public SignUpResponse(String status, String token) {
        this.status = status;
        this.token = token;
    }



}
