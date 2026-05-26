package br.com.ipet.catalog.domain.model.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ServiceType {
    BATH("Higienização completa"),
    GROOMING_SCISSOR("Higienização completa e corte na tesoura"),
    GROOMING_CLIPPERS("Higienização completa e corte com máquina"),

    RABIES_VACCINE("Vácina contra raiva"),

    DAYCARE("Creche para cães"),

    HOSTING_SIMPLE("Hospedagem"),
    HOSTING_PREMIUM("Hospedagem Premium");

    private final String description;
}
