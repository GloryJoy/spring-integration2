package com.joyful.java.spring.integration.MessageDesignPattern.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.outbound.AmqpOutboundEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class RabbitMQOutboundConfig {

    @Bean
    public MessageChannel rmqReservationChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel amqpOBChannel() {
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "rmqReservationChannel", outputChannel = "amqpOBChannel")
    public ObjectToJsonTransformer objectToJsonTransformer(){
        return new  ObjectToJsonTransformer();
    }

    private static final String queueName = "foo-reservation-queue";
    @Bean
    public Queue queue(){
        return new Queue(queueName, false);

    }

    @Bean
    @ServiceActivator(inputChannel = "amqpOBChannel")
    public AmqpOutboundEndpoint amqpOutboundEndpoint(AmqpTemplate amqpTemplate){
        var ob = new AmqpOutboundEndpoint(amqpTemplate);
        ob.setRoutingKey(queueName);
        return ob;
    }


}
