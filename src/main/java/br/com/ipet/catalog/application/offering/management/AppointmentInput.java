package br.com.ipet.catalog.application.offering.management;

import br.com.ipet.catalog.application.commons.RateData;

import java.util.UUID;

public record AppointmentInput(UUID companyId,
                               String species,
                               String serviceType,
                               int duration,
                               RateData rate) {
}
