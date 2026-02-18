package br.com.ipet.catalog.infrastructure.persistence.service;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ServicePersistenceEntity {

    private UUID id;

    private CompanyId companyId;

    private String name;
    private String description;
    private BigDecimal price;

}
