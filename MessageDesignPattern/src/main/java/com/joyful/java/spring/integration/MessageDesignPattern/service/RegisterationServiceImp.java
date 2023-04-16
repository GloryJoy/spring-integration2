package com.joyful.java.spring.integration.MessageDesignPattern.service;

import com.joyful.java.spring.integration.MessageDesignPattern.configuration.*;
import com.joyful.java.spring.integration.MessageDesignPattern.model.EventMessage;
import com.joyful.java.spring.integration.MessageDesignPattern.model.ReservationRecord;
import com.joyful.java.spring.integration.MessageDesignPattern.model.Swag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterationServiceImp implements RegisterationService {

    private static final Logger logger = LogManager.getLogger(RegisterationServiceImp.class);

    @Autowired
    private SwagGateway swagGateway;

    @Autowired
    private ReservationRecordGateway reservationRecordGateway;

    @Autowired
    private HotelBookingGateway hotelBookingGateway;

    @Autowired
    private EventGateway eventGateway;

    @Autowired
    private MainGateway mainGateway;

    @Override
    public void commit(String userId) {
        logger.info("Registeratin committed, sending Swag");

        mainGateway.sendSwag(MessageBuilder
                .withPayload(new Swag("T-Shirt"))
                .build());

        mainGateway.sendSwag(MessageBuilder
                .withPayload(new Swag("Hat"))
                .build());

        mainGateway.sendSwag(MessageBuilder
                .withPayload(new Swag("Key chain"))
                .build());

        mainGateway.sendSwag(MessageBuilder
                .withPayload(new Swag("USB Drive"))
                .build());

        logger.info("Published four Swag items to the Swag queueChannel");

    }

    @Override
    public void updateReservationRecord(ReservationRecord record) {
        var message = MessageBuilder
                .withPayload(record)
                .setHeader(IntegrationMessageHeaderAccessor.SEQUENCE_NUMBER, 1)
                .setHeader(IntegrationMessageHeaderAccessor.SEQUENCE_SIZE, 5)
                .build();

//        reservationRecordGateway.addRecord(message);
        mainGateway.addRecord(message);
    }

    public Boolean checkAvailability(Integer numberOfGuests) {
        try {

            logger.info("Checking hotel availability for {} guests", numberOfGuests);
            Message<Integer> request = MessageBuilder
                    .withPayload(numberOfGuests)
                    .build();

//        Message<Boolean> response = hotelBookingGateway.checkAvailability(request);
            Message<Boolean> response = mainGateway.checkAvailability(request);

            logger.info("Hotel Booking Service returned: {}", response);
            return response.getPayload();
        } catch (Exception e){
            logger.error("An error occurred while checking hotel availability, {}", e.getMessage());
            return false;
        }
    }

    public void notifyObservers(EventMessage eventMessage) {
        logger.info("Publishing event {}", eventMessage);

        var message = MessageBuilder
                .withPayload(eventMessage)
                .setHeader(IntegrationMessageHeaderAccessor.EXPIRATION_DATE, System.currentTimeMillis() + 60 * 60 * 1000)
                .build();
//        eventGateway.publishEvent(message);
        mainGateway.publishEvent(message);
    }



    public void setupReservation(String ... lastNames) {

        for (String lastName : lastNames) {
            logger.info("[Thread ID: {}] Set up reservation for user {}",
                    Thread.currentThread().getId(), lastName);

            var msg = MessageBuilder
                    .withPayload(lastName)
                    .build();

            mainGateway.createReservationRecord(msg);
        }

    }

    public void setupReservationTaskExecutor(String ... lastNames) {

        for (String lastName : lastNames) {
            logger.info("[Thread ID: {}] Set up reservation for user {}",
                    Thread.currentThread().getId(),
                    lastName);

            var msg = MessageBuilder
                    .withPayload(lastName)
                    .build();
            mainGateway.createReservationRecordTaskExecutor(msg);
        }

    }

}
