package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.FieldValidator;

import java.time.OffsetDateTime;

public record StayDuration(OffsetDateTime checkIn,
                           OffsetDateTime checkOut) {

    public StayDuration {
        FieldValidator.requiresNonNull("checkIn", checkIn);
        FieldValidator.requiresNonNull("checkOut", checkOut);
        FieldValidator.requireStartDateTimeIsBeforeEndTime(checkIn, checkOut);
    }
}
