package br.com.ipet.catalog.infrastructure.persistence.offering.activity;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;
import br.com.ipet.catalog.domain.model.offering.appointment.activity.Activities;
import br.com.ipet.catalog.domain.model.offering.appointment.activity.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ActivityPersistenceProvider implements Activities {

    private final ActivityPersistenceRepository repository;
    private final ActivityMapper activityMapper;
    private final ActivityPersistenceMapper activityPersistenceMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Activity> ofId(ServiceOffereingId id) {
        return repository.findById(id.value()).map(activityMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(ServiceOffereingId id) {
        return repository.existsById(id.value());
    }

    @Override
    @Transactional
    public void add(Activity activity) {
        repository.findById(activity.id().value())
                .ifPresentOrElse(
                        activityPersistence -> this.update(activityPersistence, activity),
                        () -> this.insert(activity)
                );

        activity.clearDomainEvents();
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Activity> ofCompany(CompanyId companyId) {
        return repository.findByCompanyId(companyId.value())
                .stream().map(activityMapper::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean existsOfCompany(CompanyId companyId) {
        return repository.existsByCompanyId(companyId.value());
    }

    private void insert(Activity activity) {
        var activityPersistence = activityPersistenceMapper.fromDomain(activity);
        repository.save(activityPersistence);
    }

    private void update(ActivityDocument activityPersistence, Activity activity) {
        activityPersistence = activityPersistenceMapper.merge(activityPersistence, activity);
        repository.save(activityPersistence);
    }
}
