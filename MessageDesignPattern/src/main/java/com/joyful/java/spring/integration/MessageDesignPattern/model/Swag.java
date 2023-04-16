package com.joyful.java.spring.integration.MessageDesignPattern.model;

public class Swag {
    private String type;

    public Swag(String type) {
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private Long amount = 0L;

    public Swag(String type, Long amount) {
        this.type = type;
        this.amount = amount;
    }

    public Swag() {
    }

    @Override
    public String toString() {
        return "Swag{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
