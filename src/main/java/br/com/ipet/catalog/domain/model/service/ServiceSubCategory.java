package br.com.ipet.catalog.domain.model.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import static br.com.ipet.catalog.domain.model.service.ServiceCategory.CAT;
import static br.com.ipet.catalog.domain.model.service.ServiceCategory.DOG;
import static br.com.ipet.catalog.domain.model.service.ServiceType.BATH;
import static br.com.ipet.catalog.domain.model.service.ServiceType.BATH_GROOMING_CLIPPERS;
import static br.com.ipet.catalog.domain.model.service.ServiceType.BATH_GROOMING_SCISSOR;
import static br.com.ipet.catalog.domain.model.service.ServiceType.DOG_RABIES_VACCINE;

@RequiredArgsConstructor
public enum ServiceSubCategory {
    DOG_HYGIENE("Higienização para cães", List.of(BATH, BATH_GROOMING_SCISSOR, BATH_GROOMING_CLIPPERS), DOG),
    DOG_HEALTH("Saúde para cães", List.of(DOG_RABIES_VACCINE), DOG),
    DOG_DAYCARE("Creche para cães", List.of(), DOG),
    DOG_HOSTING("Hospedagem para cães", List.of(), DOG),

    CAT_HYGIENE("Higienização para gatos", List.of(), CAT),
    CAR_HEALTH("Saúde para gatos", List.of(), CAT);

    private final String valueName;
    private final List<ServiceType> serviceTypes;
    private final ServiceCategory subCategorie;

    public List<ServiceType> serviceTypes() {
        return this.serviceTypes;
    }

    public ServiceCategory subCategorie() {
        return this.subCategorie;
    }
}
