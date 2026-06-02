package br.com.ipet.catalog.domain.model.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceType {
    BATH("Higienização completa"),
    GROOMING_SCISSOR("Higienização completa e corte na tesoura"),
    GROOMING_CLIPPERS("Higienização completa e corte com máquina"),

    RABIES_VACCINE("Vácina contra raiva");

    private final String description;
    /*private ServiceTypeId id;
    private String name;
    private ServiceSubCategoryId subCategoryId;

    static ServiceType create(ServiceSubCategoryId subCategoryId, String name) {
        return new ServiceType(new ServiceTypeId(), subCategoryId, name);
    }

    @Builder(builderClassName = "ExistingServiceTypeBuilder", builderMethodName = "existing")
    public ServiceType(ServiceTypeId id, ServiceSubCategoryId subCategoryId, String name) {
        this.setId(id);
        this.setName(name);
        this.setSubCategoryId(subCategoryId);
    }

    public ServiceTypeId id() {
        return id;
    }

    private void setId(ServiceTypeId id) {
        FieldValidator.requiresNonNull("id", id);
        this.id = id;
    }

    public String name() {
        return name;
    }

    private void setName(String name) {
        FieldValidator.requiresNonNull("name", name);
        this.name = name;
    }

    public ServiceSubCategoryId subCategoryId() {
        return subCategoryId;
    }

    private void setSubCategoryId(ServiceSubCategoryId subCategoryId) {
        FieldValidator.requiresNonNull("subCategoryId", subCategoryId);
        this.subCategoryId = subCategoryId;
    }*/
}
