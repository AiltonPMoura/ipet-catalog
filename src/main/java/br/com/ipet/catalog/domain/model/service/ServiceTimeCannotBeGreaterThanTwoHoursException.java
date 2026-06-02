package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.DomainException;

public class ServiceTimeCannotBeGreaterThanTwoHoursException extends DomainException {
    public ServiceTimeCannotBeGreaterThanTwoHoursException(String param) {
        super("Service time cannot be greater than 120 minutes. Provided value: " + param);
    }
}
