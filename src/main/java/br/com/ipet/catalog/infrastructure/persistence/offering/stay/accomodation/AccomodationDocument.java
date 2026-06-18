package br.com.ipet.catalog.infrastructure.persistence.offering.stay.accomodation;

import br.com.ipet.catalog.infrastructure.persistence.commons.RateDocument;
import br.com.ipet.catalog.infrastructure.persistence.offering.stay.daycare.DayCareDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "accomodations")
public class AccomodationDocument
        extends AbstractAggregateRoot<AccomodationDocument> {

    @Id
    private UUID id;
    private UUID companyId;
    private String type;
    private String species;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private Set<RateDocument> rates = new HashSet<>();

    private OffsetDateTime registerAt;

    @CreatedBy
    private UUID createdByUserId;

    @LastModifiedBy
    private UUID lastModifiedByUserId;

    @LastModifiedDate
    private OffsetDateTime lastModifiedAt;

    public Collection<Object> getEvents() {
        return super.domainEvents();
    }

    public void addEvents(Collection<Object> events) {
        if (events != null)
            for (Object event : events)
                this.registerEvent(event);
    }

}
