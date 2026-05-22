package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.AggregateRoot;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.commons.valueobject.Money;
import lombok.Builder;

import java.util.Objects;

public class Service implements AggregateRoot<ServiceId> {
    private ServiceId id;
    private CompanyId companyId;
    private ServiceCategory category;
    private PetSize petSize;
    private Money price;
    private ServiceTime time;

    @Builder(builderClassName = "CreateNewServiceBuilder", builderMethodName = "createNew")
    private static Service create(CompanyId companyId, ServiceCategory category,
                                  PetSize petSize, Money price, ServiceTime time) {
        return new Service(new ServiceId(), companyId, category, petSize, price, time);
    }

    @Builder(builderClassName = "ExistingServiceBuilder", builderMethodName = "existing")
    private Service(ServiceId id, CompanyId companyId, ServiceCategory category,
                    PetSize petSize, Money price, ServiceTime time) {
        this.setId(id);
        this.setCompanyId(companyId);
        this.setCategory(category);
        this.setPetSize(petSize);
        this.setPrice(price);
        this.setTime(time);
    }

    void changeCategory(ServiceCategory category) {
        this.setCategory(category);
    }

    void changePrice(Money price) {
        this.setPrice(price);
    }

    void changePetSize(PetSize petSize) {
        this.setPetSize(petSize);
    }

    void changeServiceTime(ServiceTime time) {
        FieldValidator.requiresNonNull("time", time);
        this.setTime(time);
    }

    public ServiceId id() {
        return id;
    }

    public CompanyId companyId() {
        return companyId;
    }

    public ServiceCategory category() {
        return category;
    }

    public PetSize petSize() {
        return petSize;
    }

    public Money price() {
        return price;
    }

    public ServiceTime time() {
        return time;
    }

    private void setId(ServiceId id) {
        FieldValidator.requiresNonNull("serviceId", id);
        this.id = id;
    }

    private void setCompanyId(CompanyId companyId) {
        FieldValidator.requiresNonNull("companyId", companyId);
        this.companyId = companyId;
    }

    public void setCategory(ServiceCategory category) {
        FieldValidator.requiresNonNull("category", category);
        this.category = category;
    }

    public void setPetSize(PetSize petSize) {
        FieldValidator.requiresNonNull("petSize", petSize);
        this.petSize = petSize;
    }

    private void setPrice(Money price) {
        FieldValidator.requiresNonNull("price", price);
        this.price = price;
    }

    private void setTime(ServiceTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Service service)) return false;
        return Objects.equals(id, service.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
