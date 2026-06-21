package br.com.ipet.catalog.infrastructure.persistence.offering.activity;

import br.com.ipet.catalog.domain.model.offering.appointment.activity.Activity;
import br.com.ipet.catalog.infrastructure.persistence.commons.RateDocument;
import org.springframework.stereotype.Component;

@Component
public class ActivityPersistenceMapper {

    public ActivityDocument fromDomain(Activity activity) {
        return this.merge(new ActivityDocument(), activity);
    }

    public ActivityDocument merge(ActivityDocument activityDocument, Activity activity) {
        activityDocument.setId(activity.id().value());
        activityDocument.setCompanyId(activity.companyId().value());
        activityDocument.setType(activity.type().name());
        activityDocument.setSpecies(activity.species().name());
        activityDocument.setRegisteredAt(activity.registeredAt());
        activityDocument.setDuration(activity.duration().value());
        activityDocument.setRate(new RateDocument(
                activity.rate().size().name(),
                activity.rate().price().value()
        ));

        return activityDocument;
    }

}
