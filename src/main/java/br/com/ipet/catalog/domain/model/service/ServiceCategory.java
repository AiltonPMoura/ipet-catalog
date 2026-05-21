package br.com.ipet.catalog.domain.model.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.CAT_HEALTH;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.CAT_HIGIENE;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_DAYCARE;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_HEALTH;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_HOSTING;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_HYGIENE;

@RequiredArgsConstructor
public enum ServiceCategory {
    DOG("Cachorro", List.of(DOG_HYGIENE, DOG_HEALTH, DOG_DAYCARE, DOG_HOSTING)),
    CAT("Gato", List.of(CAT_HIGIENE, CAT_HEALTH));

    private final String description;
    private final List<ServiceSubCategory> subCategories;

    public String description() {
        return description;
    }

    public List<ServiceSubCategory> subCategorie() {
        return subCategories;
    }
}
