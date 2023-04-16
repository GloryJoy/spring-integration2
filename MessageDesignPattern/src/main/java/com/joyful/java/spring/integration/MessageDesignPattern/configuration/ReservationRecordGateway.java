package com.joyful.java.spring.integration.MessageDesignPattern.configuration;

import com.joyful.java.spring.integration.MessageDesignPattern.model.ReservationRecord;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "reservationRecordGateway", defaultRequestChannel = "reservationRecordChannel")
public interface ReservationRecordGateway {
    @Gateway
    void addRecord(Message<ReservationRecord> record);
}
