package com.storeorder.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

@Configuration
public class StoreOrderStorage {

    private static final Logger log = LoggerFactory.getLogger(StoreOrderStorage.class);

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
    public void insertToDb(String jsonString){
        Document doc = Document.parse(jsonString);
        log.info("Document: "+doc);
        log.info("Mongo DB: "+mongoTemplate.getDb());
        mongoTemplate.insert(doc, "orderdetails");

    }
}
