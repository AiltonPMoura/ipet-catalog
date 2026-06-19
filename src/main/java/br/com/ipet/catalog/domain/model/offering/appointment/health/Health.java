package br.com.ipet.catalog.domain.model.offering.appointment.health;

import br.com.ipet.catalog.domain.model.AggregateRoot;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.Rate;
import br.com.ipet.catalog.domain.model.offering.ServiceCategory;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;
import br.com.ipet.catalog.domain.model.offering.ServiceOffering;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.UnsupportedServiceCategoryException;
import br.com.ipet.catalog.domain.model.offering.appointment.Appointment;
import br.com.ipet.catalog.domain.model.offering.appointment.DurationTime;
import br.com.ipet.catalog.domain.model.offering.appointment.MaximumAppointmentDurationExceededException;
import lombok.Builder;

import java.time.OffsetDateTime;

public class Health extends ServiceOffering
        implements Appointment, AggregateRoot<ServiceOffereingId> {

    private DurationTime duration;
    private Rate rate;

    @Builder(builderClassName = "CreateNewHealthServiceBuilder", builderMethodName = "createNew")
    static Health create(CompanyId companyId, ServiceType type, Species species,
                         DurationTime duration, Rate rate) {

        validateCategory(type);
        validateMaximumDuration(duration.value());

        return new Health(new ServiceOffereingId(), companyId, type, species,  duration, rate, OffsetDateTime.now());
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
    private Health(ServiceOffereingId id, CompanyId companyId, ServiceType type, Species species,
                   DurationTime duration, Rate rate, OffsetDateTime registerAt) {
        super(id, companyId, type, species, registerAt);
        this.setRate(rate);
        this.setDuration(duration);
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
