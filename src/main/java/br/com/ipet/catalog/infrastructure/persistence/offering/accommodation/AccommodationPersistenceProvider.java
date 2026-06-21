package br.com.ipet.catalog.infrastructure.persistence.offering.accommodation;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.Accomodations;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.Accommodation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccommodationPersistenceProvider implements Accomodations {

    private final AccommodationPersistenceRepository repository;
    private final AccommodationMapper accommodationMapper;
    private final AccommodationPersistenceMapper accommodationPersistenceMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Accommodation> ofId(ServiceOffereingId id) {
        return repository.findById(id.value()).map(accommodationMapper::toDomain);
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
        return repository.findByCompanyId(companyId.value()).map(accommodationMapper::toDomain);
    }

    @Override
    public boolean existsOfCompany(CompanyId companyId) {
        return repository.existsByCompanyId(companyId.value());
    }

    private void insert(Accommodation accommodation) {
        var accommodationPersistence = accommodationPersistenceMapper.fromDomain(accommodation);
        repository.save(accommodationPersistence);
    }

    private void update(AccommodationDocument accommodationPersistence, Accommodation accommodation) {
        accommodationPersistence = accommodationPersistenceMapper.merge(accommodationPersistence, accommodation);
        repository.save(accommodationPersistence);
    }
}
