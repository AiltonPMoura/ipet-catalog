package br.com.ipet.catalog.domain.model.offering;

import br.com.ipet.catalog.domain.model.DomainException;

public class TimeCannotBeNullException extends DomainException {

    public TimeCannotBeNullException(String message) {
        super(message);
    }
}
