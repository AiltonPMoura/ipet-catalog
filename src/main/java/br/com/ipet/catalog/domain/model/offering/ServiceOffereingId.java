package br.com.ipet.catalog.domain.model.offering;

import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.IdGenerator;

import java.util.UUID;

public record ServiceOffereingId(UUID value) {

    public ServiceOffereingId() {
        this(IdGenerator.generateTimeBasedEpochRandomGenerator());
    }

    public ServiceOffereingId {
        FieldValidator.requiresNonNull("serviceId value", value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
