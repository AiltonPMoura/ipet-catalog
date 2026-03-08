package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.DomainException;

public class ServiceDoesNotBelongToTheCompany extends DomainException {
    public ServiceDoesNotBelongToTheCompany() {
        super("");
    }
}
