package com.storeorder.repository;

import com.storeorder.bean.StoreOrderBean;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StoreOrderRepository extends MongoRepository<StoreOrderBean, Long> {
    public String findByPerson(String person);
    public String findByTitle(String title);
}
