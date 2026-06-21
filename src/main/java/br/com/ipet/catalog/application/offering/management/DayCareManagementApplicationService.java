package br.com.ipet.catalog.application.offering.management;

import br.com.ipet.catalog.application.commons.RateMapper;
import br.com.ipet.catalog.application.commons.StayInput;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.CheckInOut;
import br.com.ipet.catalog.domain.model.offering.stay.daycare.DayCares;
import br.com.ipet.catalog.domain.model.offering.stay.StayRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DayCareManagementApplicationService {

    private final StayRegistrationService stayRegistrationService;
    private final DayCares dayCares;
    private final RateMapper rateMapper;

    public UUID create(UUID companyId, StayInput input) {
        FieldValidator.requiresNonNull("companyId", companyId);
        FieldValidator.requiresNonNull("input", input);

        var dayCare = this.stayRegistrationService.registerDayCare(
                new CompanyId(companyId),
                ServiceType.valueOf(input.serviceType()),
                Species.valueOf(input.species()),
                new CheckInOut(input.checkin(), input.checkout()),
                rateMapper.toRates(input.rates())
        );

        dayCares.add(dayCare);

        return dayCare.id().value();
    }

    /*public void update(UUID serviceId, UUID companyId, ServiceUpdateInput input) {
        var service = dayCares.ofId(new ServiceOffereingId(serviceId))
                .orElseThrow(() -> new ServiceNotFoundException(serviceId.toString()));

        stayRegistrationService.change(service, new CompanyId(companyId),
                ServiceCategory.valueOf(input.getType()),
                PetSize.valueOf(input.getSize()),
                new Money(input.getPrice()),
                new DurationTime(input.getTime()));

        dayCares.add(service);
    }*/

}
