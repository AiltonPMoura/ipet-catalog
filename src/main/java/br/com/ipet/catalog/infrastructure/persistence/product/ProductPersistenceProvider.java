package br.com.ipet.catalog.infrastructure.persistence.product;

import br.com.ipet.catalog.domain.model.commons.ProductId;
import br.com.ipet.catalog.domain.model.product.Product;
import br.com.ipet.catalog.domain.model.product.ProductSubCategory;
import br.com.ipet.catalog.domain.model.product.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductPersistenceProvider implements Products {

    private final ProductPersistenceRepository repository;
    private final ProductPersistenceMapper productPersistenceMapper;
    private final ProductMapper productMapper;

    @Override
    public Optional<Product> ofSubCategory(ProductSubCategory subCategory) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> ofId(ProductId id) {
        return repository.findById(id.value()).map(productMapper::toDomain);
    }

    @Override
    public boolean exists(ProductId id) {
        return false;
    }

    @Override
    public void add(Product product) {
        var productPersistenceEntity = productPersistenceMapper.fromDomain(product);
        repository.save(productPersistenceEntity);
    }

    @Override
    public int count() {
        return 0;
    }
}
