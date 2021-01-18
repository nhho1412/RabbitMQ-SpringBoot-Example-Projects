package com.example.demoRabbitMQProducer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demoRabbitMQProducer.model.Book;

@Service
public class RabbitMQSender {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${tiger.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${tiger.rabbitmq.routingkey}")
	private String routingkey;	
	
	@Value("${tiger.rabbitmq.routingkey2}")
	private String routingkey2;	
	
	public void send(Book book) {
		rabbitTemplate.convertAndSend(exchange, routingkey, book);
		System.out.println("Producer: Send msg = " + book);
	}
	
	public void send2(Book book) {
		rabbitTemplate.convertAndSend(exchange, routingkey2, book);
		System.out.println("Producer2: Send msg = " + book);
	}
}
