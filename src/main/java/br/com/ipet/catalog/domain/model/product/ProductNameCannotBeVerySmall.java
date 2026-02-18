package br.com.ipet.catalog.domain.model.product;

import br.com.ipet.catalog.domain.model.DomainException;
import br.com.ipet.catalog.domain.model.MessageCode;

public class ProductNameCannotBeVerySmall extends DomainException {

    public ProductNameCannotBeVerySmall() {
        super(MessageCode.ERROR_PRODUCT_NAME_CANNOT_BE_VERY_SMALL);
    }
}
