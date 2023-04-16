package com.joyful.java.spring.integration.MessageDesignPattern.service;

import com.joyful.java.spring.integration.MessageDesignPattern.model.ReservationRecord;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

public interface ReservationService {
    void addRecord(Message<ReservationRecord> record);
    }
