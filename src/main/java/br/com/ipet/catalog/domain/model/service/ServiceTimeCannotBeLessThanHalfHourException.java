package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.DomainException;

public class ServiceTimeCannotBeLessThanHalfHourException extends DomainException {

    public ServiceTimeCannotBeLessThanHalfHourException(String param) {
        super("Service time must be at least 30 minutes.");
    }
}
