package br.com.ipet.catalog.application.service.management;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInput {

    private CompanyId companyId;
    private String name;
    private String description;
    private String size;
    private String type;
    private BigDecimal price;
    private OffsetTime time;

}
