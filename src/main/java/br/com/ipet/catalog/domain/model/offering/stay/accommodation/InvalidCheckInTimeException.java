package br.com.ipet.catalog.domain.model.offering.stay.accommodation;

import br.com.ipet.catalog.domain.model.DomainException;

public class InvalidCheckInTimeException extends DomainException {
    public InvalidCheckInTimeException(String message) {
        super(message);
    }
}
