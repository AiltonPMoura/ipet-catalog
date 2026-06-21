package br.com.ipet.catalog.application.offering.query;

import br.com.ipet.catalog.application.commons.RateData;

import java.util.UUID;

public record AppointmentDetailOutput(UUID serviceId,
                                      UUID companyId,
                                      String category,
                                      String species,
                                      String serviceType,
                                      int duration,
                                      RateData rate) implements ServiceOfferingOutput{
}
