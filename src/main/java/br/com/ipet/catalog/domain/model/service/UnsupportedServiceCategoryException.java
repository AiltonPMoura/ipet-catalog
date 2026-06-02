package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.DomainException;

public class UnsupportedServiceCategoryException extends DomainException {
    public UnsupportedServiceCategoryException(String category) {
        super("Categoria de serviço não suportada para este tipo de serviço: " + category);
    }
}
