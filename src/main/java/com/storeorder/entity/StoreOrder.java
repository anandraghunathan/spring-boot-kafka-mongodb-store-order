package com.storeorder.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orderdetails")
public class StoreOrder {

    @Id
    public String orderId;

    @NotBlank
    //@Indexed(unique=true)
    public String username;

    public String product;
    public String count;
    public String cost;
    public String shipTo;

    public StoreOrder() {}

    public StoreOrder(String username, String product, String count, String cost, String shipTo){
        this.username = username;
        this.product = product;
        this.count = count;
        this.cost = cost;
        this.shipTo = shipTo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    @Override
    public String toString() {
        return String.format("StoreOrder[orderId='%s', username='%s', product='%s', count='%s', cost='%s', shipTo='%s']",
                orderId, username, product, count, cost, shipTo);
    }
}