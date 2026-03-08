package br.com.ipet.catalog.domain.model.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.CAT_HIGIENE;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_HEALTH;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_HYGIENE;

@RequiredArgsConstructor
public enum ServiceCategory {
    HYGIENE("Higienização", List.of(DOG_HYGIENE, CAT_HIGIENE)),
    HEALTH("Saúde", List.of(DOG_HEALTH)),
    DAYCARE("Creche", List.of()),
    HOSTING("Hospedagem", List.of());

    private final String description;
    private final List<ServiceSubCategory> subCategories;

    public String description() {
        return description;
    }

    public List<ServiceSubCategory> subCategorie() {
        return subCategories;
    }
}
