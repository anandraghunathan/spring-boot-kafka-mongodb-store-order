package com.storeorder.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "orderdetails")
@Component
public class StoreOrderBean {

    @Id
    public String id;
    public String person;
    public String title;
    public String count;
    public String cost;
    public String shipto;

    public StoreOrderBean() {}

    public StoreOrderBean(String person, String title, String count, String cost, String shipto){
        this.person = person;
        this.title = title;
        this.count = count;
        this.cost = cost;
        this.shipto = shipto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getShipto() {
        return shipto;
    }

    public void setShipto(String shipto) {
        this.shipto = shipto;
    }

    @Override
    public String toString() {
        return String.format("StoreOrder[id=%s, person='%s', title='%s', count='%s', cost='%s', shipto='%s']",
                id, person, title, count, cost, shipto);
    }
}
