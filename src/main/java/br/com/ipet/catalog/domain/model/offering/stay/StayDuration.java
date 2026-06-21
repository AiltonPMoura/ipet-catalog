package br.com.ipet.catalog.domain.model.offering.stay;

import br.com.ipet.catalog.domain.model.FieldValidator;

import java.time.OffsetDateTime;

public record StayDuration(OffsetDateTime checkin,
                           OffsetDateTime checkout) {

    public StayDuration {
        FieldValidator.requiresNonNull("checkin", checkin);
        FieldValidator.requiresNonNull("checkout", checkout);
        FieldValidator.requireStartDateTimeIsBeforeEndTime(checkin, checkout);
    }
}
