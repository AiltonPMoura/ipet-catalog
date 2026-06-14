package br.com.ipet.catalog.application.offering.management.stay;

import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.Money;
import br.com.ipet.catalog.domain.model.offering.PetSize;
import br.com.ipet.catalog.domain.model.offering.Rate;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.appointment.Accomodations;
import br.com.ipet.catalog.domain.model.offering.stay.CheckInOutTime;
import br.com.ipet.catalog.domain.model.offering.stay.DayCares;
import br.com.ipet.catalog.domain.model.offering.stay.StayRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StayManagementApplicationService {

    private StayRegistrationService stayRegistrationService;
    private DayCares dayCares;
    private Accomodations accomodations;

    public UUID createDayCare(StayInput input) {
        FieldValidator.requiresNonNull("input", input);

        var dayCare = this.stayRegistrationService.registerDayCare(
                input.companyId(),
                ServiceType.valueOf(input.serviceType()),
                Species.valueOf(input.species()),
                new CheckInOutTime(input.checkin(), input.checkout()),
                toRates(input.rates())
        );

        dayCares.add(dayCare);

        return dayCare.id().value();
    }

    private Set<Rate> toRates(Set<RateData> rates) {
        return rates.stream()
                .map(rate -> new Rate(PetSize.valueOf(rate.petSize()), new Money(rate.price())))
                .collect(Collectors.toSet());
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
