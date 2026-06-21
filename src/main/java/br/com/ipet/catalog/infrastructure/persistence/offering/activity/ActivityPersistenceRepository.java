package br.com.ipet.catalog.infrastructure.persistence.offering.activity;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;
import java.util.UUID;

public interface ActivityPersistenceRepository extends MongoRepository<ActivityDocument, UUID> {
    Set<ActivityDocument> findByCompanyId(UUID companyId);
    boolean existsByCompanyId(UUID companyId);
}
