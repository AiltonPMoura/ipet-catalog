package br.com.ipet.catalog.infrastructure.persistence.offering.health;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;
import br.com.ipet.catalog.domain.model.offering.appointment.health.Health;
import br.com.ipet.catalog.domain.model.offering.appointment.health.Healths;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HealthPersistenceProvider implements Healths {

    private final HealthPersistenceRepository repository;
    private final HealthMapper healthMapper;
    private final HealthPersistenceMapper healthPersistenceMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Health> ofId(ServiceOffereingId id) {
        return repository.findById(id.value()).map(healthMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(ServiceOffereingId id) {
        return repository.existsById(id.value());
    }

    @Override
    @Transactional
    public void add(Health health) {
        repository.findById(health.id().value())
                .ifPresentOrElse(
                        healthPersistence -> this.update(healthPersistence, health),
                        () -> this.insert(health)
                );

        health.clearDomainEvents();
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Health> ofCompany(CompanyId companyId) {
        return repository.findByCompanyId(companyId.value())
                .stream().map(healthMapper::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean existsOfCompany(CompanyId companyId) {
        return repository.existsByCompanyId(companyId.value());
    }

    private void insert(Health health) {
        var healthPersistence = healthPersistenceMapper.fromDomain(health);
        repository.save(healthPersistence);
    }

    private void update(HealthDocument healthPersistence, Health health) {
        healthPersistence = healthPersistenceMapper.merge(healthPersistence, health);
        repository.save(healthPersistence);
    }
}
