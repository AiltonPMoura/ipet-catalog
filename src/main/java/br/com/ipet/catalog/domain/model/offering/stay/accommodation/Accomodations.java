package br.com.ipet.catalog.domain.model.offering.stay.accommodation;

import br.com.ipet.catalog.domain.model.Repository;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;

import java.util.Optional;

public interface Accomodations extends Repository<Accommodation, ServiceOffereingId> {
    Optional<Accommodation> ofCompany(CompanyId companyId);
    boolean existsOfCompany(CompanyId companyId);
}
