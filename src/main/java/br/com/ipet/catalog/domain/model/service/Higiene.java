package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.AggregateRoot;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.commons.valueobject.Money;
import lombok.Builder;

import static br.com.ipet.catalog.domain.model.service.ServiceType.BATH;
import static br.com.ipet.catalog.domain.model.service.ServiceType.GROOMING_CLIPPERS;
import static br.com.ipet.catalog.domain.model.service.ServiceType.GROOMING_SCISSOR;

public class Higiene extends Service
        implements Appointment, AggregateRoot<ServiceId> {

    private PetSize petSize;
    private Duration duration;
    private Money price;

    @Builder(builderClassName = "CreateHigieneServiceBuilder", builderMethodName = "create")
    private static Higiene create(CompanyId companyId, ServiceType type, Species species,
                                  PetSize petSize, Money price, Duration duration) {

        if (type != BATH && type != GROOMING_CLIPPERS && type != GROOMING_SCISSOR)
            throw new UnsupportedServiceCategoryException(type.name());

        if (duration.minutes() > 120)
            throw new ServiceTimeCannotBeGreaterThanTwoHoursException(String.valueOf(duration.minutes()));

        return new Higiene(new ServiceId(), companyId, type, species, petSize, price, duration);
    }

    @Builder(builderClassName = "ExistingTimeSlotServiceBuilder", builderMethodName = "existing")
    public Higiene(ServiceId id, CompanyId companyId, ServiceType type, Species species,
                   PetSize petSize, Money price, Duration duration) {
        super(id, companyId, type, species);
        this.setPetSize(petSize);
        this.setPrice(price);
        this.setDuration(duration);
    }

    public PetSize petSize() {
        return petSize;
    }

    private void setPetSize(PetSize petSize) {
        FieldValidator.requiresNonNull("petSize", petSize);
        this.petSize = petSize;
    }

    public Money price() {
        return price;
    }

    private void setPrice(Money price) {
        FieldValidator.requiresNonNull("price", price);
        this.price = price;
    }

    public Duration duration() {
        return duration;
    }

    private void setDuration(Duration duration) {
        FieldValidator.requiresNonNull("duration", duration);
        this.duration = duration;
    }
}
