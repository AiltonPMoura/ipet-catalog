package br.com.ipet.catalog.domain.model.product;

import br.com.ipet.catalog.domain.model.DomainException;

public class ProductOutOfStock extends DomainException {
    public ProductOutOfStock() {
        super("");
    }
}
