package br.com.ipet.catalog.application.commons;

import br.com.ipet.catalog.domain.model.commons.valueobject.Money;
import br.com.ipet.catalog.domain.model.offering.PetSize;
import br.com.ipet.catalog.domain.model.offering.RateService;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RateMapper {
    public Set<RateService> toRates(Set<RateData> rates) {
        return rates.stream().map(this::toRate).collect(Collectors.toSet());
    }

    public RateService toRate(RateData rate) {
        return new RateService(PetSize.valueOf(rate.petSize()), new Money(rate.price()));
    }
}
