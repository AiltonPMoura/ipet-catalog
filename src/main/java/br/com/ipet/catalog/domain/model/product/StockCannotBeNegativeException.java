package br.com.ipet.catalog.domain.model.product;

import br.com.ipet.catalog.domain.model.DomainException;
import br.com.ipet.catalog.domain.model.MessageCode;

public class StockCannotBeNegativeException extends DomainException {

    public StockCannotBeNegativeException() {
        super(MessageCode.ERROR_STOCK_CANNOT_BE_NEGATIVE);
    }

}
