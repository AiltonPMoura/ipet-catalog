package br.com.ipet.catalog.domain.model.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum ServiceCategory {
    HIGYENE("Hygiene"),
    HEALTH("Health"),
    DAILY_CARE("Daily Care"),
    ACCOMMODATION("Accommodation");

    private final String description;
    private final List<ServiceType> serviceTypes = new ArrayList<>();
}
