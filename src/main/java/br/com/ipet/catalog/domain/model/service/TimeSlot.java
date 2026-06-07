package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.FieldValidator;

import java.time.Duration;

public record TimeSlot(Duration duration) {

    private static final int MINIMUM_DURATION_MINUTES = 30;
    private static final int DURATION_INTERVAL_MINUTES = 15;

    public TimeSlot {
        FieldValidator.requiresNonNull("duration", duration);

        if (duration.toMinutes() < MINIMUM_DURATION_MINUTES)
            throw new ServiceTimeCannotBeLessThanHalfHourException(String.valueOf(duration.toMinutes()));

        if (duration.toMinutes() % DURATION_INTERVAL_MINUTES != 0)
            throw new InvalidIntervalException("Time must be in intervals of 15 minutes.");
    }

}
