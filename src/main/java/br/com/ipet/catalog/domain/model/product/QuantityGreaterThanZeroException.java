package br.com.ipet.catalog.domain.model.product;


import br.com.ipet.catalog.domain.model.DomainException;
import br.com.ipet.catalog.domain.model.MessageCode;

public class QuantityGreaterThanZeroException extends DomainException {

    public QuantityGreaterThanZeroException() {
        super(MessageCode.ERROR_QUANTITY_GREATER_THAN_ZERO);
    }
}
