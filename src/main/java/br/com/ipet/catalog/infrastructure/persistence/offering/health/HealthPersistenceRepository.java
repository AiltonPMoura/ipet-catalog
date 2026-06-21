package br.com.ipet.catalog.infrastructure.persistence.offering.health;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface HealthPersistenceRepository extends MongoRepository<HealthDocument, UUID> {
    Optional<HealthDocument> findByCompanyId(UUID companyId);
    boolean existsByCompanyId(UUID companyId);
}
