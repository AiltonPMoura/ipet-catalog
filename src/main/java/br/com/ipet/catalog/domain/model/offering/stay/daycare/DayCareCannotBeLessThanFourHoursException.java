package br.com.ipet.catalog.domain.model.offering.stay.daycare;

import br.com.ipet.catalog.domain.model.DomainException;

public class DayCareCannotBeLessThanFourHoursException extends DomainException {
    public DayCareCannotBeLessThanFourHoursException(String s) {
        super(s);
    }
}
