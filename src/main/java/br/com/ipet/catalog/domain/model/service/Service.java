package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.commons.CompanyId;
import br.com.ipet.catalog.domain.model.commons.Money;
import br.com.ipet.catalog.domain.model.commons.ServiceId;
import lombok.Builder;

public class Service {
    private ServiceId id;
    private CompanyId companyId;
    private ServiceType type;
    private Money price;
    private ServiceDuration duration;

    @Builder(builderClassName = "CreateNewServiceBuilder", builderMethodName = "createNew")
    private static Service create(CompanyId companyId, ServiceType name, Money price) {
        return new Service(new ServiceId(), companyId, name, price);
    }

    @Builder(builderClassName = "ExistingServiceBuilder", builderMethodName = "existing")
    private Service(ServiceId id, CompanyId companyId, ServiceType type, Money price) {
        this.setId(id);
        this.setCompanyId(companyId);
        this.setServiceType(type);
        this.setPrice(price);
    }

    void changeType(ServiceType name) {
        this.setServiceType(type);
    }

    void changeDescription(ServiceDescription description) {
        FieldValidator.requiresNonNull("service description", description);
    }

    void changePrice(Money price) {
        this.setPrice(price);
    }

    public ServiceId id() {
        return id;
    }

    public CompanyId companyId() {
        return companyId;
    }

    public ServiceType type() {
        return type;
    }

    public Money price() {
        return price;
    }

    private void setId(ServiceId id) {
        FieldValidator.requiresNonNull("serviceId", id);
        this.id = id;
    }

    private void setCompanyId(CompanyId companyId) {
        FieldValidator.requiresNonNull("companyId", companyId);
        this.companyId = companyId;
    }

    private void setServiceType(ServiceType type) {
        FieldValidator.requiresNonNull("service type", type);
        this.type = type;
    }

    private void setPrice(Money price) {
        FieldValidator.requiresNonNull("service price", price);
        this.price = price;
    }
}
