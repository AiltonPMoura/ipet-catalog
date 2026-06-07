package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.DomainException;

public class ServiceTypeDoesNotSupportSpeciesException extends DomainException {
    public ServiceTypeDoesNotSupportSpeciesException(String... param) {
        super("Service type does not support the specified species");
    }
}
