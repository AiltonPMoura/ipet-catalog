package br.com.ipet.catalog.infrastructure.persistence.offering.accommodation;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccommodationPersistenceRepository extends MongoRepository<AccommodationDocument, UUID> {
    Optional<AccommodationDocument> findByCompanyId(UUID companyId);
    boolean existsByCompanyId(UUID companyId);
}
