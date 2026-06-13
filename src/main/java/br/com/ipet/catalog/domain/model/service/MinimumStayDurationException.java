package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.DomainException;

public class MinimumStayDurationException extends DomainException {
    public MinimumStayDurationException(String s) {
        super(s);
    }
}
