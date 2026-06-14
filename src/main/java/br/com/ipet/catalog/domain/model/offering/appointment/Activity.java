package br.com.ipet.catalog.domain.model.offering.appointment;

import br.com.ipet.catalog.domain.model.AggregateRoot;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.ServiceCategory;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;
import br.com.ipet.catalog.domain.model.offering.ServiceOffering;
import br.com.ipet.catalog.domain.model.offering.Rate;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.UnsupportedServiceCategoryException;
import lombok.Builder;

public class Activity extends ServiceOffering
        implements Appointment, AggregateRoot<ServiceOffereingId> {

    private DurationTime duration;
    private Rate rate;

    @Builder(builderClassName = "CreateActivityServiceBuilder", builderMethodName = "create")
    static Activity create(CompanyId companyId, ServiceType type, Species species,
                           DurationTime duration, Rate rate) {

        validateCategory(type);
        validateMaximumDuration(type, duration.value());

        return new Activity(new ServiceOffereingId(), companyId, type, species, duration, rate);
    }

    private static void validateCategory(ServiceType type) {
        if (type.category() != ServiceCategory.ACTIVITY)
            throw new UnsupportedServiceCategoryException(type.category().name());
    }

    private static void validateMaximumDuration(ServiceType type, int duration) {
        int maxAllowed = 60;

        if (type == ServiceType.TRAINING || type == ServiceType.PLAY_SESSION)
            maxAllowed = 180;

        if (duration > maxAllowed)
            throw new MaximumAppointmentDurationExceededException("Max " + maxAllowed + " min");
    }

    @Builder(builderClassName = "ExistingActivityServiceBuilder", builderMethodName = "existing")
    private Activity(ServiceOffereingId id, CompanyId companyId, ServiceType type, Species species,
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

    public DurationTime duration() {
        return duration;
    }

    private void setDuration(DurationTime duration) {
        FieldValidator.requiresNonNull("duration", duration);
        this.duration = duration;
    }
}
