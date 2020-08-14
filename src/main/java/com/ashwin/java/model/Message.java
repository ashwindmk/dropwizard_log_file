package com.ashwin.java.model;

public class Message {
    private long id;
    private String message;

    public Message() { }

    public Message(long id, String msg) {
        this.id = id;
        this.message = msg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
