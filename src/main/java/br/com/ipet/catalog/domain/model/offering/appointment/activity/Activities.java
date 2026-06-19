package br.com.ipet.catalog.domain.model.offering.appointment.activity;

import br.com.ipet.catalog.domain.model.Repository;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;

import java.util.Set;

public interface Activities extends Repository<Activity, ServiceOffereingId> {
    Set<Activity> ofCompany(CompanyId companyId);
    boolean existsOfCompany(CompanyId companyId);
}
