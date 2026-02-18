package br.com.ipet.catalog.infrastructure.persistence.product;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    /*public Product toDomain(ProductPersistenceEntity productPersistenceEntity) {
        return Product.existing()
                .id(new ProductId(productPersistenceEntity.getId()))
                .companyId(new CompanyId(productPersistenceEntity.getCompanyId()))
                .name(new ProductName(productPersistenceEntity.getName()))
                .description(new ProductDescription(productPersistenceEntity.getDescription()))
                .price(new Money(productPersistenceEntity.getPrice()))
                .totalStock(new Quantity(productPersistenceEntity.getTotalStock()))
                .subCategory(ProductSubCategory.valueOf(productPersistenceEntity.getSubCategory()))
                .enabled(productPersistenceEntity.isEnabled())
                .build();
    }*/

}
