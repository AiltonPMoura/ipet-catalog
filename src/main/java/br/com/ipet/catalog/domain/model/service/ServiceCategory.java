package br.com.ipet.catalog.domain.model.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.CAT;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG;

@RequiredArgsConstructor
@Getter
public enum ServiceCategory {
    HIGYENE("Hygiene", List.of(DOG, CAT)),
    HEALTH("Health", List.of(DOG, CAT)),
    DAYCARE("Daycare", List.of(DOG)),
    HOSTING("Hosting", List.of(DOG, CAT));

    private final String description;
    private final List<ServiceSubCategory> subCategories;
}
