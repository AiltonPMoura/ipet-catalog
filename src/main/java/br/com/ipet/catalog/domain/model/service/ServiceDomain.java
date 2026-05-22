package br.com.ipet.catalog.domain.model.service;


import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.commons.valueobject.Money;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceDomain {

    private final Services services;

    public Service generate(CompanyId companyId, ServiceCategory category,
                            PetSize petSize, Money price, ServiceTime time) {

        verifyServiceRequireTime(category, time);

        return Service.createNew()
                .companyId(companyId)
                .category(category)
                .petSize(petSize)
                .price(price)
                .time(time)
                .build();

    }

    public void change(Service service, CompanyId companyId, ServiceCategory category,
                       PetSize petSize, Money price, ServiceTime time) {
        verifyIfBelongsToTheCompany(companyId, service);
        verifyServiceRequireTime(category, time);

        service.changeCategory(category);
        service.changeServiceTime(time);
        service.changePetSize(petSize);
        service.changePrice(price);
    }

    private void verifyIfBelongsToTheCompany(CompanyId companyId, Service service) {
        var doesNottBelongsToTheCustomer = services.ofCompany(companyId)
                .stream()
                .noneMatch(service::equals);

        if (doesNottBelongsToTheCustomer)
            throw new ServiceDoesNotBelongToTheCompany();
    }

    private void verifyServiceRequireTime(ServiceCategory category, ServiceTime time) {
        var isServiceTime = time == null &&
                (ServiceCategory.HIGYENE.equals(category)
                        || ServiceCategory.HEALTH.equals(category));

        if (isServiceTime)
            throw new TimeCannotBeNullException("Tempo é requerido para este tipo de serviço");
    }

}
