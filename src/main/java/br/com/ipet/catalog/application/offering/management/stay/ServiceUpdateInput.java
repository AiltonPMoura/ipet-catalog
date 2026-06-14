package br.com.ipet.catalog.application.offering.management.stay;

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
public class ServiceUpdateInput {

    private String name;
    private String description;
    private String size;
    private String type;
    private BigDecimal price;
    private OffsetTime time;

}
