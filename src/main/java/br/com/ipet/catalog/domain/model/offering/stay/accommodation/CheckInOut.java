package br.com.ipet.catalog.domain.model.offering.stay.accommodation;

import br.com.ipet.catalog.domain.model.offering.InvalidIntervalException;

import java.time.Duration;
import java.time.LocalTime;

import static br.com.ipet.catalog.domain.model.FieldValidator.requiresNonNull;

public record CheckInOut(LocalTime checkin, LocalTime checkout) {

    private static final int STAY_INTERVAL_MINUTES = 60;

    public CheckInOut {
        requiresNonNull("checkin", checkin);
        requiresNonNull("checkout", checkout);
        verifyValidInterval(checkin, checkout);
    }

    private void verifyValidInterval(LocalTime checkIn, LocalTime checkOut) {
        if (Duration.between(checkIn, checkOut).toMinutes() % STAY_INTERVAL_MINUTES != 0) {
            throw new InvalidIntervalException("interval between check-in and check-out must be in intervals of 1 hour.");
        }
    }

}
