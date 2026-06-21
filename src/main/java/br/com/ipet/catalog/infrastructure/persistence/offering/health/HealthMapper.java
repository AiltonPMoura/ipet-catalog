package br.com.ipet.catalog.infrastructure.persistence.offering.health;

import br.com.ipet.catalog.domain.model.commons.valueobject.Money;
import br.com.ipet.catalog.domain.model.offering.PetSize;
import br.com.ipet.catalog.domain.model.offering.RateService;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.appointment.DurationTime;
import br.com.ipet.catalog.domain.model.offering.appointment.health.Health;
import org.springframework.stereotype.Component;

@Component
public class HealthMapper {

    public Health toDomain(HealthDocument healthDocument) {
        return Health.existing()
                .id(new ServiceOffereingId(healthDocument.getId()))
                .type(ServiceType.valueOf(healthDocument.getType()))
                .species(Species.valueOf(healthDocument.getSpecies()))
                .registeredAt(healthDocument.getRegisteredAt())
                .duration(new DurationTime(healthDocument.getDuration()))
                .rate(new RateService(
                        PetSize.valueOf(healthDocument.getRate().petSize()),
                        new Money(healthDocument.getRate().price())
                ))
                .build();
    }

}
