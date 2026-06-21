package br.com.ipet.catalog.infrastructure.persistence.offering.activity;

import br.com.ipet.catalog.infrastructure.persistence.commons.RateDocument;
import br.com.ipet.catalog.infrastructure.persistence.offering.ServiceOfferingDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDocument extends ServiceOfferingDocument {

    private int duration;
    private RateDocument rate;

}
