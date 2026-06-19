package br.com.ipet.catalog.domain.model.offering.appointment.activity;

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
import br.com.ipet.catalog.domain.model.offering.appointment.Appointment;
import br.com.ipet.catalog.domain.model.offering.appointment.DurationTime;
import br.com.ipet.catalog.domain.model.offering.appointment.MaximumAppointmentDurationExceededException;
import lombok.Builder;

import java.time.OffsetDateTime;

public class Activity extends ServiceOffering
        implements Appointment, AggregateRoot<ServiceOffereingId> {

    private DurationTime duration;
    private Rate rate;

    @Builder(builderClassName = "CreateNewActivityServiceBuilder", builderMethodName = "createNew")
    static Activity create(CompanyId companyId, ServiceType type, Species species,
                           DurationTime duration, Rate rate) {

        validateCategory(type);
        validateMaximumDuration(type, duration.value());

        return new Activity(new ServiceOffereingId(), companyId, type, species, duration, rate, OffsetDateTime.now());
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
                     DurationTime duration, Rate rate, OffsetDateTime createdAt) {
        super(id, companyId, type, species, createdAt);
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
