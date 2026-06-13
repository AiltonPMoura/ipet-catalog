package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.FieldValidator;

public record DurationTime(int duration) {

    private static final int MINIMUM_DURATION_MINUTES = 30;
    private static final int DURATION_INTERVAL_MINUTES = 15;

    public DurationTime {
        FieldValidator.requiresNonNull("duration", duration);

        if (duration < MINIMUM_DURATION_MINUTES)
            throw new MinimumDurationTimeException(String.valueOf(duration));

        if (duration % DURATION_INTERVAL_MINUTES != 0)
            throw new InvalidIntervalException("Time must be in intervals of 15 minutes.");
    }

}
