package br.com.ipet.catalog.infrastructure.persistence.offering.stay;

import br.com.ipet.catalog.application.util.Mapper;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.stay.DayCare;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;
import br.com.ipet.catalog.domain.model.offering.stay.DayCares;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DayCarePersistenceProvider implements DayCares {

    private final DayCarePersistenceRepository repository;
    private final Mapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<DayCare> ofId(ServiceOffereingId id) {
        return repository.findById(id.value());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(ServiceOffereingId id) {
        return repository.existsById(id);
    }

    @Override
    @Transactional
    public void add(DayCare service) {
        repository.save(service);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<DayCare> ofCompany(CompanyId companyId) {
        return repository.findByCompanyId(companyId);
    }

    @Override
    public boolean existsOfCompany(CompanyId companyId) {
        return repository.existsByCompanyId(companyId);
    }
}
