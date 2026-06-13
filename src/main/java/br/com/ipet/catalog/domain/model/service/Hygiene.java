package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.AggregateRoot;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import lombok.Builder;

public class Hygiene extends Service
        implements Appointment, AggregateRoot<ServiceId> {

    private DurationTime durationTime;
    private ServiceRate rate;

    static Hygiene create(CompanyId companyId, ServiceType type, Species species,
                          DurationTime durationTime, ServiceRate rate) {

        validateCategory(type);
        validateMaximumDuration(type, rate.size(), durationTime.duration());

        return new Hygiene(new ServiceId(), companyId, type, species, durationTime, rate);
    }

    private static void validateMaximumDuration(ServiceType type, PetSize size, int duration) {
        if (size == PetSize.GIANT && type == ServiceType.GROOMING_SCISSOR) {
            if (duration > 300) throw new MaximumAppointmentDurationExceededException("Max 300 min");
        } else if (size == PetSize.LARGE && type == ServiceType.GROOMING_SCISSOR) {
            if (duration > 240) throw new MaximumAppointmentDurationExceededException("Max 240 min");
        } else if (duration > 180) {
            throw new MaximumAppointmentDurationExceededException("Max 180 min");
        }
    }

    private static void validateCategory(ServiceType type) {
        if (type.category() != ServiceCategory.HYGIENE)
            throw new UnsupportedServiceCategoryException(type.category().name());
    }

    @Builder(builderClassName = "ExistingHygieneServiceBuilder", builderMethodName = "existing")
    private Hygiene(ServiceId id, CompanyId companyId, ServiceType type, Species species,
                   DurationTime durationTime, ServiceRate rate) {
        super(id, companyId, type, species);
        this.setDurationTime(durationTime);
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
    public DurationTime durationTime() {
        return durationTime;
    }

    private void setDurationTime(DurationTime durationTime) {
        FieldValidator.requiresNonNull("durationTime", durationTime);
        this.durationTime = durationTime;
    }
}
