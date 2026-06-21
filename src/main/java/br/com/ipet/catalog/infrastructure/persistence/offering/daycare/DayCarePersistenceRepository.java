package br.com.ipet.catalog.infrastructure.persistence.offering.daycare;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface DayCarePersistenceRepository extends MongoRepository<DayCareDocument, UUID> {
    Optional<DayCareDocument> findByCompanyId(UUID companyId);
    boolean existsByCompanyId(UUID companyId);
}
