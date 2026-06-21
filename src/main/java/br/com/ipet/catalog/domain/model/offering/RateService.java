package br.com.ipet.catalog.domain.model.offering;

import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.Money;
import java.math.BigDecimal;

public record RateService(PetSize size, Money price) {

    private static final BigDecimal MAX_PRICE = new BigDecimal("300.00");
    
    public RateService {
        FieldValidator.requiresNonNull("size", size);
        FieldValidator.requiresNonNull("price", price);

        if (price.value().compareTo(MAX_PRICE) > 0)
            throw new InvalidServicePriceException(price.value());
    }
    
}
