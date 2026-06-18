package br.com.ipet.catalog.infrastructure.persistence.offering.stay.accomodation;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccomodationPersistenceRepository extends MongoRepository<AccomodationDocument, UUID> {
    Optional<AccomodationDocument> findByCompanyId(CompanyId companyId);
    boolean existsByCompanyId(CompanyId companyId);
}
