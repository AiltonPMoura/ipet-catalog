package br.com.ipet.catalog.application.offering.management;

import br.com.ipet.catalog.application.commons.StayInput;
import br.com.ipet.catalog.application.commons.RateMapper;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.stay.StayRegistrationService;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.Accommodations;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.CheckInOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AccommodationManagementApplicationService {

    private final StayRegistrationService stayRegistrationService;
    private final Accommodations accommodations;
    private final RateMapper rateMapper;

    public UUID create(UUID companyId, StayInput input) {
        FieldValidator.requiresNonNull("companyId", companyId);
        FieldValidator.requiresNonNull("input", input);

        var accommodation = this.stayRegistrationService.registerAccommodation(
                new CompanyId(companyId),
                ServiceType.valueOf(input.serviceType()),
                Species.valueOf(input.species()),
                new CheckInOut(input.checkin(), input.checkout()),
                rateMapper.toRates(input.rates())
        );

        accommodations.add(accommodation);

        return accommodation.id().value();
    }



}
