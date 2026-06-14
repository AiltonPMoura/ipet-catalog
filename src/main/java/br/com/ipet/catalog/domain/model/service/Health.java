package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.AggregateRoot;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import lombok.Builder;

public class Health extends Service
        implements Appointment, AggregateRoot<ServiceId> {

    private DurationTime duration;
    private ServiceRate rate;

    @Builder(builderClassName = "CreateHealthServiceBuilder", builderMethodName = "create")
    static Health create(CompanyId companyId, ServiceType type, Species species,
                                 DurationTime duration, ServiceRate rate) {

        validateCategory(type);
        validateMaximumDuration(duration.value());

        return new Health(new ServiceId(), companyId, type, species,  duration, rate);
    }

    private static void validateCategory(ServiceType type) {
        if (type.category() != ServiceCategory.HEALTH)
            throw new UnsupportedServiceCategoryException(type.category().name());
    }

    private static void validateMaximumDuration(int duration) {
        if (duration > 120)
            throw new MaximumAppointmentDurationExceededException("Max 120 min");
    }

    @Builder(builderClassName = "ExistingHealthServiceBuilder", builderMethodName = "existing")
    private Health(ServiceId id, CompanyId companyId, ServiceType type, Species species,
                   DurationTime duration, ServiceRate rate) {
        super(id, companyId, type, species);
        this.setRate(rate);
        this.setDuration(duration);
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
