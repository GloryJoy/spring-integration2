package com.joyful.java.spring.integration.MessageDesignPattern.service;

import com.joyful.java.spring.integration.MessageDesignPattern.configuration.FooReservationGateway;
import com.joyful.java.spring.integration.MessageDesignPattern.model.FooReservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FooReservationService {

    private static final Logger logger = LogManager.getLogger(FooReservationService.class);


    @Autowired
    private FooReservationGateway fooReservationGateway;

    public void publishReservation(FooReservation reservation){
        logger.info("Publishing reservation {} for user {}",
                reservation.getId(),
                reservation.getName());

        fooReservationGateway.publishReservation(reservation);
    }


}
