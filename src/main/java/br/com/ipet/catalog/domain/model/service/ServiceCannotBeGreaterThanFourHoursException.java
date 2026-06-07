package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.DomainException;

public class ServiceCannotBeGreaterThanFourHoursException extends DomainException {
    public ServiceCannotBeGreaterThanFourHoursException(String s) {
        super("Service time cannot be greater than 4 hours.");
    }
}
