package com.storeorder;

import com.storeorder.storage.StoreOrderStorage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
//@EnableTask
public class StoreOrderSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				new Object[] { StoreOrderSpringBootApplication.class }, args);
	}

	/*@Bean
	public CommandLineRunner commandLineRunner(StoreOrderStorage storeOrderStorage) {
		return strings ->
				System.out.println("Spring cloud task executed at :" +
						new SimpleDateFormat().format(new Date()));
	}*/
}