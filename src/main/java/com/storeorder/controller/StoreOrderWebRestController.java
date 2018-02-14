package com.storeorder.controller;

import com.storeorder.services.StoreOrderProducer;
import com.storeorder.storage.StoreOrderStorage;
import com.storeorder.transformer.StoreOrderTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/storeorder/")
public class StoreOrderWebRestController {

	private static final Logger log = LoggerFactory.getLogger(StoreOrderWebRestController.class);

	@Autowired
	StoreOrderProducer storeOrderProducer;

	@Autowired
	StoreOrderStorage storeOrderStorage;

	@Autowired
	StoreOrderTransformer storeOrderTransformer;

	@RequestMapping(value="/send", method=POST, consumes=APPLICATION_XML_VALUE, produces=APPLICATION_JSON_VALUE)
	@ResponseBody
	//@GetMapping(value = "/producer")
	public String producer(@RequestBody String order) {
		log.info("sending data = '{}'", order);

		String jsonOrder = null;
		try {
			// transform xml to json object
			jsonOrder = storeOrderTransformer.transform(order);
		} catch(NullPointerException npe) {
			log.error("Null pointer exception occurred: " +npe);
		}

		// send order info
		storeOrderProducer.send(jsonOrder);

		// store entry into the mongodb
		System.out.println("Persisting data into mongodb will proceed now...");
		storeOrderStorage.insertToDb(jsonOrder);

		return "Order information sent to the Kafka Topic store_order_topic Successfully";
	}

	@GetMapping(value = "/consumer")
	public String getAllReceivedOrders() {
		String messages = storeOrderStorage.toString();
		storeOrderStorage.clear();

		return messages;
	}

}

