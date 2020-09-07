package com.example.cars.kafka.consumer;

import java.util.concurrent.CountDownLatch;

import com.example.cars.model.Car;
import com.example.cars.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@EnableKafka
@Service
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
    private final CarRepository carRepository;

    private static final String TOPIC = "test_topic";
    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    public Receiver(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void receive(Car car) {
        LOGGER.info("received car='{}'", car.toString());
        carRepository.save(car);
        System.out.println(car.toString() + " successfully saved!!!");
        latch.countDown();
    }
}
