package br.com.ipet.catalog.infrastructure.persistence.offering.health;

import br.com.ipet.catalog.domain.model.offering.appointment.health.Health;
import br.com.ipet.catalog.infrastructure.persistence.commons.RateDocument;
import org.springframework.stereotype.Component;

@Component
public class HealthPersistenceMapper {

    public HealthDocument fromDomain(Health health) {
        return this.merge(new HealthDocument(), health);
    }

    public HealthDocument merge(HealthDocument healthDocument, Health health) {
        healthDocument.setId(health.id().value());
        healthDocument.setCompanyId(health.companyId().value());
        healthDocument.setType(health.type().name());
        healthDocument.setSpecies(health.species().name());
        healthDocument.setRegisteredAt(health.registeredAt());
        healthDocument.setDuration(health.duration().value());
        healthDocument.setRate(new RateDocument(
                health.rate().size().name(),
                health.rate().price().value()
        ));

        return healthDocument;
    }

}
