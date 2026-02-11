package br.com.ipet.catalog.domain.model.service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OptionalDogHygiene {
    NAIL_CUTTING("Corte de Unhas"),
    TEETH_BRUSHING("Escovação dos dentes"),
    EAR_CLEANING("Limpeza dos ouvidos"),
    PERFUME("Perfume"),
    PROPS("Adereços");

    private final String valueName;

    public String valueName() {
        return this.valueName;
    }

}
