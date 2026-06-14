package br.com.ipet.catalog.application.offering.management.stay;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.util.Set;

public record StayInput (CompanyId companyId,
                         String species,
                         String serviceType,
                         LocalTime checkin,
                         LocalTime checkout,
                         Set<RateData> rates) {
}
