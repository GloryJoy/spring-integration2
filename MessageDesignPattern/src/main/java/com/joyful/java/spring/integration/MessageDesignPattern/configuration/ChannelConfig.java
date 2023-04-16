package com.joyful.java.spring.integration.MessageDesignPattern.configuration;

import com.joyful.java.spring.integration.MessageDesignPattern.model.Swag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.channel.*;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import java.util.Comparator;

@Configuration
@EnableIntegration
public class ChannelConfig {
    @Bean
    public MessageChannel swagChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel directChannel()
    {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel reservationRecordChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel eventChannel(){
        return new PublishSubscribeChannel();
    }

    @Bean
    public MessageChannel hotelBookingChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel queueChannel(){
        return new QueueChannel();
    }

    @Bean
    public MessageChannel priorityChannel(){
        return new PriorityChannel();
    }

    @Bean
    public MessageChannel executorChannel(TaskExecutor taskExecutor){
        return new ExecutorChannel(taskExecutor);
    }

    @Bean
    public MessageChannel customErrorChannel(){
        return new PublishSubscribeChannel();
    }

//    @Bean
//    public MessageChannel customizedPriorityChannel(){
//        return new PriorityChannel((Comparator.comparingLong(
//                (Message<?> m) -> (((Swag) m.getPayload()).getAmount())
//        )))
//    }
}
