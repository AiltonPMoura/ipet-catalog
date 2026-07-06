package br.com.ipet.catalog.application.product.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOutput {
    private UUID id;
    private UUID companyId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private String subcategory;
}
