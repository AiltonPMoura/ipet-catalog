package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.IdGenerator;

import java.util.UUID;

public record ServiceId(UUID value) {

    public ServiceId() {
        this(IdGenerator.generateTimeBasedEpochRandomGenerator());
    }

    public ServiceId {
        FieldValidator.requiresNonNull("serviceId value", value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
