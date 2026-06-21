package br.com.ipet.catalog.infrastructure.persistence.offering.accommodation;

import br.com.ipet.catalog.domain.model.commons.valueobject.Money;
import br.com.ipet.catalog.domain.model.offering.PetSize;
import br.com.ipet.catalog.domain.model.offering.RateService;
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
public class AccommodationMapper {

    public Accommodation toDomain(AccommodationDocument accommodationDocument) {
        return Accommodation.existing()
                .id(new ServiceOffereingId(accommodationDocument.getId()))
                .type(ServiceType.valueOf(accommodationDocument.getType()))
                .species(Species.valueOf(accommodationDocument.getSpecies()))
                .registerAt(accommodationDocument.getRegisterAt())
                .checkInOutTime(new CheckInOutTime(accommodationDocument.getCheckIn(), accommodationDocument.getCheckOut()))
                .rates(this.toRates(accommodationDocument.getRates()))
                .build();
    }

    private Set<RateService> toRates(Set<RateDocument> rateDocuments) {
        return rateDocuments.stream().map(rateDocument -> new RateService(
                PetSize.valueOf(rateDocument.petSize()),
                new Money(rateDocument.price())
        )).collect(Collectors.toUnmodifiableSet());
    }

}
