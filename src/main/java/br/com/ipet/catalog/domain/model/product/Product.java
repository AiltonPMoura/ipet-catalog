package br.com.ipet.catalog.domain.model.product;

import br.com.ipet.catalog.domain.model.AggregateRoot;
import br.com.ipet.catalog.domain.model.commons.CompanyId;
import br.com.ipet.catalog.domain.model.commons.Money;
import br.com.ipet.catalog.domain.model.commons.ProductDescription;
import br.com.ipet.catalog.domain.model.commons.ProductId;
import br.com.ipet.catalog.domain.model.commons.ProductName;
import br.com.ipet.catalog.domain.model.commons.Quantity;
import br.com.ipet.catalog.domain.model.util.FieldValidator;
import lombok.Builder;

public class Product implements AggregateRoot<ProductId> {
    private ProductId id;
    private CompanyId companyId;
    private ProductName name;
    private ProductDescription description;
    private Money price;
    private Quantity totalStock;
    private ProductSubCategory subCategory;
    private boolean enabled;

    @Builder(builderClassName = "CreateNewProductBuilder", builderMethodName = "createNew")
    private static Product create(CompanyId companyId,
                                  ProductName name, ProductDescription description,
                                  Money price, Quantity totalStock, ProductSubCategory subCategory) {
        return new Product(new ProductId(), companyId, name, description, price, totalStock, subCategory, true);
    }

    @Builder(builderClassName = "CreateExistingProductBuilder", builderMethodName = "existing")
    private Product(ProductId id, CompanyId companyId,
                    ProductName name, ProductDescription description,
                    Money price, Quantity totalStock, ProductSubCategory subCategory,
                    boolean enabled) {
        this.setId(id);
        this.setCompanyId(companyId);
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
        this.setTotalStock(totalStock);
        this.setSubCategory(subCategory);
        this.setEnabled(enabled);
    }

    void changeName(ProductName name) {
        this.setName(name);
    }

    void changeDescription(ProductDescription description) {
        FieldValidator.requiresNonNull("product description", description);
        this.setDescription(description);
    }

    void changePrice(Money price) {
        this.setPrice(price);
    }

    void changeSubCategory(ProductSubCategory subCategory) {
        this.setSubCategory(subCategory);
    }

    void enable() {
        this.setEnabled(true);
    }

    void disable() {
        this.setEnabled(false);
    }

    void addTotalStock(Quantity quantity) {
        FieldValidator.requiresNonNull("quantity", quantity);
        this.setTotalStock(this.totalStock.sum(quantity));
    }

    void subtractTotalStock(Quantity quantity) {
        FieldValidator.requiresNonNull("quantity", quantity);

        if (quantity.value() > this.totalStock.value())
            throw new RuntimeException();

        this.setTotalStock(this.totalStock.subtract(quantity));
    }

    public boolean availableStock() {
        return this.totalStock.value() > 0;
    }

    public ProductId id() {
        return id;
    }

    public CompanyId companyId() {
        return companyId;
    }

    public ProductName name() {
        return name;
    }

    public ProductDescription description() {
        return description;
    }

    public Money price() {
        return price;
    }

    public Quantity totalStock() {
        return totalStock;
    }

    public ProductSubCategory subCategory() {
        return subCategory;
    }

    public boolean enabled() {
        return enabled;
    }

    private void setId(ProductId id) {
        FieldValidator.requiresNonNull("productId", id);
        this.id = id;
    }

    private void setCompanyId(CompanyId companyId) {
        FieldValidator.requiresNonNull("companyId", companyId);
        this.companyId = companyId;
    }

    private void setName(ProductName name) {
        FieldValidator.requiresNonNull("product name", name);
        this.name = name;
    }

    private void setDescription(ProductDescription description) {
        this.description = description;
    }

    private void setPrice(Money price) {
        FieldValidator.requiresNonNull("product price", price);
        this.price = price;
    }

    private void setTotalStock(Quantity totalStock) {
        this.totalStock = totalStock;
    }

    private void setSubCategory(ProductSubCategory subCategory) {
        FieldValidator.requiresNonNull("productSubCategory", subCategory);
        this.subCategory = subCategory;
    }

    private void setEnabled(boolean enabled) {
        FieldValidator.requiresNonNull("enabled", enabled);
        this.enabled = enabled;
    }
}
