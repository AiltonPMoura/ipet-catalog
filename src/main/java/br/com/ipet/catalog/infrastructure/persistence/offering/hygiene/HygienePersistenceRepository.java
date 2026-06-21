package br.com.ipet.catalog.infrastructure.persistence.offering.hygiene;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface HygienePersistenceRepository extends MongoRepository<HygieneDocument, UUID> {
    Optional<HygieneDocument> findByCompanyId(UUID companyId);
    boolean existsByCompanyId(UUID companyId);
}
