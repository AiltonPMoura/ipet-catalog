package br.com.ipet.catalog.infrastructure.persistence.offering;

import br.com.ipet.catalog.domain.model.DomainException;

public class ServiceOfferingNotFoundException extends DomainException {
    public ServiceOfferingNotFoundException(String s) {
        super(s);
    }
}
