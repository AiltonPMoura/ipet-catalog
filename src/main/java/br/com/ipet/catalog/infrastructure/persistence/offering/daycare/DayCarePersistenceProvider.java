package br.com.ipet.catalog.infrastructure.persistence.offering.daycare;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;
import br.com.ipet.catalog.domain.model.offering.stay.daycare.DayCare;
import br.com.ipet.catalog.domain.model.offering.stay.daycare.DayCares;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DayCarePersistenceProvider implements DayCares {

    private final DayCarePersistenceRepository repository;
    private final DayCareMapper dayCareMapper;
    private final DayCarePersistenceMapper dayCarePersistenceMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<DayCare> ofId(ServiceOffereingId id) {
        return repository.findById(id.value()).map(dayCareMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(ServiceOffereingId id) {
        return repository.existsById(id.value());
    }

    @Override
    @Transactional
    public void add(DayCare dayCare) {
        repository.findById(dayCare.id().value())
                .ifPresentOrElse(
                        dayCarePersistence -> this.update(dayCarePersistence, dayCare),
                        () -> this.insert(dayCare)
                );

        dayCare.clearDomainEvents();
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DayCare> ofCompany(CompanyId companyId) {
        return repository.findByCompanyId(companyId.value()).map(dayCareMapper::toDomain);
    }

    @Override
    public boolean existsOfCompany(CompanyId companyId) {
        return repository.existsByCompanyId(companyId.value());
    }

    private void insert(DayCare dayCare) {
        var dayCarePersistence = dayCarePersistenceMapper.fromDomain(dayCare);
        repository.save(dayCarePersistence);
    }

    private void update(DayCareDocument dayCarePersistence, DayCare dayCare) {
        dayCarePersistence = dayCarePersistenceMapper.merge(dayCarePersistence, dayCare);
        repository.save(dayCarePersistence);
    }
}
