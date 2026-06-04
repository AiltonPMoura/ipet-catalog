package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.DomainException;

public class DayCareCannotBeLessThanFourHoursException extends DomainException {
    public DayCareCannotBeLessThanFourHoursException(String s) {
        super(s);
    }
}
