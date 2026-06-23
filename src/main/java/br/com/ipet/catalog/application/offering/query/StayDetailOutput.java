package br.com.ipet.catalog.application.offering.query;

import br.com.ipet.catalog.application.commons.RateData;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record StayDetailOutput(UUID serviceId,
                               UUID companyId,
                               String category,
                               String species,
                               String serviceType,
                               OffsetDateTime registeredAt,
                               String checkIn,
                               String checkOut,
                               List<RateData> rates) implements ServiceOfferingOutput{
}
