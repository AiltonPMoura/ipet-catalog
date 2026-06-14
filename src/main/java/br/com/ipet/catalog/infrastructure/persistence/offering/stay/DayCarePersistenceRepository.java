package br.com.ipet.catalog.infrastructure.persistence.offering.stay;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.stay.DayCare;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;
import java.util.UUID;

public interface DayCarePersistenceRepository extends MongoRepository<DayCareDocument, UUID> {
    Set<DayCare> findByCompanyId(CompanyId companyId);
    boolean existsByCompanyId(CompanyId companyId);
}
