package br.com.ipet.catalog.infrastructure.persistence.service;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.service.Service;
import br.com.ipet.catalog.domain.model.service.ServiceId;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ServicePersistenceRepository extends CrudRepository<Service, ServiceId> {
    Set<Service> findByCompanyId(CompanyId companyId);
}
