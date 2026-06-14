package br.com.ipet.catalog.domain.model.offering.appointment;

import br.com.ipet.catalog.domain.model.DomainException;

public class MaximumAppointmentDurationExceededException extends DomainException {
    public MaximumAppointmentDurationExceededException(String s) {
        super(s);
    }
}
