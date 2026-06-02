package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.FieldValidator;

import java.time.Duration;

public record TimeSlotDuration(Duration value) {

    private static final int MINIMUM_SERVICE_TIME_IN_MINUTES = 30;
    private static final int MAXIMUM_SERVICE_TIME_IN_MINUTES = 240;
    private static final int SERVICE_TIME_INTERVAL_IN_MINUTES = 15;

    public TimeSlotDuration {
        FieldValidator.requiresNonNull("duration", value);

        var minutes = value.toMinutes();

        if (minutes < MINIMUM_SERVICE_TIME_IN_MINUTES)
            throw new ServiceTimeCannotBeLessThanHalfHourException(String.valueOf(minutes));

        if (minutes > MAXIMUM_SERVICE_TIME_IN_MINUTES)
            throw new ServiceTimeCannotBeGreaterThanTwoHoursException(String.valueOf(minutes));

        if (minutes % SERVICE_TIME_INTERVAL_IN_MINUTES != 0)
            throw new InvalidIntervalException("Service time must be in intervals of 15 minutes.");
    }

}
