package br.com.ipet.catalog.infrastructure.persistence.offering.accommodation;

import br.com.ipet.catalog.infrastructure.persistence.commons.RateDocument;
import br.com.ipet.catalog.infrastructure.persistence.offering.ServiceOfferingDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("ACCOMMODATION")
public class AccommodationDocument extends ServiceOfferingDocument {

    private LocalTime checkIn;
    private LocalTime checkOut;
    private Set<RateDocument> rates = new HashSet<>();

}
