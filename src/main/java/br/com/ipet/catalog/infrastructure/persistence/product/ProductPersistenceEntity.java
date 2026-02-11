package br.com.ipet.catalog.infrastructure.persistence.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "product")
public class ProductPersistenceEntity {

    @Id
    private UUID id;

    @JoinColumn
    @ManyToOne(optional = false)
    private CompanyPersistenceEntity company;

    private String name;
    private String description;
    private BigDecimal price;
    private Integer totalStock;
    private String category;
    private String subCategory;
    private boolean enabled;

    public UUID getCompanyId() {
        return this.company.getId();
    }
}
