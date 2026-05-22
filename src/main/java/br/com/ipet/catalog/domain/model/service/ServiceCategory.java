package br.com.ipet.catalog.domain.model.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.CAT_DAYCARE;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.CAT_HOSTING;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.CAT_RABIES_VACCINE;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_BATH;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_BATH_GROOMING_CLIPPERS;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_BATH_GROOMING_SCISSOR;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_DAYCARE;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_HOSTING;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_RABIES_VACCINE;

@RequiredArgsConstructor
@Getter
public enum ServiceCategory {
    HIGYENE("Hygiene", List.of(DOG_BATH, DOG_BATH_GROOMING_SCISSOR, DOG_BATH_GROOMING_CLIPPERS)),
    HEALTH("Health", List.of(DOG_RABIES_VACCINE, CAT_RABIES_VACCINE)),
    DAYCARE("Daycare", List.of(DOG_DAYCARE, CAT_DAYCARE)),
    HOSTING("Hosting", List.of(DOG_HOSTING, CAT_HOSTING));

    private final String description;
    private final List<ServiceSubCategory> subCategories;
}
