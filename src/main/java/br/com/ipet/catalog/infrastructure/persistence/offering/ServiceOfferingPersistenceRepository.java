package br.com.ipet.catalog.infrastructure.persistence.offering;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceOfferingPersistenceRepository extends MongoRepository<AbstractServiceOfferingDocument, UUID> {
    List<AbstractServiceOfferingDocument> findAllByCompanyId(UUID companyId);
    Optional<AbstractServiceOfferingDocument> findByIdAndCompanyId(UUID id, UUID companyId);
}
