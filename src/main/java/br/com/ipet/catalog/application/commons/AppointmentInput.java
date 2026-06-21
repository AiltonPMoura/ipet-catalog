package br.com.ipet.catalog.application.commons;

import java.util.UUID;

public record AppointmentInput(UUID companyId,
                               String species,
                               String serviceType,
                               int duration,
                               RateData rate) {
}
