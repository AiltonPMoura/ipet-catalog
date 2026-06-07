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
    DAYCARE("Daycare"),
    ACCOMMODATION("Accommodation"),
    ACTIVITY("Activity");

    private final String description;
    private final List<ServiceType> serviceTypes = new ArrayList<>();
}
