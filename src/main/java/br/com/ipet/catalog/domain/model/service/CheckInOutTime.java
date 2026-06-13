package br.com.ipet.catalog.domain.model.service;

import java.time.Duration;
import java.time.LocalTime;

import static br.com.ipet.catalog.domain.model.FieldValidator.requiresNonNull;

public record CheckInOutTime(LocalTime checkInTime, LocalTime checkOutTime) {

    private static final int STAY_INTERVAL_MINUTES = 60;

    public CheckInOutTime {
        requiresNonNull("checkInTime", checkInTime);
        requiresNonNull("checkOutTime", checkOutTime);
        verifyValidInterval(checkInTime, checkOutTime);
    }

    private void verifyValidInterval(LocalTime checkIn, LocalTime checkOut) {
        if (Duration.between(checkIn, checkOut).toMinutes() % STAY_INTERVAL_MINUTES != 0) {
            throw new InvalidIntervalException("interval between check-in and check-out must be in intervals of 1 hour.");
        }
    }

}
