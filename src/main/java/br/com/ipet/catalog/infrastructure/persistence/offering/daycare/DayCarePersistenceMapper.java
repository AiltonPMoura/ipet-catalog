package br.com.ipet.catalog.infrastructure.persistence.offering.daycare;

import br.com.ipet.catalog.domain.model.offering.RateService;
import br.com.ipet.catalog.domain.model.offering.stay.daycare.DayCare;
import br.com.ipet.catalog.infrastructure.persistence.commons.RateDocument;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DayCarePersistenceMapper {

    public DayCareDocument fromDomain(DayCare dayCare) {
        return this.merge(new DayCareDocument(), dayCare);
    }

    public DayCareDocument merge(DayCareDocument dayCareDocument, DayCare dayCare) {
        dayCareDocument.setId(dayCare.id().value());
        dayCareDocument.setCompanyId(dayCare.companyId().value());
        dayCareDocument.setType(dayCare.type().name());
        dayCareDocument.setSpecies(dayCare.species().name());
        dayCareDocument.setRegisteredAt(dayCare.registeredAt());
        dayCareDocument.setCheckIn(dayCare.checkInOut().checkin());
        dayCareDocument.setCheckOut(dayCare.checkInOut().checkout());
        dayCareDocument.setRates(this.toRateDocuments(dayCare.rates()));
        return dayCareDocument;
    }

    private Set<RateDocument> toRateDocuments(Set<RateService> rates) {
        return rates.stream()
                .map(rate -> new RateDocument(
                        rate.size().name(),
                        rate.price().value())
                ).collect(Collectors.toUnmodifiableSet());
    }

}
