package com.storeorder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StoreOrderProducer {
	private static final Logger log = LoggerFactory.getLogger(StoreOrderProducer.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${store.order.topic}")
	String kafkaTopic = "store-order-topic";
	
	public void send(String order) {
		//log.info("sending data = '{}'", order);
	    kafkaTemplate.send(kafkaTopic, order);
	}
}