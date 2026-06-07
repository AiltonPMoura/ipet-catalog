package br.com.ipet.catalog.domain.model.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public enum ServiceType {
    BATH("Higienização completa", ServiceCategory.HIGYENE, Set.of(Species.GOG, Species.CAT)),
    GROOMING_SCISSOR("Higienização completa e corte na tesoura", ServiceCategory.HIGYENE, Set.of(Species.GOG)),
    GROOMING_CLIPPERS("Higienização completa e corte com máquina", ServiceCategory.HIGYENE, Set.of(Species.GOG)),

    STANDARD_HOTEL("Hospedagem comum", ServiceCategory.ACCOMMODATION, Set.of(Species.GOG, Species.CAT)),
    VIP_HOTEL("Hospedagem VIP", ServiceCategory.ACCOMMODATION, Set.of(Species.GOG, Species.CAT)),
    IN_HOME_BOARDING("Hospedagem domiciliar", ServiceCategory.ACCOMMODATION, Set.of(Species.GOG, Species.CAT)),

    STANDARD_DAYCARE("Creche comum", ServiceCategory.DAYCARE, Set.of(Species.GOG, Species.CAT)),
    VIP_DAYCARE("Creche VIP", ServiceCategory.DAYCARE, Set.of(Species.GOG, Species.CAT)),

    DOG_WALKING("Passeio para cães", ServiceCategory.ACTIVITY, Set.of(Species.GOG)),
    DROP_IN_VISIT("Visita rápida", ServiceCategory.ACTIVITY, Set.of(Species.GOG, Species.CAT)),
    TRAINING("Treinamento", ServiceCategory.ACTIVITY, Set.of(Species.GOG)),
    PLAY_SESSION("Sessão de brincadeiras", ServiceCategory.ACTIVITY, Set.of(Species.GOG)),

    VETERINARY_CONSULTATION("Consulta veterinária", ServiceCategory.HEALTH, Set.of(Species.GOG, Species.CAT, Species.BIRD, Species.HORSE, Species.HAMSTER, Species.RABBIT));

    private final String description;
    private final ServiceCategory category;
    private final Set<Species> supportedSpecies;

    public boolean supportsSpecies(Species species) {
        return this.supportedSpecies.contains(species);
    }

    public boolean doesNotSupportsSpecies(Species species) {
        return !this.supportsSpecies(species);
    }

    public String description() {
        return description;
    }

    public ServiceCategory category() {
        return category;
    }

    public Set<Species> supportedSpecies() {
        return supportedSpecies;
    }
}
