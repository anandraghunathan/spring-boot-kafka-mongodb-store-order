package com.storeorder.controller;

import com.storeorder.service.StoreOrderProducer;
import com.storeorder.service.StoreOrderTransformerService;
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
    StoreOrderTransformerService storeOrderTransformerService;

	@RequestMapping(value="/send", method=POST, consumes=APPLICATION_XML_VALUE, produces=APPLICATION_JSON_VALUE)
	@ResponseBody
	public String send(@RequestBody String order) {
		log.info("sending data to kafka = '{}'", order);

		String jsonOrder = null;
		try {
			// transform xml to json object
			jsonOrder = storeOrderTransformerService.transform(order);
		} catch(NullPointerException npe) {
			log.error("Null pointer exception occurred: " +npe);
		}

		// send order info to kafka
		storeOrderProducer.send(jsonOrder);

		return "Order information sent to the Kafka Topic store_order_topic Successfully";
	}
}