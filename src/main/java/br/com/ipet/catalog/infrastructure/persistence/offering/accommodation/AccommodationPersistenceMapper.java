package br.com.ipet.catalog.infrastructure.persistence.offering.accommodation;

import br.com.ipet.catalog.domain.model.offering.RateService;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.Accommodation;
import br.com.ipet.catalog.infrastructure.persistence.commons.RateDocument;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AccommodationPersistenceMapper {

    public AccommodationDocument fromDomain(Accommodation accommodation) {
        return this.merge(new AccommodationDocument(), accommodation);
    }

    public AccommodationDocument merge(AccommodationDocument accommodationDocument, Accommodation accommodation) {
        accommodationDocument.setId(accommodation.id().value());
        accommodationDocument.setCompanyId(accommodation.companyId().value());
        accommodationDocument.setType(accommodation.type().name());
        accommodationDocument.setSpecies(accommodation.species().name());
        accommodationDocument.setRegisterAt(accommodation.registerAt());
        accommodationDocument.setCheckIn(accommodation.checkInOutTime().checkInTime());
        accommodationDocument.setCheckOut(accommodation.checkInOutTime().checkOutTime());
        accommodationDocument.setRates(this.toRateDocuments(accommodation.rates()));
        return accommodationDocument;
    }

    private Set<RateDocument> toRateDocuments(Set<RateService> rates) {
        return rates.stream().map(rate -> new RateDocument(
                rate.size().name(),
                rate.price().value())
        ).collect(Collectors.toSet());
    }

}
