package com.storeorder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StoreOrderConsumer {
    private static final Logger log = LoggerFactory.getLogger(StoreOrderConsumer.class);

    @Autowired
    SaveStoreOrderService saveStoreOrderService;

    @KafkaListener(topics="${store.order.topic}")
    public void saveOrder(String order) {
        log.info("Received order info = '{}'", order);
        // store entry into the mongodb
        System.out.println("Persisting data into mongodb will proceed now...");
        saveStoreOrderService.insertToDb(order);
    }
}
