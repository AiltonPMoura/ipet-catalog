package br.com.ipet.catalog.infrastructure.persistence.product;

import br.com.ipet.ordering.domain.model.entity.Product;
import br.com.ipet.ordering.infrastructure.persistence.repository.CompanyPersistenceEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductPersistenceMapper {

    private final CompanyPersistenceEntityRepository companyPersistenceEntityRepository;

    public ProductPersistenceEntity fromDomain(Product product) {
        return merge(new ProductPersistenceEntity(), product);
    }

    private ProductPersistenceEntity merge(ProductPersistenceEntity productPersistenceEntity, Product product) {
        productPersistenceEntity.setId(product.id().value());
        productPersistenceEntity.setCompany(companyPersistenceEntityRepository.getReferenceById(product.companyId().value()));
        productPersistenceEntity.setName(product.name().value());
        productPersistenceEntity.setDescription(product.description().value());
        productPersistenceEntity.setPrice(product.price().value());
        productPersistenceEntity.setTotalStock(product.totalStock().value());
        productPersistenceEntity.setCategory(product.subCategory().category().name());
        productPersistenceEntity.setSubCategory(product.subCategory().name());
        productPersistenceEntity.setEnabled(product.enabled());
        return productPersistenceEntity;
    }

}
