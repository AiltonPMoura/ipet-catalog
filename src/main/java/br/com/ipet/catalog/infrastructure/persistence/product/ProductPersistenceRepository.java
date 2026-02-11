package br.com.ipet.catalog.infrastructure.persistence.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductPersistenceRepository extends JpaRepository<ProductPersistenceEntity, UUID> {
}
