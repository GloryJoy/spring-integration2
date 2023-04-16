package com.joyful.java.spring.integration.MessageDesignPattern.service;

import com.joyful.java.spring.integration.MessageDesignPattern.model.ReservationRecord;

public interface RegisterationService {
    public void commit(String userId);
    void updateReservationRecord(ReservationRecord record);
}
