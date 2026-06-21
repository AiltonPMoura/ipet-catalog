package br.com.ipet.catalog.domain.model.offering.stay;


import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.RateService;
import br.com.ipet.catalog.domain.model.offering.ServiceDoesNotBelongToTheCompany;
import br.com.ipet.catalog.domain.model.offering.ServiceOffering;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.Accommodations;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.Accommodation;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.CheckInOut;
import br.com.ipet.catalog.domain.model.offering.stay.daycare.DayCare;
import br.com.ipet.catalog.domain.model.offering.stay.daycare.DayCares;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class StayRegistrationService {

    private final DayCares dayCares;
    private final Accommodations accommodations;

    public DayCare registerDayCare(CompanyId companyId, ServiceType serviceType, Species species,
                                   CheckInOut checkInOut, Set<RateService> rates) {

        this.verifyExistingStay(companyId);

        return DayCare.createNew()
                .companyId(companyId)
                .type(serviceType)
                .species(species)
                .checkInOut(checkInOut)
                .rates(rates)
                .build();
    }

    public Accommodation registerAccommodation(CompanyId companyId, ServiceType serviceType, Species species,
                                               CheckInOut checkInOut, Set<RateService> rates) {

        this.verifyExistingStay(companyId);

        return Accommodation.createNew()
                .companyId(companyId)
                .type(serviceType)
                .species(species)
                .checkInOut(checkInOut)
                .rates(rates)
                .build();
    }

    private void verifyExistingStay(CompanyId companyId) {
        if (dayCares.existsOfCompany(companyId) || accommodations.existsOfCompany(companyId))
            throw new StayAlreadyException("Empresa já possui um serviço de estadia cadastrado");
    }

    private void verifyIfBelongsToTheCompany(CompanyId companyId, ServiceOffering service) {
        var doesNottBelongsToTheCustomer = dayCares.ofCompany(companyId)
                .stream()
                .noneMatch(service::equals);

        if (doesNottBelongsToTheCustomer)
            throw new ServiceDoesNotBelongToTheCompany();
    }

}
