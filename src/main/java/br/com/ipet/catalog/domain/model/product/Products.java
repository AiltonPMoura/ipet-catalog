package br.com.ipet.catalog.domain.model.product;


import br.com.ipet.catalog.domain.model.Repository;
import br.com.ipet.catalog.domain.model.commons.ProductId;

import java.util.Optional;

public interface Products extends Repository<Product, ProductId> {
    Optional<Product> ofSubCategory(ProductSubCategory subCategory);
}
