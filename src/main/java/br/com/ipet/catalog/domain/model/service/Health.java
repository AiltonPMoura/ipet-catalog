package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.AggregateRoot;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.commons.valueobject.Money;
import lombok.Builder;

public class Health extends Service
        implements Appointment, AggregateRoot<ServiceId> {

    private PetSize petSize;
    private TimeSlot timeSlot;
    private Money price;

    @Builder(builderClassName = "CreateHealthServiceBuilder", builderMethodName = "create")
    private static Health create(CompanyId companyId, ServiceType type, Species species,
                                 PetSize petSize, Money price, TimeSlot timeSlot) {

        if (type.category() != ServiceCategory.HEALTH)
            throw new UnsupportedServiceCategoryException(type.category().name());

        if (timeSlot.duration().toMinutes() > 180)
            throw new ServiceTimeCannotBeGreaterThanThreeHoursException(String.valueOf(timeSlot.duration().toMinutes()));

        return new Health(new ServiceId(), companyId, type, species, petSize, price, timeSlot);
    }

    @Builder(builderClassName = "ExistingHealthServiceBuilder", builderMethodName = "existing")
    public Health(ServiceId id, CompanyId companyId, ServiceType type, Species species,
                  PetSize petSize, Money price, TimeSlot timeSlot) {
        super(id, companyId, type, species);
        this.setPetSize(petSize);
        this.setPrice(price);
        this.setTimeSlot(timeSlot);
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

    public TimeSlot duration() {
        return timeSlot;
    }

    private void setTimeSlot(TimeSlot timeSlot) {
        FieldValidator.requiresNonNull("duration", timeSlot);
        this.timeSlot = timeSlot;
    }
}
