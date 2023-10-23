package model;

import java.io.Serializable;
import java.util.List;

import static service.validation.ProductValidate.*;

public class Product implements Serializable {
    private long productId;
    private String productName;
    private long categoryId;
    private String description;
    private double unitPrice;
    private int stock;
    private boolean status;
    public Product() {
    }

    public Product(long productId, String productName, long categoryId, String description, double unitPrice, int stock, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.status = status;

    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }



    public void inputProductData() {
        this.productName = productNameValidate();
        this.categoryId = getCategoryIdToCategory();
        this.description = productDescriptionValidate();
        this.unitPrice = initPriceValidate();
        this.stock = stockValidate();
        this.status = validateProductStatus();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("productId=").append(productId);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", categoryId=").append(categoryId);
        sb.append(", description='").append(description).append('\'');
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", stock=").append(stock);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }

    public String toString1() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("productId=").append(productId);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", unitPrice=").append(unitPrice);
        sb.append('}');
        return sb.toString();
    }
}
