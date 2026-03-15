package br.com.ipet.catalog.application.service.management;

import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.commons.valueobject.Money;
import br.com.ipet.catalog.domain.model.service.PetSize;
import br.com.ipet.catalog.domain.model.service.ServiceDomain;
import br.com.ipet.catalog.domain.model.service.ServiceId;
import br.com.ipet.catalog.domain.model.service.ServiceNotFoundException;
import br.com.ipet.catalog.domain.model.service.ServiceTime;
import br.com.ipet.catalog.domain.model.service.ServiceType;
import br.com.ipet.catalog.domain.model.service.Services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceApplication {

    private ServiceDomain serviceDomain;
    private Services services;

    public UUID create(ServiceInput input) {
        FieldValidator.requiresNonNull("input", input);

        var service = this.serviceDomain.generate(
                input.getCompanyId(),
                ServiceType.valueOf(input.getType()),
                PetSize.valueOf(input.getSize()),
                new Money(input.getPrice()),
                new ServiceTime(input.getTime())
        );

        services.add(service);

        return service.id().value();
    }

    public void update(UUID serviceId, UUID companyId, ServiceUpdateInput input) {
        var service = services.ofId(new ServiceId(serviceId))
                .orElseThrow(() -> new ServiceNotFoundException(serviceId.toString()));

        serviceDomain.change(service, new CompanyId(companyId),
                ServiceType.valueOf(input.getType()),
                PetSize.valueOf(input.getSize()),
                new Money(input.getPrice()),
                new ServiceTime(input.getTime()));

        services.add(service);
    }

}
