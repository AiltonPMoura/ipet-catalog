package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.DomainException;
import br.com.ipet.catalog.domain.model.MessageCode;
import lombok.Getter;

@Getter
public class ServiceNotFoundException extends DomainException {

    private final String[] fields;

    public ServiceNotFoundException(String... fields) {
        super(MessageCode.ERROR_SERVICE_NOT_FOUND);
        this.fields = fields;
    }

}
