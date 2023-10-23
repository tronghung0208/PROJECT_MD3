package model;

import java.io.Serializable;

import static service.validation.CategoryValidate.*;

public class Category implements Serializable {
    //1.Attribute
    private long catalogId;
    private String catalogName;
    private String description;
    private boolean status;


    public Category() {
    }

    public Category(long catalogId, String catalogName, String description, boolean status) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.status = status;
    }

    public long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(long catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputCategory() {
        this.catalogName = catalogNameValidate();
        this.description = catalogDescriptionValidate();
        this.status = validateCategoryStatus();
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Category{");
        sb.append("catalogId: ").append(catalogId);
        sb.append(", catalogName: '").append(catalogName).append('\'');
        sb.append(", description: '").append(description).append('\'');
        sb.append(", status: ").append(status ? "Hiện" : "Ẩn");
        sb.append('}');
        return sb.toString();
    }

}
