package com.joyful.java.spring.integration.MessageDesignPattern.configuration;

import com.joyful.java.spring.integration.MessageDesignPattern.model.EventMessage;
import com.joyful.java.spring.integration.MessageDesignPattern.model.ReservationRecord;
import com.joyful.java.spring.integration.MessageDesignPattern.model.Swag;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "mainGateway", errorChannel = "customErrorChannel")
public interface MainGateway {
    @Gateway(requestChannel = "queueChannel")
    void sendSwag(Message<Swag> swag);

    @Gateway(requestChannel = "reservationRecordChannel")
    void addRecord(Message<ReservationRecord> record);

    @Gateway(requestChannel = "hotelBookingChannel")
    Message<Boolean> checkAvailability(Message<Integer> numberOfGuests);

    @Gateway(requestChannel = "eventChannel")
    void publishEvent(Message<EventMessage> message);

    @Gateway(requestChannel = "directChannel")
    void createReservationRecord(Message<String> lastName);


    @Gateway(requestChannel = "executorChannel")
    void createReservationRecordTaskExecutor(Message<String> lastName);
}
