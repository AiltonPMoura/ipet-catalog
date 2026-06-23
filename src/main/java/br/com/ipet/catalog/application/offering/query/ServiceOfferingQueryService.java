package br.com.ipet.catalog.application.offering.query;

import java.util.List;
import java.util.UUID;

public interface ServiceOfferingQueryService {

    List<ServiceOfferingOutput> findAll(UUID companyId);

    ServiceOfferingOutput findById(UUID companyId, UUID id);

}
