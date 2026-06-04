package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.DomainException;

public class CheckoutHoursCannotBeGreaterThanNineteenHoursException extends DomainException {
    public CheckoutHoursCannotBeGreaterThanNineteenHoursException(String message) {
        super(message);
    }
}
