package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.DomainException;

public class ServiceHigieneCannotBeGreaterThanFourHoursException extends DomainException {
    public ServiceHigieneCannotBeGreaterThanFourHoursException(String s) {
        super("Service time cannot be greater than 4 hours.");
    }
}
