package com.joyful.java.spring.integration.MessageDesignPattern.service;

import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@MessageEndpoint
@Service
public class HotelBookingService {

    private static final Logger logger = LogManager.getLogger(HotelBookingService.class);

    @ServiceActivator(inputChannel = "hotelBookingChannel")
    public Message<Boolean> checkAvailability(Message<Integer> numberOfGuestsMsg) throws RuntimeException{
        logger.info("check availability: received message : {}", numberOfGuestsMsg);
        numberOfGuestsMsg.getHeaders().entrySet()
                .forEach(entry -> logger.info("Header: {} = {}", entry.getKey(), entry.getValue()));


        Integer guests = numberOfGuestsMsg.getPayload();
        logger.info("Number of guests: {}", guests);

        if (guests <=0 ){
            throw new RuntimeException("Invalid party size " + guests);
        }

        return MessageBuilder
                .withPayload(true)
                .build();
    }

}
