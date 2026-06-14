package br.com.ipet.catalog.domain.model.offering.stay;

import br.com.ipet.catalog.domain.model.FieldValidator;

import java.time.OffsetDateTime;

public record StayDuration(OffsetDateTime checkIn,
                           OffsetDateTime checkOut) {

    public StayDuration {
        FieldValidator.requiresNonNull("checkInTime", checkIn);
        FieldValidator.requiresNonNull("checkOutTime", checkOut);
        FieldValidator.requireStartDateTimeIsBeforeEndTime(checkIn, checkOut);
    }
}
