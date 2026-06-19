package br.com.ipet.catalog.domain.model.offering.appointment.health;

import br.com.ipet.catalog.domain.model.Repository;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;

import java.util.Set;

public interface Healths extends Repository<Health, ServiceOffereingId> {
    Set<Health> ofCompany(CompanyId companyId);
    boolean existsOfCompany(CompanyId companyId);
}
