package br.com.ipet.catalog.domain.model.service;


import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.commons.valueobject.Money;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceDomain {

    private final Services services;

    public Service generate(CompanyId companyId, ServiceType type,
                            PetSize petSize, Money price, ServiceTime time) {

        verifyServiceRequireTime(type, time);

        return Service.createNew()
                .companyId(companyId)
                .type(type)
                .petSize(petSize)
                .price(price)
                .time(time)
                .build();

    }

    public Service change(ServiceId id, CompanyId companyId, ServiceType type,
                          PetSize petSize, Money price, ServiceTime time) {
        var service = services.ofId(id)
                .orElseThrow(() -> new ServiceNotFoundException(id.toString()));

        verifyIfBelongsToTheCompany(companyId, service);
        verifyServiceRequireTime(type, time);

        service.changeType(type);
        service.changeServiceTime(time);
        service.changePetSize(petSize);
        service.changePrice(price);

        return service;
    }

    private void verifyIfBelongsToTheCompany(CompanyId companyId, Service service) {
        var doesNottBelongsToTheCustomer = services.ofCompany(companyId)
                .stream()
                .noneMatch(service::equals);

        if (doesNottBelongsToTheCustomer)
            throw new ServiceDoesNotBelongToTheCompany();
    }

    private void verifyServiceRequireTime(ServiceType type, ServiceTime time) {
        var serviceCategory = type.category();
        var isServiceTime = time == null &&
                (ServiceCategory.HEALTH.equals(serviceCategory)
                        || ServiceCategory.HYGIENE.equals(serviceCategory));

        if (isServiceTime)
            throw new TimeCannotBeNullException("Tempo é requerido para este tipo de serviço");
    }

}
