package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.FieldValidator;

public record DurationTime(int value) {

    private static final int MINIMUM_DURATION_MINUTES = 30;
    private static final int DURATION_INTERVAL_MINUTES = 15;

    public DurationTime {
        FieldValidator.requiresNonNull("duration", value);

        if (value < MINIMUM_DURATION_MINUTES)
            throw new MinimumDurationTimeException(String.valueOf(value));

        if (value % DURATION_INTERVAL_MINUTES != 0)
            throw new InvalidIntervalException("Time must be in intervals of 15 minutes.");
    }

}
