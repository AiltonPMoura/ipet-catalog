package br.com.ipet.catalog.infrastructure.persistence.offering.stay.daycare;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface DayCarePersistenceRepository extends MongoRepository<DayCareDocument, UUID> {
    Optional<DayCareDocument> findByCompanyId(CompanyId companyId);
    boolean existsByCompanyId(CompanyId companyId);
}
