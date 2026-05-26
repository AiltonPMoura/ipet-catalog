package br.com.ipet.catalog.domain.model.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public enum ServiceSubCategory {
    DOG("Cães", List.of(
            ServiceType.BATH,
            ServiceType.GROOMING_SCISSOR,
            ServiceType.GROOMING_CLIPPERS,
            ServiceType.RABIES_VACCINE,
            ServiceType.DAYCARE,
            ServiceType.HOSTING_SIMPLE,
            ServiceType.HOSTING_PREMIUM
    )),
    CAT("Gatos", List.of(
            ServiceType.BATH,
            ServiceType.RABIES_VACCINE,
            ServiceType.HOSTING_SIMPLE
    ));


    private final String description;
    private final List<ServiceType> serviceTypes;
}
