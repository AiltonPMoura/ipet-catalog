package br.com.ipet.catalog.application.offering.query;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface ServiceOfferingOutput {
    UUID serviceId();
    UUID companyId();
    String category();
    String species();
    String serviceType();
    OffsetDateTime registeredAt();
}
