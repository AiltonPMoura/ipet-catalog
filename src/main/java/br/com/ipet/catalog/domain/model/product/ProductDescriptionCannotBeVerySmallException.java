package br.com.ipet.catalog.domain.model.product;

import br.com.ipet.catalog.domain.model.DomainException;
import br.com.ipet.catalog.domain.model.MessageCode;

public class ProductDescriptionCannotBeVerySmallException extends DomainException {

    public ProductDescriptionCannotBeVerySmallException() {
        super(MessageCode.ERROR_PRODUCT_DESCRIPTION_CANNOT_BE_VERY_SMALL);
    }
}
