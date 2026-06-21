package br.com.ipet.catalog.infrastructure.persistence.offering.activity;

import br.com.ipet.catalog.domain.model.commons.valueobject.Money;
import br.com.ipet.catalog.domain.model.offering.PetSize;
import br.com.ipet.catalog.domain.model.offering.RateService;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.appointment.DurationTime;
import br.com.ipet.catalog.domain.model.offering.appointment.activity.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {

    public Activity toDomain(ActivityDocument activityDocument) {
        return Activity.existing()
                .id(new ServiceOffereingId(activityDocument.getId()))
                .type(ServiceType.valueOf(activityDocument.getType()))
                .species(Species.valueOf(activityDocument.getSpecies()))
                .registeredAt(activityDocument.getRegisteredAt())
                .duration(new DurationTime(activityDocument.getDuration()))
                .rate(new RateService(
                        PetSize.valueOf(activityDocument.getRate().petSize()),
                        new Money(activityDocument.getRate().price())
                ))
                .build();
    }

}
