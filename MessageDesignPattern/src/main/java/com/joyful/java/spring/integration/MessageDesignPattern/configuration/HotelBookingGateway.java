package com.joyful.java.spring.integration.MessageDesignPattern.configuration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "hotelBookingGateway", defaultRequestChannel = "hotelBookingChannel")
public interface HotelBookingGateway {

    @Gateway
    Message<Boolean> checkAvailability(Message<Integer> numberOfGuests);

}
