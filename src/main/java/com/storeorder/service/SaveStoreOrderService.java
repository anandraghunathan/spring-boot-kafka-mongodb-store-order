package com.storeorder.service;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SaveStoreOrderService {

    private static final Logger log = LoggerFactory.getLogger(SaveStoreOrderService.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    private List<String> orderStorage = new ArrayList<>();

    public void put(String message){
        orderStorage.add(message);
    }

    public String toString(){
        StringBuffer info = new StringBuffer();
        orderStorage.forEach(msg->info.append(msg).append("<br/>"));
        return info.toString();
    }

    public void clear() {
        orderStorage.clear();
    }

    // inserts to our mongodb
    public void insertToDb(String jsonString) {
        //Document doc = Document.parse(jsonString);
        DBObject dbObject = (DBObject) JSON.parse(jsonString);
        try {
            mongoTemplate.insert(dbObject, "orderdetails");
        } catch (DuplicateKeyException dke) {
            log.error("Duplicate order id can't be inserted: "+dke);
            throw new DuplicateKeyException("Duplicate order id can't be inserted");
        }

    }
}
