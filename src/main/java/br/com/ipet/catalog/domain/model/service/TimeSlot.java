package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.FieldValidator;

public record TimeSlot(Integer minutes) {

    private static final int MINIMUM_DURATION_MINUTES = 30;
    private static final int DURATION_INTERVAL_MINUTES = 15;

    public TimeSlot {
        FieldValidator.requiresNonNull("duration", minutes);

        if (minutes < MINIMUM_DURATION_MINUTES)
            throw new ServiceTimeCannotBeLessThanHalfHourException(String.valueOf(minutes));

        if (minutes % DURATION_INTERVAL_MINUTES != 0)
            throw new InvalidIntervalException("Time must be in intervals of 15 minutes.");
    }

}
