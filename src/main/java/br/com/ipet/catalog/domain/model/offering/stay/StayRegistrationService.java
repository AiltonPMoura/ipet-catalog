package br.com.ipet.catalog.domain.model.offering.stay;


import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.Rate;
import br.com.ipet.catalog.domain.model.offering.ServiceDoesNotBelongToTheCompany;
import br.com.ipet.catalog.domain.model.offering.ServiceOffering;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.appointment.Accomodations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StayRegistrationService {

    private final DayCares dayCares;
    private final Accomodations accomodations;

    public DayCare registerDayCare(CompanyId companyId, ServiceType serviceType, Species species,
                                   CheckInOutTime checkInOutTime, Set<Rate> rates) {

        this.verifyExistingStay(companyId);

        return DayCare.createNew()
                .companyId(companyId)
                .type(serviceType)
                .species(species)
                .checkInOutTime(checkInOutTime)
                .rates(rates)
                .build();
    }

    public Accommodation registerAccommodation(CompanyId companyId, ServiceType serviceType, Species species,
                                                 CheckInOutTime checkInOutTime, Set<Rate> rates) {

        this.verifyExistingStay(companyId);

        return Accommodation.createNew()
                .companyId(companyId)
                .type(serviceType)
                .species(species)
                .checkInOutTime(checkInOutTime)
                .rates(rates)
                .build();
    }

    private void verifyExistingStay(CompanyId companyId) {
        if (dayCares.existsOfCompany(companyId) || accomodations.existsOfCompany(companyId))
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
