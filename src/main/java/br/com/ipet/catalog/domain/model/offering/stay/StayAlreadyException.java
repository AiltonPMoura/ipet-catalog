package br.com.ipet.catalog.domain.model.offering.stay;

import br.com.ipet.catalog.domain.model.DomainException;

public class StayAlreadyException extends DomainException {
    public StayAlreadyException(String s) {
        super(s);
    }
}
