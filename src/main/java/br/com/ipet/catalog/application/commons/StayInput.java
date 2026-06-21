package br.com.ipet.catalog.application.commons;

import java.time.LocalTime;
import java.util.Set;

public record StayInput (String species,
                         String serviceType,
                         LocalTime checkin,
                         LocalTime checkout,
                         Set<RateData> rates) {
}
