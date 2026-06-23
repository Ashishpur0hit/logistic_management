package com.logistics.payment_service.config;

import com.logistics.payment_service.event.PaymentEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;


import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

//    @Bean
//    public ProducerFactory<String, PaymentEvent> producerFactory() {
//
//        Map<String, Object> config = new HashMap<>();
//
//        config.put(
//                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                "localhost:9092"
//        );
//
//        config.put(
//                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
//                StringSerializer.class
//        );
//
//        config.put(
//                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//                org.springframework.kafka.support.serializer.JsonSerializer.class
//        );
//
//        return new DefaultKafkaProducerFactory<>(config);
//    }
//
//    @Bean
//    public KafkaTemplate<String, PaymentEvent> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name("payment-topic").build();
    }
}