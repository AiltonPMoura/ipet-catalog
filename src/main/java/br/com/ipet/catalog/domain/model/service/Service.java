package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.AbstractEventSourceEntity;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;

import java.util.Objects;

public abstract class Service extends AbstractEventSourceEntity {
    private ServiceId id;
    private CompanyId companyId;
    private ServiceType type;
    private Species species;

    protected Service(ServiceId id, CompanyId companyId, ServiceType type, Species species) {
        validateSpecies(type, species);

        this.setId(id);
        this.setCompanyId(companyId);
        this.setType(type);
        this.setSpecies(species);
    }

    private static void validateSpecies(ServiceType type, Species species) {
        if (type.doesNotSupportSpecies(species))
            throw new ServiceTypeDoesNotSupportSpeciesException(type.name(), species.name());
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

    public ServiceType type() {
        return type;
    }

    private void setType(ServiceType type) {
        FieldValidator.requiresNonNull("type", type);
        this.type = type;
    }

    public Species species() {
        return species;
    }

    private void setSpecies(Species species) {
        FieldValidator.requiresNonNull("species", species);
        this.species = species;
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
