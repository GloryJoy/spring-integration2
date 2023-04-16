package com.joyful.java.spring.integration.MessageDesignPattern.configuration;

import com.joyful.java.spring.integration.MessageDesignPattern.model.FooReservation;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "rmqReservationChannel")
public interface FooReservationGateway {
    void publishReservation(FooReservation fooReservation);
}
