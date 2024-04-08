package com.example.pr3_zubarev;

import java.io.Serializable;

public class data implements Serializable {
    private String message;

    public data(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
