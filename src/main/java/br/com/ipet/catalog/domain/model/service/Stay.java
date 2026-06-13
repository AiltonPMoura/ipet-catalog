package br.com.ipet.catalog.domain.model.service;

import java.util.List;

public interface Stay {
    CheckInOutTime checkInOutTime();
    List<ServiceRate> rates();
}
