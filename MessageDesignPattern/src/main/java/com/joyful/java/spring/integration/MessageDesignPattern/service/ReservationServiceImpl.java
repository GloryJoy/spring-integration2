package com.joyful.java.spring.integration.MessageDesignPattern.service;

import com.joyful.java.spring.integration.MessageDesignPattern.configuration.ReservationRecordGateway;
import com.joyful.java.spring.integration.MessageDesignPattern.model.ReservationRecord;
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@MessageEndpoint
@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger logger = LogManager.getLogger(ReservationServiceImpl.class);
    @Autowired
    private ReservationRecordGateway reservationRecordGateway;


    @ServiceActivator(inputChannel = "reservationRecordChannel")
    @Override
    public void addRecord(Message<ReservationRecord> message) {
        IntegrationMessageHeaderAccessor accessor = new IntegrationMessageHeaderAccessor(message);

        logger.info("Sequence:{}/{}", accessor.getSequenceNumber(), accessor.getSequenceSize());
        logger.info("Add reservation record: {}", message.getPayload());
    }

    @ServiceActivator(inputChannel = "directChannel")
    public void setupReservation(Message<String> lastName) {
        logger.info("[Thread ID: {}] [RESERV] Creating Reservation for user {}", Thread.currentThread().getId(), lastName.getPayload());

    }

    @ServiceActivator(inputChannel = "executorChannel")
    public void setupReservationTaskExecutor(Message<String> lastName) {
        logger.info("[Thread ID: {}] [RESERV-Executor] Creating Reservation for user {}",
                Thread.currentThread().getId(), lastName.getPayload());

    }
}
