	package com.example.demoRabbitMQProducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoRabbitMQProducer.model.Book;
import com.example.demoRabbitMQProducer.service.RabbitMQSender;

@RestController
@RequestMapping(value="/tiger-rabbitmq")
public class RabbitMQWebController {
	@Autowired
	RabbitMQSender rabbitMQSender;
	
	@GetMapping(value="/producer")
	public String produce(@RequestParam("title") String title, @RequestParam("author") String author) {
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		rabbitMQSender.send(book);
		return("Producer 1: send successfully");
	}
	
	@GetMapping(value="/producer2")
	public String produce2(@RequestParam("title") String title, @RequestParam("author") String author) {
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		rabbitMQSender.send2(book);
		return("Producer 2: send successfully");
	}
}
