package br.com.ipet.catalog.infrastructure.persistence.offering.stay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "daycares")
public class DayCareDocument {

    @Id
    private UUID id;
    private UUID companyId;
    private String type;
    private String species;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private Set<PriceDocument> prices;

}
