package br.com.ipet.catalog.application.offering.management.stay;

import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

public record StayInput (UUID companyId,
                         String species,
                         String serviceType,
                         LocalTime checkin,
                         LocalTime checkout,
                         Set<RateData> rates) {
}
