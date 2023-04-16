package com.joyful.java.spring.integration.MessageDesignPattern.model;

import lombok.Builder;

@Builder
public class FooReservation {
    private long id;
    private String name;

    public FooReservation() {
    }

    public FooReservation(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "FooReservation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
