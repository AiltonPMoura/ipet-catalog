package br.com.ipet.catalog.domain.model.product;

import br.com.ipet.catalog.domain.model.FieldValidator;

public record ProductName(String value) {

    public ProductName {
        FieldValidator.requiresNonBlank("product name", value);

        if (value.length() < 4)
            throw new ProductNameCannotBeVerySmall();
    }

}
