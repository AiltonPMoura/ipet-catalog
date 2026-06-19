package br.com.ipet.catalog.domain.model.offering.appointment;

import br.com.ipet.catalog.domain.model.offering.Rate;

public interface Appointment {
    DurationTime duration();
    Rate rate();
}
