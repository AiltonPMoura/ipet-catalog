package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.DomainException;

public class InvalidCheckOutTimeException extends DomainException {
    public InvalidCheckOutTimeException(String message) {
        super(message);
    }
}
