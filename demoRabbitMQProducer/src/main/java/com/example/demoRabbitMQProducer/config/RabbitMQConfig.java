package com.example.demoRabbitMQProducer.config;

import static org.springframework.amqp.core.BindingBuilder.bind;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	@Value("${tiger.rabbitmq.queue}")
	String queueName;

	@Value("${tiger.rabbitmq.queue2}")
	String queueName2;

	@Value("${tiger.rabbitmq.exchange}")
	String exchange;

	@Value("${tiger.rabbitmq.routingkey}")
	String routingkey;

	@Value("${tiger.rabbitmq.routingkey2}")
	String routingkey2;

	/*
	 * @Bean Queue queue() { return new Queue(queueName, false); }
	 * 
	 * @Bean DirectExchange exchange() { return new DirectExchange(exchange); }
	 */

	/*
	 * @Bean Binding binding(Queue queue, DirectExchange exchange) { return
	 * BindingBuilder.bind(queue).to(exchange).with(routingkey); }
	 */

	@Bean
	public Declarables directBingdings() {
		Queue directQueue1 = new Queue(queueName, false);
		Queue directQueue2 = new Queue(queueName2, false);
		DirectExchange directExchange = new DirectExchange(exchange);
		return new Declarables(directExchange, bind(directQueue1).to(directExchange).with(routingkey),
				bind(directQueue2).to(directExchange).with(routingkey2));
	}
	
//	@RabbitListener(queues = {"javainuse.queue", "javainuse.queue2"})
//    public void listen(Book book) {
//        System.out.println("Message read from myQueue : " + book);
//    }
    
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
