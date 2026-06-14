package br.com.ipet.catalog.infrastructure.persistence.offering.stay;

import java.math.BigDecimal;

public record PriceDocument(String petSize, BigDecimal amount) {
}
