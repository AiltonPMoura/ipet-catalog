package br.com.ipet.catalog.domain.model.offering.stay;

import br.com.ipet.catalog.domain.model.DomainException;

public class MinimumStayDurationException extends DomainException {
    public MinimumStayDurationException(String s) {
        super(s);
    }
}
