package model;

import java.io.Serializable;

public class OrdersDetail implements Serializable {
    private long productId;
    private long oderId;
    private String productName;
    private double initPrice;
    private int quantity;

    public OrdersDetail() {
    }

    public OrdersDetail(long productId, long oderId, String productName, double initPrice, int quantity) {
        this.productId = productId;
        this.oderId = oderId;
        this.productName = productName;
        this.initPrice = initPrice;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getOderId() {
        return oderId;
    }

    public void setOderId(long oderId) {
        this.oderId = oderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getInitPrice() {
        return initPrice;
    }

    public void setInitPrice(double initPrice) {
        this.initPrice = initPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void dataInputOder() {

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrdersDetail{");
        sb.append("productId=").append(productId);
        sb.append(", oderId=").append(oderId);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", initPrice=").append(initPrice);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
