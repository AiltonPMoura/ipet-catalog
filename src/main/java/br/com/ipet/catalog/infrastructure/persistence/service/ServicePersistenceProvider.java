package br.com.ipet.catalog.infrastructure.persistence.service;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.service.Service;
import br.com.ipet.catalog.domain.model.service.ServiceId;
import br.com.ipet.catalog.domain.model.service.Services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ServicePersistenceProvider implements Services {

    private final ServicePersistenceRepository repository;

    @Override
    public Optional<Service> ofId(ServiceId id) {
        return repository.findById(id);
    }

    @Override
    public boolean exists(ServiceId id) {
        return repository.existsById(id);
    }

    @Override
    public void add(Service service) {
        repository.save(service);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Set<Service> ofCompany(CompanyId companyId) {
        return repository.findByCompanyId(companyId);
    }
}
