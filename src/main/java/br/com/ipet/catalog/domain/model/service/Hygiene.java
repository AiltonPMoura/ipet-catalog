package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.AggregateRoot;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import lombok.Builder;

public class Hygiene extends Service
        implements Appointment, AggregateRoot<ServiceId> {

    private DurationTime duration;
    private ServiceRate rate;

    @Builder(builderClassName = "CreateHygieneServiceBuilder", builderMethodName = "create")
    static Hygiene create(CompanyId companyId, ServiceType type, Species species,
                          DurationTime duration, ServiceRate rate) {

        validateCategory(type);
        validateMaximumDuration(type, rate.size(), duration.value());

        return new Hygiene(new ServiceId(), companyId, type, species, duration, rate);
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
    private Hygiene(ServiceId id, CompanyId companyId, ServiceType type, Species species,
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

    @Override
    public DurationTime duration() {
        return duration;
    }

    private void setDuration(DurationTime duration) {
        FieldValidator.requiresNonNull("duration", duration);
        this.duration = duration;
    }
}
