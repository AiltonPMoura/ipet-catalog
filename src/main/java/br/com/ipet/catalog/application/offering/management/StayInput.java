package br.com.ipet.catalog.application.offering.management;

import br.com.ipet.catalog.application.commons.RateData;

import java.time.LocalTime;
import java.util.Set;

public record StayInput (String species,
                         String serviceType,
                         LocalTime checkin,
                         LocalTime checkout,
                         Set<RateData> rates) {
}
