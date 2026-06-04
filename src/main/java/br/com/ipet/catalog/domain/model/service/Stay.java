package br.com.ipet.catalog.domain.model.service;

import java.util.List;

public interface Stay {
    CheckInOut checkInOut();
    List<ServiceRate> rates();
}
