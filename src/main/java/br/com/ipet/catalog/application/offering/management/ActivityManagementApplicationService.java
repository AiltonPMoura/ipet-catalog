package br.com.ipet.catalog.application.offering.management;

import br.com.ipet.catalog.application.commons.AppointmentInput;
import br.com.ipet.catalog.application.commons.RateMapper;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.appointment.AppointmentRegistrationService;
import br.com.ipet.catalog.domain.model.offering.appointment.DurationTime;
import br.com.ipet.catalog.domain.model.offering.appointment.activity.Activities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ActivityManagementApplicationService {

    private final AppointmentRegistrationService appointmentRegistrationService;
    private final Activities activities;
    private final RateMapper rateMapper;

    public UUID create(UUID companyId, AppointmentInput input) {
        var activity = this.appointmentRegistrationService.registerActivity(
                new CompanyId(companyId),
                ServiceType.valueOf(input.serviceType()),
                Species.valueOf(input.species()),
                new DurationTime(input.duration()),
                rateMapper.toRate(input.rate())
        );

        activities.add(activity);

        return activity.id().value();
    }

}
