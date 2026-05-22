package br.com.ipet.catalog.domain.model.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ServiceSubCategory {
    DOG_BATH("Higienização completa para Cães"),
    DOG_BATH_GROOMING_SCISSOR("Higienização completa para Cães e corte na tesoura"),
    DOG_BATH_GROOMING_CLIPPERS("Higienização completa para Cães e corte com máquina"),
    DOG_RABIES_VACCINE("Vácina contra raiva para Cães"),
    DOG_DAYCARE("Creche para cães"),
    DOG_HOSTING("Hospedagem para cães"),

    CAT_RABIES_VACCINE("Vácina contra raiva para Gatos"),
    CAT_DAYCARE("Creche para gatos"),
    CAT_HOSTING("Hospedagem para gatos");

    private final String description;
}
