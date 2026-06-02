package br.com.ipet.catalog.domain.model.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum ServiceSubCategory {
    DOG_HIGYENE(List.of(ServiceType.BATH, ServiceType.GROOMING_SCISSOR, ServiceType.GROOMING_CLIPPERS)),
    DOG_HEALTH(List.of(ServiceType.RABIES_VACCINE)),
    DOG_DAYCARE(List.of()),
    DOG_HOSTING(List.of()),

    CAT_HIGYENE(List.of(ServiceType.BATH)),
    CAT_HEALTH(List.of(ServiceType.RABIES_VACCINE));

    private final List<ServiceType> serviceTypes;

    /*private ServiceSubCategoryId id;
    private ServiceCategoryId categoryId;
    private String name;
    private Set<ServiceType> serviceTypes;

    static ServiceSubCategory create(ServiceCategoryId categoryId, String name, Set<ServiceType> serviceTypes) {
        return new ServiceSubCategory(new ServiceSubCategoryId(), categoryId, name, serviceTypes);
    }

    @Builder(builderClassName = "ExistingServiceSubCategoryBuilder", builderMethodName = "existing")
    public ServiceSubCategory(ServiceSubCategoryId id, ServiceCategoryId categoryId, String name, Set<ServiceType> serviceTypes) {
        this.setId(id);
        this.setCategoryId(categoryId);
        this.setName(name);
        this.setServiceTypes(serviceTypes);
    }

    public ServiceSubCategoryId id() {
        return id;
    }

    private void setId(ServiceSubCategoryId id) {
        FieldValidator.requiresNonNull("id", id);
        this.id = id;
    }

    public ServiceCategoryId categoryId() {
        return categoryId;
    }

    private void setCategoryId(ServiceCategoryId categoryId) {
        FieldValidator.requiresNonNull("categoryId", categoryId);
        this.categoryId = categoryId;
    }

    public String name() {
        return name;
    }

    private void setName(String name) {
        FieldValidator.requiresNonNull("name", name);
        this.name = name;
    }

    public Set<ServiceType> serviceTypes() {
        return Collections.unmodifiableSet(serviceTypes);
    }

    private void setServiceTypes(Set<ServiceType> serviceTypes) {
        FieldValidator.requiresNonNull("serviceTypes", serviceTypes);
        this.serviceTypes = serviceTypes;
    }*/
}
