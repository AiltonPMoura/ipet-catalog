package br.com.ipet.catalog.domain.model.product;

import lombok.RequiredArgsConstructor;

import static br.com.ipet.catalog.domain.model.product.ProductCategory.CAT;
import static br.com.ipet.catalog.domain.model.product.ProductCategory.DOG;

@RequiredArgsConstructor
public enum ProductSubCategory {
    DOG_FOOD(1, "Ração para cães", DOG),
    DOG_SNAKE(2, "Petisco para cães", DOG),
    CAT_FOOD(3, "Ração para gatos", CAT),
    CAT_SNAKE(4, "Petisco para gatos", CAT);

    public String nameValue() {
        return this.nameValue;
    }

    public Integer id() {
        return this.id;
    }

    public ProductCategory category() {
        return this.category;
    }

    private final Integer id;
    private final String nameValue;
    private final ProductCategory category;
}
