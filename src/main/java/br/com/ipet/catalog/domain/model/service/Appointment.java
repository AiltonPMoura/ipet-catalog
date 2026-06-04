package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.commons.valueobject.Money;

public interface Appointment {
    Duration duration();
    PetSize petSize();
    Money price();
}
