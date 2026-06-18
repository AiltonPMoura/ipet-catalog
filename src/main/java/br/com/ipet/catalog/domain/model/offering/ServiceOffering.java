package br.com.ipet.catalog.domain.model.offering;

import br.com.ipet.catalog.domain.model.AbstractEventSourceEntity;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import org.springframework.data.annotation.Id;

import java.time.OffsetDateTime;
import java.util.Objects;

public abstract class ServiceOffering extends AbstractEventSourceEntity {

    private ServiceOffereingId id;
    private CompanyId companyId;
    private ServiceType type;
    private Species species;
    private OffsetDateTime registerAt;

    protected ServiceOffering(ServiceOffereingId id, CompanyId companyId,
                              ServiceType type, Species species, OffsetDateTime registerAt) {
        validateSpecies(type, species);

        this.setId(id);
        this.setCompanyId(companyId);
        this.setType(type);
        this.setSpecies(species);
        this.setRegisterAt(OffsetDateTime.now());
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

    public OffsetDateTime registerAt() {
        return registerAt;
    }

    private void setRegisterAt(OffsetDateTime registerAt) {
        FieldValidator.requiresNonNull("registerAt", registerAt);
        this.registerAt = registerAt;
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
