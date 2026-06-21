package br.com.ipet.catalog.infrastructure.persistence.offering.hygiene;

import br.com.ipet.catalog.domain.model.commons.valueobject.Money;
import br.com.ipet.catalog.domain.model.offering.PetSize;
import br.com.ipet.catalog.domain.model.offering.RateService;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.appointment.DurationTime;
import br.com.ipet.catalog.domain.model.offering.appointment.hygiene.Hygiene;
import org.springframework.stereotype.Component;

@Component
public class HygieneMapper {

    public Hygiene toDomain(HygieneDocument hygieneDocument) {
        return Hygiene.existing()
                .id(new ServiceOffereingId(hygieneDocument.getId()))
                .type(ServiceType.valueOf(hygieneDocument.getType()))
                .species(Species.valueOf(hygieneDocument.getSpecies()))
                .registeredAt(hygieneDocument.getRegisteredAt())
                .duration(new DurationTime(hygieneDocument.getDuration()))
                .rate(new RateService(
                        PetSize.valueOf(hygieneDocument.getRate().petSize()),
                        new Money(hygieneDocument.getRate().price())
                ))
                .build();
    }

}
