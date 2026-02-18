package br.com.ipet.catalog.infrastructure.persistence.product;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductPersistenceEntity {

    private UUID id;

    private CompanyId company;

    private String name;
    private String description;
    private BigDecimal price;
    private Integer totalStock;
    private String category;
    private String subCategory;
    private boolean enabled;

}
