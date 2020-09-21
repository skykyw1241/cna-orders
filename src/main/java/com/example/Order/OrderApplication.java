package com.example.Order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
//@EnableBinding(Processor.class)
@EnableBinding(KafkaProcessor.class)
public class OrderApplication {

	private static ApplicationContext applicationContext;
	public static void main(String[] args) {
		applicationContext = SpringApplication.run(OrderApplication.class, args);
	}

	public static KafkaProcessor getBeanForProcessor(){
		return OrderApplication.applicationContext.getBean(KafkaProcessor.class);
	}

}
