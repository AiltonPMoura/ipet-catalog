package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.FieldValidator;

import java.time.OffsetTime;

public record ServiceTime(OffsetTime value) {

    public ServiceTime {
        FieldValidator.requiresNonNull("value", value);

        if (value.getMinute() % 15 > 0)
            throw new RuntimeException("Minutos precisa ser múltiplos de 15");
    }

}
