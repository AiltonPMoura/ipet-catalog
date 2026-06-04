package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.DomainException;

public class ServiceTimeCannotBeGreaterThanThreeHoursException extends DomainException {
    public ServiceTimeCannotBeGreaterThanThreeHoursException(String s) {
        super(s);
    }
}
