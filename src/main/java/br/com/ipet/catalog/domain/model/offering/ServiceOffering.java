package br.com.ipet.catalog.domain.model.offering;

import br.com.ipet.catalog.domain.model.AbstractEventSourceEntity;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;

import java.time.OffsetDateTime;
import java.util.Objects;

public abstract class ServiceOffering extends AbstractEventSourceEntity {

    private ServiceOffereingId id;
    private CompanyId companyId;
    private ServiceType type;
    private Species species;
    private OffsetDateTime registeredAt;

    protected ServiceOffering(ServiceOffereingId id, CompanyId companyId,
                              ServiceType type, Species species, OffsetDateTime registeredAt) {
        validateSpecies(type, species);

        this.setId(id);
        this.setCompanyId(companyId);
        this.setType(type);
        this.setSpecies(species);
        this.setRegisteredAt(registeredAt);
    }

    private static void validateSpecies(ServiceType type, Species species) {
        if (type.doesNotSupportSpecies(species))
            throw new ServiceTypeDoesNotSupportSpeciesException(type.name(), species.name());
    }

    public ServiceOffereingId id() {
        return id;
    }

    private void setId(ServiceOffereingId id) {
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

    public OffsetDateTime registeredAt() {
        return registeredAt;
    }

    private void setRegisteredAt(OffsetDateTime registeredAt) {
        FieldValidator.requiresNonNull("registeredAt", registeredAt);
        this.registeredAt = registeredAt;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ServiceOffering service)) return false;
        return Objects.equals(id, service.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
