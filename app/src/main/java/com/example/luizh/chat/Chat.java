package com.example.luizh.chat;

/**
 * Created by luizh on 26/05/2017.
 */

public class Chat {

    private String name;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return name + " - " + message;
    }
}
