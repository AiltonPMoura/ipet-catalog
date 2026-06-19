package br.com.ipet.catalog.domain.model.offering.appointment;

import br.com.ipet.catalog.domain.model.DomainException;

public class AppointmentAlreadyException extends DomainException {
    public AppointmentAlreadyException(String s) {
        super(s);
    }
}
