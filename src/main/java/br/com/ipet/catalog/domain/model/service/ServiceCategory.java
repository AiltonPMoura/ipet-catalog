package br.com.ipet.catalog.domain.model.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.CAT_HEALTH;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.CAT_HIGYENE;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_DAYCARE;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_HEALTH;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_HIGYENE;
import static br.com.ipet.catalog.domain.model.service.ServiceSubCategory.DOG_HOSTING;

@Getter
@RequiredArgsConstructor
public enum ServiceCategory {
    HIGYENE("Hygiene", List.of(DOG_HIGYENE, CAT_HIGYENE)),
    HEALTH("Health", List.of(DOG_HEALTH, CAT_HEALTH)),
    DAYCARE("Daycare", List.of(DOG_DAYCARE)),
    HOSTING("Hosting", List.of(DOG_HOSTING));

    private final String description;
    private final List<ServiceSubCategory> subCategories;

    /*private ServiceCategoryId id;
    private String name;
    private Set<ServiceSubCategory> subCategories;

    static ServiceCategory create(String name, Set<ServiceSubCategory> subCategories) {
        return new ServiceCategory(new ServiceCategoryId(), name, subCategories);
    }

    @Builder(builderClassName = "ExistingServiceCategoryBuilder", builderMethodName = "existing")
    public ServiceCategory(ServiceCategoryId id, String name, Set<ServiceSubCategory> subCategories) {
        this.setId(id);
        this.setName(name);
        this.setSubCategories(subCategories);
    }

    public ServiceCategoryId id() {
        return id;
    }

    private void setId(ServiceCategoryId id) {
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

    public Set<ServiceSubCategory> subCategories() {
        return Collections.unmodifiableSet(subCategories);
    }

    private void setSubCategories(Set<ServiceSubCategory> subCategories) {
        FieldValidator.requiresNonNull("subCategories", subCategories);
        this.subCategories = subCategories;
    }*/
}
