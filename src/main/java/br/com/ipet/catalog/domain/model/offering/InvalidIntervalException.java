package br.com.ipet.catalog.domain.model.offering;


import br.com.ipet.catalog.domain.model.DomainException;

public class InvalidIntervalException extends DomainException {

    public InvalidIntervalException(String s) {
        super(s);
    }

}
