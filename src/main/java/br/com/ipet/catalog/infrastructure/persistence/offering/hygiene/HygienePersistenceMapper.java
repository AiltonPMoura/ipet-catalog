package br.com.ipet.catalog.infrastructure.persistence.offering.hygiene;

import br.com.ipet.catalog.domain.model.offering.appointment.hygiene.Hygiene;
import br.com.ipet.catalog.infrastructure.persistence.commons.RateDocument;
import org.springframework.stereotype.Component;

@Component
public class HygienePersistenceMapper {

    public HygieneDocument fromDomain(Hygiene hygiene) {
        return this.merge(new HygieneDocument(), hygiene);
    }

    public HygieneDocument merge(HygieneDocument hygieneDocument, Hygiene hygiene) {
        hygieneDocument.setId(hygiene.id().value());
        hygieneDocument.setCompanyId(hygiene.companyId().value());
        hygieneDocument.setType(hygiene.type().name());
        hygieneDocument.setSpecies(hygiene.species().name());
        hygieneDocument.setRegisteredAt(hygiene.registeredAt());
        hygieneDocument.setDuration(hygiene.duration().value());
        hygieneDocument.setRate(new RateDocument(
                hygiene.rate().size().name(),
                hygiene.rate().price().value()
        ));

        return hygieneDocument;
    }

}
