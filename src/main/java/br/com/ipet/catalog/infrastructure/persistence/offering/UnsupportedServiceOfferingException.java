package br.com.ipet.catalog.infrastructure.persistence.offering;

import br.com.ipet.catalog.domain.model.DomainException;

public class UnsupportedServiceOfferingException extends DomainException {
    public UnsupportedServiceOfferingException(String s) {
        super(s);
    }
}
