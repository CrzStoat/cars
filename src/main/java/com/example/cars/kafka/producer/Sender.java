package com.example.cars.kafka.producer;

import com.example.cars.model.Car;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {
    private static final String TOPIC = "test_topic";
    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Bean
    public NewTopic createTopic(){
        return new NewTopic(TOPIC,3,(short) 1);
    }


    @Autowired
    private KafkaTemplate<String, Car> kafkaTemplate;

    public void send(Car car) {
        LOGGER.info("sending car='{}'", car.toString());
        kafkaTemplate.send(TOPIC, car);
    }
}
