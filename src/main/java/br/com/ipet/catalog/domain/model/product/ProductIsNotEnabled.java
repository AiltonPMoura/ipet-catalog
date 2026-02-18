package br.com.ipet.catalog.domain.model.product;

import br.com.ipet.catalog.domain.model.DomainException;

public class ProductIsNotEnabled extends DomainException {
    public ProductIsNotEnabled() {
        super("");
    }
}
