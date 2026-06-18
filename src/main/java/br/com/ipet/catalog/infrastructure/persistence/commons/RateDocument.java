package br.com.ipet.catalog.infrastructure.persistence.commons;

import java.math.BigDecimal;

public record RateDocument(String petSize, BigDecimal price) {
}
