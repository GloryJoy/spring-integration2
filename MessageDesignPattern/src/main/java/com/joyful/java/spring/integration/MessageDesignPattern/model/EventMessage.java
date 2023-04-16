package com.joyful.java.spring.integration.MessageDesignPattern.model;

public class EventMessage {
    private String id;
    private String type;

    public EventMessage() {
    }

    public EventMessage(String id, String type) {
        this.id = id;
        this.type = type;
    }


    @Override
    public String toString() {
        return "EventMessage{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
