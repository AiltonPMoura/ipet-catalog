package br.com.ipet.catalog.domain.model.service;

import lombok.RequiredArgsConstructor;

import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.CAT_HIGIENE;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_HEALTH;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_HYGIENE;

@RequiredArgsConstructor
public enum ServiceType {
    DOG_BATH("Banho", "Higienização completa", DOG_HYGIENE),
    DOG_BATH_GROOMING_SCISSOR("Banho e Tosa na tesoura", "Higienização completa e corte na tesoura", DOG_HYGIENE),
    DOG_BATH_GROOMING_CLIPPERS("Banho e Tosa com máquina", "Higienização completa e corte com máquina", DOG_HYGIENE),
    DOG_RABIES_VACCINE("Vacina da raiva", "Vacina da raiva para cães", DOG_HEALTH),

    CAT_BATH("Banho", "Higienização completa", CAT_HIGIENE);

    private final String valueName;
    private final String description;
    private final ServiceSubCategory subCategory;

    public String valueName() {
        return this.valueName;
    }

    public String description() {
        return this.description;
    }

    public ServiceSubCategory subCategory() {
        return this.subCategory;
    }

    public ServiceCategory category() {
        return subCategory.category();
    }

}
