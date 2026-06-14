package br.com.ipet.catalog.domain.model.offering.stay;

import br.com.ipet.catalog.domain.model.offering.Rate;

import java.util.List;
import java.util.Set;

public interface Stay {
    CheckInOutTime checkInOutTime();
    Set<Rate> rates();
}
