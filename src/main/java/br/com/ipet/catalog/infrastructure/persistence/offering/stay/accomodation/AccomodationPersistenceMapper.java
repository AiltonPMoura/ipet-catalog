package br.com.ipet.catalog.infrastructure.persistence.offering.stay.accomodation;

import br.com.ipet.catalog.domain.model.offering.Rate;
import br.com.ipet.catalog.domain.model.offering.stay.Accommodation;
import br.com.ipet.catalog.infrastructure.persistence.commons.RateDocument;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AccomodationPersistenceMapper {

    public AccomodationDocument fromDomain(Accommodation accommodation) {
        return this.merge(new AccomodationDocument(), accommodation);
    }

    public AccomodationDocument merge(AccomodationDocument accomodationDocument, Accommodation accommodation) {
        accomodationDocument.setId(accommodation.id().value());
        accomodationDocument.setCompanyId(accommodation.companyId().value());
        accomodationDocument.setType(accommodation.type().name());
        accomodationDocument.setSpecies(accommodation.species().name());
        accomodationDocument.setCheckIn(accommodation.checkInOutTime().checkInTime());
        accomodationDocument.setCheckOut(accommodation.checkInOutTime().checkOutTime());
        accomodationDocument.setRates(this.toRateDocuments(accommodation.rates()));
        accomodationDocument.setRegisterAt(accommodation.registerAt());
        return accomodationDocument;
    }

    private Set<RateDocument> toRateDocuments(Set<Rate> rates) {
        return rates.stream()
                .map(rate -> new RateDocument(
                        rate.size().name(),
                        rate.price().value())
                ).collect(Collectors.toUnmodifiableSet());
    }

}
