package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.ordering.domain.model.valueobject.Money;
import br.com.ipet.ordering.domain.model.valueobject.OptionalServiceItemId;
import br.com.ipet.ordering.domain.model.valueobject.ServiceId;

public class OptionalServiceItems {
    private OptionalServiceItemId id;
    private ServiceId serviceId;
    private OptionalServiceItemName itemName;
    private Money price;

}
