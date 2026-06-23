package br.com.ipet.catalog.application.offering.query;

import br.com.ipet.catalog.application.commons.RateData;

import java.time.OffsetDateTime;
import java.util.UUID;

public record AppointmentDetailOutput(UUID serviceId,
                                      UUID companyId,
                                      String category,
                                      String species,
                                      String serviceType,
                                      OffsetDateTime registeredAt,
                                      int duration,
                                      RateData rate) implements ServiceOfferingOutput{
}
