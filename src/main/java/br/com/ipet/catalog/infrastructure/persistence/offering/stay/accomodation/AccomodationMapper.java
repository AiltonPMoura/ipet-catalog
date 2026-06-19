package br.com.ipet.catalog.infrastructure.persistence.offering.stay.accomodation;

import br.com.ipet.catalog.domain.model.commons.valueobject.Money;
import br.com.ipet.catalog.domain.model.offering.PetSize;
import br.com.ipet.catalog.domain.model.offering.Rate;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.Accommodation;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.CheckInOutTime;
import br.com.ipet.catalog.infrastructure.persistence.commons.RateDocument;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AccomodationMapper {

    public Accommodation toDomain(AccomodationDocument accomodationDocument) {
        return Accommodation.existing()
                .id(new ServiceOffereingId(accomodationDocument.getId()))
                .type(ServiceType.valueOf(accomodationDocument.getType()))
                .species(Species.valueOf(accomodationDocument.getSpecies()))
                .checkInOutTime(new CheckInOutTime(accomodationDocument.getCheckIn(), accomodationDocument.getCheckOut()))
                .rates(this.toRates(accomodationDocument.getRates()))
                .registerAt(accomodationDocument.getRegisterAt())
                .build();
    }

    private Set<Rate> toRates(Set<RateDocument> rateDocuments) {
        return rateDocuments.stream().map(rateDocument -> new Rate(
                PetSize.valueOf(rateDocument.petSize()),
                new Money(rateDocument.price())
        )).collect(Collectors.toUnmodifiableSet());
    }

}
