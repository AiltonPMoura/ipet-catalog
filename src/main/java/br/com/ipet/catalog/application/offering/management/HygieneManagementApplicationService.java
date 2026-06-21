package br.com.ipet.catalog.application.offering.management;

import br.com.ipet.catalog.application.commons.AppointmentInput;
import br.com.ipet.catalog.application.commons.RateMapper;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.appointment.AppointmentRegistrationService;
import br.com.ipet.catalog.domain.model.offering.appointment.DurationTime;
import br.com.ipet.catalog.domain.model.offering.appointment.hygiene.Hygienes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class HygieneManagementApplicationService {

    private final AppointmentRegistrationService appointmentRegistrationService;
    private final Hygienes hygienes;
    private final RateMapper rateMapper;

     public UUID create(UUID companyId, AppointmentInput input) {
         var hygiene = this.appointmentRegistrationService.registerHygiene(
                 new CompanyId(companyId),
                 ServiceType.valueOf(input.serviceType()),
                 Species.valueOf(input.species()),
                 new DurationTime(input.duration()),
                 rateMapper.toRate(input.rate())
         );

         hygienes.add(hygiene);

         return hygiene.id().value();
     }

}
