package br.com.ipet.catalog.domain.model.offering.stay.daycare;

import br.com.ipet.catalog.domain.model.Repository;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;

import java.util.Optional;

public interface DayCares extends Repository<DayCare, ServiceOffereingId> {
    Optional<DayCare> ofCompany(CompanyId companyId);
    boolean existsOfCompany(CompanyId companyId);
}
