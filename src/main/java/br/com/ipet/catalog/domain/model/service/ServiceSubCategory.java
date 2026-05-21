package br.com.ipet.catalog.domain.model.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import static br.com.ipet.catalog.domain.model.service.ServiceCategory.*;
import static br.com.ipet.catalog.domain.model.service.ServiceType.DOG_BATH_GROOMING_CLIPPERS;
import static br.com.ipet.catalog.domain.model.service.ServiceType.DOG_BATH_GROOMING_SCISSOR;
import static br.com.ipet.catalog.domain.model.service.ServiceType.CAT_BATH;
import static br.com.ipet.catalog.domain.model.service.ServiceType.DOG_BATH;
import static br.com.ipet.catalog.domain.model.service.ServiceType.DOG_RABIES_VACCINE;

@RequiredArgsConstructor
public enum ServiceSubCategory {
    DOG_HYGIENE("Higienização para Cães", List.of(DOG_BATH, DOG_BATH_GROOMING_SCISSOR, DOG_BATH_GROOMING_CLIPPERS), DOG),
    DOG_HEALTH("Saúde para Cães", List.of(DOG_RABIES_VACCINE), DOG),
    DOG_DAYCARE("Creche para cães", List.of(), DOG),
    DOG_HOSTING("Hospedagem para cães", List.of(), DOG),

    CAT_HIGIENE("Higienização para Gatos", List.of(CAT_BATH), CAT),
    CAT_HEALTH("Saúde para Cães", List.of(), DOG);

    private final String description;
    private final List<ServiceType> serviceTypes;
    private final ServiceCategory category;

    public String description() {
        return this.description;
    }
    public List<ServiceType> subCategories() {
        return this.serviceTypes;
    }
    public ServiceCategory category() {
        return  this.category;
    }
}
