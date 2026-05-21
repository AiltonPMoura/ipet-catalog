package br.com.ipet.catalog.infrastructure.persistence.service;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.service.Service;
import br.com.ipet.catalog.domain.model.service.ServiceId;
import br.com.ipet.catalog.domain.model.service.Services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ServicePersistenceProvider implements Services {

    private final ServicePersistenceRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Service> ofId(ServiceId id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(ServiceId id) {
        return repository.existsById(id);
    }

    @Override
    @Transactional
    public void add(Service service) {
        repository.save(service);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Service> ofCompany(CompanyId companyId) {
        return repository.findByCompanyId(companyId);
    }
}
