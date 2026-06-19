package br.com.ipet.catalog.domain.model.offering.appointment.hygiene;

import br.com.ipet.catalog.domain.model.Repository;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;

import java.util.Set;

public interface Hygienes extends Repository<Hygiene, ServiceOffereingId> {
    Set<Hygiene> ofCompany(CompanyId companyId);
    boolean existsOfCompany(CompanyId companyId);
}
