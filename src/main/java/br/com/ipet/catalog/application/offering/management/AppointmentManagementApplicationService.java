package br.com.ipet.catalog.application.offering.management;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.commons.valueobject.Money;
import br.com.ipet.catalog.domain.model.offering.PetSize;
import br.com.ipet.catalog.domain.model.offering.RateService;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.appointment.AppointmentRegistrationService;
import br.com.ipet.catalog.domain.model.offering.appointment.DurationTime;
import br.com.ipet.catalog.domain.model.offering.appointment.activity.Activities;
import br.com.ipet.catalog.domain.model.offering.appointment.health.Healths;
import br.com.ipet.catalog.domain.model.offering.appointment.hygiene.Hygienes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentManagementApplicationService {

    private final AppointmentRegistrationService appointmentRegistrationService;
    private final Hygienes hygienes;
    private final Healths healths;
    private final Activities activities;

     public UUID createHealth(UUID companyId, AppointmentInput input) {
         var health = this.appointmentRegistrationService.registerHealth(
                 new CompanyId(companyId),
                 ServiceType.valueOf(input.serviceType()),
                 Species.valueOf(input.species()),
                 new DurationTime(input.duration()),
                 new RateService(PetSize.valueOf(input.rate().petSize()), new Money(input.rate().price()))
         );

         healths.add(health);

         return health.id().value();
     }

     public UUID createHygiene(UUID companyId, AppointmentInput input) {
         var hygiene = this.appointmentRegistrationService.registerHygiene(
                 new CompanyId(companyId),
                 ServiceType.valueOf(input.serviceType()),
                 Species.valueOf(input.species()),
                 new DurationTime(input.duration()),
                 new RateService(PetSize.valueOf(input.rate().petSize()), new Money(input.rate().price()))
         );

         hygienes.add(hygiene);

         return hygiene.id().value();
     }

    public UUID createrActivity(UUID companyId, AppointmentInput input) {
        var activity = this.appointmentRegistrationService.registerActivity(
                new CompanyId(companyId),
                ServiceType.valueOf(input.serviceType()),
                Species.valueOf(input.species()),
                new DurationTime(input.duration()),
                new RateService(PetSize.valueOf(input.rate().petSize()), new Money(input.rate().price()))
        );

        activities.add(activity);

        return activity.id().value();
    }

}
