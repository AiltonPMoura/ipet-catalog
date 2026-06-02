package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.AbstractEventSourceEntity;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;

import java.util.Objects;

public abstract class Service extends AbstractEventSourceEntity {
    private ServiceId id;
    private CompanyId companyId;
    private ServiceCategory category;

    protected Service(ServiceId id, CompanyId companyId, ServiceCategory category) {
        this.setId(id);
        this.setCompanyId(companyId);
        this.setCategory(category);
    }

    public ServiceId id() {
        return id;
    }

    private void setId(ServiceId id) {
        FieldValidator.requiresNonNull("serviceId", id);
        this.id = id;
    }

    public CompanyId companyId() {
        return companyId;
    }

    private void setCompanyId(CompanyId companyId) {
        FieldValidator.requiresNonNull("companyId", companyId);
        this.companyId = companyId;
    }

    public ServiceCategory category() {
        return category;
    }

    private void setCategory(ServiceCategory category) {
        FieldValidator.requiresNonNull("category", category);
        this.category = category;
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
