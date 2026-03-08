package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.Repository;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;

import java.util.Set;

public interface Services extends Repository<Service, ServiceId> {
    Set<Service> ofCompany(CompanyId companyId);
}
