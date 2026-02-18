package br.com.ipet.catalog.domain.model.product;

import br.com.ipet.catalog.domain.model.DomainException;
import br.com.ipet.catalog.domain.model.MessageCode;
import lombok.Getter;

@Getter
public class ProductNotFoundException extends DomainException {

    private final String value;

    public ProductNotFoundException(String value) {
        super(MessageCode.ERROR_PRODUCT_NOT_FOUND);
        this.value = value;
    }

}
