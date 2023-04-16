package com.joyful.java.spring.integration.MessageDesignPattern;

import com.joyful.java.spring.integration.MessageDesignPattern.configuration.FooReservationGateway;
import com.joyful.java.spring.integration.MessageDesignPattern.model.EventMessage;
import com.joyful.java.spring.integration.MessageDesignPattern.model.FooReservation;
import com.joyful.java.spring.integration.MessageDesignPattern.model.ReservationRecord;
import com.joyful.java.spring.integration.MessageDesignPattern.service.FooReservationService;
import com.joyful.java.spring.integration.MessageDesignPattern.service.RegisterationService;
import com.joyful.java.spring.integration.MessageDesignPattern.service.RegisterationServiceImp;
import com.joyful.java.spring.integration.MessageDesignPattern.service.ReservationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class MessageDesignPatternApplication implements CommandLineRunner, ExitCodeGenerator {
	private static final Logger logger = LogManager.getLogger(MessageDesignPatternApplication.class);

//
	@Autowired
	private RegisterationServiceImp registerationService;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	FooReservationService fooReservationService;


	public static void main(String[] args) {
		SpringApplication.run(MessageDesignPatternApplication.class, args);
	}

	public void processSimple(){

		registerationService.commit("123");
		registerationService.updateReservationRecord(new ReservationRecord("Person A", new Date()));

		Boolean available = registerationService.checkAvailability(4);
		logger.info("Hotel Availability for 4 guests: {}", available);

		available = registerationService.checkAvailability(0);
		logger.info("Hotel Availability for 4 guests: {}", available);

		registerationService.setupReservation("Smith", "Jones", "Anderson");
		registerationService.setupReservationTaskExecutor("Joy", "Prawena", "Mechoke");

		registerationService.notifyObservers(new EventMessage("Event - 1", "New Event"));


		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		SpringApplication.exit(applicationContext, this);
	}
	@Override
	public void run(String... args) throws Exception {

		processReservationQueue();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		SpringApplication.exit(applicationContext, this);
	}


	public void processReservationQueue(){
		List<FooReservation> reservations = Arrays.asList(
				new FooReservation(1, "User 1"),
				new FooReservation(2, "User 2"),
				new FooReservation(3, "User 3"),
				new FooReservation(4, "User 4"),
				new FooReservation(5, "User 5"),
				new FooReservation(6, "User 6"));

		reservations.forEach(reservation -> {
			try {
				fooReservationService.publishReservation(reservation);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

	}
	@Override
	public int getExitCode() {
		return 0;
	}
}
