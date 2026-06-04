package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.DomainException;

public class CheckinHoursCannotBeLessThanSixHoursException extends DomainException {
    public CheckinHoursCannotBeLessThanSixHoursException(String message) {
        super(message);
    }
}
