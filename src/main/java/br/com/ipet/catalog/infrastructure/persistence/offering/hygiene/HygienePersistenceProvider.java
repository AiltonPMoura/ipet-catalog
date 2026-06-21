package br.com.ipet.catalog.infrastructure.persistence.offering.hygiene;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;
import br.com.ipet.catalog.domain.model.offering.appointment.hygiene.Hygiene;
import br.com.ipet.catalog.domain.model.offering.appointment.hygiene.Hygienes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HygienePersistenceProvider implements Hygienes {

    private final HygienePersistenceRepository repository;
    private final HygieneMapper hygieneMapper;
    private final HygienePersistenceMapper hygienePersistenceMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Hygiene> ofId(ServiceOffereingId id) {
        return repository.findById(id.value()).map(hygieneMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(ServiceOffereingId id) {
        return repository.existsById(id.value());
    }

    @Override
    @Transactional
    public void add(Hygiene hygiene) {
        repository.findById(hygiene.id().value())
                .ifPresentOrElse(
                        hygienePersistence -> this.update(hygienePersistence, hygiene),
                        () -> this.insert(hygiene)
                );

        hygiene.clearDomainEvents();
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Hygiene> ofCompany(CompanyId companyId) {
        return repository.findByCompanyId(companyId.value())
                .stream().map(hygieneMapper::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean existsOfCompany(CompanyId companyId) {
        return repository.existsByCompanyId(companyId.value());
    }

    private void insert(Hygiene hygiene) {
        var hygienePersistence = hygienePersistenceMapper.fromDomain(hygiene);
        repository.save(hygienePersistence);
    }

    private void update(HygieneDocument hygienePersistence, Hygiene hygiene) {
        hygienePersistence = hygienePersistenceMapper.merge(hygienePersistence, hygiene);
        repository.save(hygienePersistence);
    }
}
