package br.com.ipet.catalog.application.commons;

import java.math.BigDecimal;

public record RateData(String petSize,
                       BigDecimal price) {
}
