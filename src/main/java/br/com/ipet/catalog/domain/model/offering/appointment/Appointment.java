package br.com.ipet.catalog.domain.model.offering.appointment;

import br.com.ipet.catalog.domain.model.offering.RateService;

public interface Appointment {
    DurationTime duration();
    RateService rate();
}
