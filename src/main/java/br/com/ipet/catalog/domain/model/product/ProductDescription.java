package br.com.ipet.catalog.domain.model.product;

import br.com.ipet.catalog.domain.model.FieldValidator;

public record ProductDescription(String value) {

    public ProductDescription {
        FieldValidator.requiresNonBlank("product description", value);

        if (value.length() < 4)
            throw new ProductDescriptionCannotBeVerySmallException();
    }

}
