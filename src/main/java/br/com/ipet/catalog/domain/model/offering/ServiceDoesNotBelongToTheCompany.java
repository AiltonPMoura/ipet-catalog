package br.com.ipet.catalog.domain.model.offering;

import br.com.ipet.catalog.domain.model.DomainException;

public class ServiceDoesNotBelongToTheCompany extends DomainException {
    public ServiceDoesNotBelongToTheCompany() {
        super("");
    }
}
