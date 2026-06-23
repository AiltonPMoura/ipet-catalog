package br.com.ipet.catalog.infrastructure.persistence.offering.daycare;

import br.com.ipet.catalog.domain.model.commons.valueobject.Money;
import br.com.ipet.catalog.domain.model.offering.PetSize;
import br.com.ipet.catalog.domain.model.offering.RateService;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.CheckInOut;
import br.com.ipet.catalog.domain.model.offering.stay.daycare.DayCare;
import br.com.ipet.catalog.infrastructure.persistence.commons.RateDocument;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DayCareMapper {

    public DayCare toDomain(DayCareDocument dayCareDocument) {
        return DayCare.existing()
                .id(new ServiceOffereingId(dayCareDocument.getId()))
                .type(ServiceType.valueOf(dayCareDocument.getType()))
                .species(Species.valueOf(dayCareDocument.getSpecies()))
                .registeredAt(dayCareDocument.getRegisteredAt())
                .checkInOut(new CheckInOut(dayCareDocument.getCheckin(), dayCareDocument.getCheckout()))
                .rates(this.toRates(dayCareDocument.getRates()))
                .build();
    }

    private Set<RateService> toRates(Set<RateDocument> rateDocuments) {
        return rateDocuments.stream().map(rateDocument -> new RateService(
                PetSize.valueOf(rateDocument.petSize()),
                new Money(rateDocument.price())
        )).collect(Collectors.toUnmodifiableSet());
    }

}
