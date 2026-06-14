package br.com.ipet.catalog.domain.model.offering.appointment;

import br.com.ipet.catalog.domain.model.AggregateRoot;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.PetSize;
import br.com.ipet.catalog.domain.model.offering.ServiceCategory;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;
import br.com.ipet.catalog.domain.model.offering.ServiceOffering;
import br.com.ipet.catalog.domain.model.offering.Rate;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.UnsupportedServiceCategoryException;
import lombok.Builder;

public class Hygiene extends ServiceOffering
        implements Appointment, AggregateRoot<ServiceOffereingId> {

    private DurationTime duration;
    private Rate rate;

    @Builder(builderClassName = "CreateHygieneServiceBuilder", builderMethodName = "create")
    static Hygiene create(CompanyId companyId, ServiceType type, Species species,
                          DurationTime duration, Rate rate) {

        validateCategory(type);
        validateMaximumDuration(type, rate.size(), duration.value());

        return new Hygiene(new ServiceOffereingId(), companyId, type, species, duration, rate);
    }

    private static void validateCategory(ServiceType type) {
        if (type.category() != ServiceCategory.HYGIENE)
            throw new UnsupportedServiceCategoryException(type.category().name());
    }

    private static void validateMaximumDuration(ServiceType type, PetSize size, int duration) {
        int maxAllowed = 180;

        if (type == ServiceType.GROOMING_SCISSOR)
            maxAllowed = switch (size) {
                case GIANT -> 300;
                case LARGE -> 240;
                default -> 180;
            };

        if (duration > maxAllowed)
            throw new MaximumAppointmentDurationExceededException("Max " + maxAllowed + " min");
    }

    @Builder(builderClassName = "ExistingHygieneServiceBuilder", builderMethodName = "existing")
    private Hygiene(ServiceOffereingId id, CompanyId companyId, ServiceType type, Species species,
                    DurationTime duration, Rate rate) {
        super(id, companyId, type, species);
        this.setDuration(duration);
        this.setRate(rate);
    }

    @Override
    public Rate rate() {
        return rate;
    }

    private void setRate(Rate rate) {
        FieldValidator.requiresNonNull("rate", rate);
        this.rate = rate;
    }

    @Override
    public DurationTime duration() {
        return duration;
    }

    private void setDuration(DurationTime duration) {
        FieldValidator.requiresNonNull("duration", duration);
        this.duration = duration;
    }
}
