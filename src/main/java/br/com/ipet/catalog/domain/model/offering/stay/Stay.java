package br.com.ipet.catalog.domain.model.offering.stay;

import br.com.ipet.catalog.domain.model.offering.RateService;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.CheckInOut;

import java.util.Set;

public interface Stay {
    CheckInOut checkInOut();
    Set<RateService> rates();
}
