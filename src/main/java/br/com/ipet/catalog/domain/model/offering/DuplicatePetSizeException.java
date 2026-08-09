package br.com.ipet.catalog.domain.model.offering;

import br.com.ipet.catalog.domain.model.DomainException;

public class DuplicatePetSizeException extends DomainException {

    public DuplicatePetSizeException(String size) {
        super("Cannot have duplicate PetSize: " + size);
    }
}


