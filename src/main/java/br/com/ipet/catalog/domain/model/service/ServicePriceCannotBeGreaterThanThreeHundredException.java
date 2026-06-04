package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.DomainException;

import java.math.BigDecimal;

public class ServicePriceCannotBeGreaterThanThreeHundredException extends DomainException {
    public ServicePriceCannotBeGreaterThanThreeHundredException(BigDecimal provided) {
        super("Service price cannot be greater than 300.00. Provided value: " + provided);
    }
}

