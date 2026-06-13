package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.DomainException;

public class MinimumDurationTimeException extends DomainException {

    public MinimumDurationTimeException(String param) {
        super("Service time must be at least 30 minutes.");
    }
}
