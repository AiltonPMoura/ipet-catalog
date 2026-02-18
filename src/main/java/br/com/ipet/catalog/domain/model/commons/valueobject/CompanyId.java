package br.com.ipet.catalog.domain.model.commons.valueobject;

import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.IdGenerator;

import java.util.UUID;

public record CompanyId(UUID value) {

    public CompanyId() {
        this(IdGenerator.generateTimeBasedEpochRandomGenerator());
    }

    public CompanyId {
        FieldValidator.requiresNonNull("companyId", value);
    }

}
