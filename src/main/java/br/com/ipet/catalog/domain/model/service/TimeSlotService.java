package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.AggregateRoot;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.commons.valueobject.Money;
import lombok.Builder;

import java.util.Set;

public class TimeSlotService
        extends Service
        implements AggregateRoot<ServiceId> {

    private PetSize petSize;
    private TimeSlotDuration duration;
    private Money price;

    private static final Set<ServiceCategory> SUPPORTS_CATEGORIES = Set.of(
            ServiceCategory.HIGYENE, ServiceCategory.HEALTH, ServiceCategory.DAYCARE);

    @Builder(builderClassName = "CreateTimeSlotServiceBuilder", builderMethodName = "create")
    private static TimeSlotService create(CompanyId companyId, ServiceCategory category,
                                     PetSize petSize, Money price, TimeSlotDuration duration) {
        if (!SUPPORTS_CATEGORIES.contains(category))
            throw new UnsupportedServiceCategoryException(category.getDescription());

        return new TimeSlotService(new ServiceId(), companyId, category, petSize, price, duration);
    }

    @Builder(builderClassName = "ExistingTimeSlotServiceBuilder", builderMethodName = "existing")
    public TimeSlotService(ServiceId id, CompanyId companyId, ServiceCategory category,
                                   PetSize petSize, Money price, TimeSlotDuration duration) {
        super(id, companyId, category);
        this.setPetSize(petSize);
        this.setPrice(price);
        this.setDuration(duration);
    }

    void changePrice(Money price) {
        this.setPrice(price);
    }

    void changeDuration(TimeSlotDuration duration) {
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

    public TimeSlotDuration duration() {
        return duration;
    }

    private void setDuration(TimeSlotDuration duration) {
        FieldValidator.requiresNonNull("duration", duration);
        this.duration = duration;
    }
}
