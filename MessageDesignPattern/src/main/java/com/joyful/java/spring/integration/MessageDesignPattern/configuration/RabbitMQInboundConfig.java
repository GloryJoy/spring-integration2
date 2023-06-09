package com.joyful.java.spring.integration.MessageDesignPattern.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.inbound.AmqpInboundChannelAdapter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class RabbitMQInboundConfig {

    static final String queueName = "foo-reservation-queue";

    @Bean
    public MessageChannel fooReservationListenerChannel()  {
        return new DirectChannel();
    }

//    @Bean()
//    Queue queue(){
//        return new Queue(queueName, false);
//    }

    @Bean
    public AmqpInboundChannelAdapter amqpInboundChannelAdapter(SimpleMessageListenerContainer listenerContainer,
                                                               @Qualifier("fooReservationListenerChannel") MessageChannel channel){

        var adapter = new AmqpInboundChannelAdapter(listenerContainer);
        adapter.setOutputChannel(channel);

        return adapter;
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory){
        var container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        return container;
    }
}
