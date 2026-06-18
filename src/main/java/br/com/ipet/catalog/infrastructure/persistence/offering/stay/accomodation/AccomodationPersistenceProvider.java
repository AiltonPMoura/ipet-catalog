package br.com.ipet.catalog.infrastructure.persistence.offering.stay.accomodation;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;
import br.com.ipet.catalog.domain.model.offering.appointment.Accomodations;
import br.com.ipet.catalog.domain.model.offering.stay.Accommodation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccomodationPersistenceProvider implements Accomodations {

    private final AccomodationPersistenceRepository repository;
    private final AccomodationMapper accomodationMapper;
    private final AccomodationPersistenceMapper accomodationPersistenceMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Accommodation> ofId(ServiceOffereingId id) {
        return repository.findById(id.value()).map(accomodationMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(ServiceOffereingId id) {
        return repository.existsById(id.value());
    }

    @Override
    @Transactional
    public void add(Accommodation accommodation) {
        repository.findById(accommodation.id().value())
                .ifPresentOrElse(
                        accommodationPersistence -> this.update(accommodationPersistence, accommodation),
                        () -> this.insert(accommodation)
                );

        accommodation.clearDomainEvents();
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Accommodation> ofCompany(CompanyId companyId) {
        return repository.findByCompanyId(companyId).map(accomodationMapper::toDomain);
    }

    @Override
    public boolean existsOfCompany(CompanyId companyId) {
        return repository.existsByCompanyId(companyId);
    }

    private void insert(Accommodation accommodation) {
        var accommodationPersistence = accomodationPersistenceMapper.fromDomain(accommodation);
        repository.save(accommodationPersistence);
    }

    private void update(AccomodationDocument accommodationPersistence, Accommodation accommodation) {
        accommodationPersistence = accomodationPersistenceMapper.merge(accommodationPersistence, accommodation);
        repository.save(accommodationPersistence);
    }
}
