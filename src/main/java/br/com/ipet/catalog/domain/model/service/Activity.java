package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.AggregateRoot;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import lombok.Builder;

public class Activity extends Service
        implements Appointment, AggregateRoot<ServiceId> {

    private DurationTime duration;
    private ServiceRate rate;

    @Builder(builderClassName = "CreateActivityServiceBuilder", builderMethodName = "create")
    static Activity create(CompanyId companyId, ServiceType type, Species species,
                           DurationTime duration, ServiceRate rate) {

        validateCategory(type);
        validateMaximumDuration(type, duration.value());

        return new Activity(new ServiceId(), companyId, type, species, duration, rate);
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
    private Activity(ServiceId id, CompanyId companyId, ServiceType type, Species species,
                    DurationTime duration, ServiceRate rate) {
        super(id, companyId, type, species);
        this.setDuration(duration);
        this.setRate(rate);
    }

    @Override
    public ServiceRate rate() {
        return rate;
    }

    private void setRate(ServiceRate rate) {
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
