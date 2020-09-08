package com.example.cars.kafka.consumer;

import java.time.LocalDate;
import java.util.concurrent.CountDownLatch;

import com.example.cars.model.Car;
import com.example.cars.model.Receipt;
import com.example.cars.model.Seller;
import com.example.cars.repository.CarRepository;
import com.example.cars.repository.ReceiptRepository;
import com.example.cars.repository.SellerRepository;
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
    private final SellerRepository sellerRepository;
    private final ReceiptRepository receiptRepository;

    private static final String TOPIC = "test_topic";
    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    public Receiver(CarRepository carRepository, SellerRepository sellerRepository, ReceiptRepository receiptRepository) {
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
        this.receiptRepository = receiptRepository;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void receive(Receipt receipt) {
        LOGGER.info("received receipt='{}'", receipt.toString());
        receiptRepository.save(receipt);
        System.out.println(receipt.toString() + "Receipt"+ receipt.toString()+"Successfully saved!!!");
        latch.countDown();
    }
}
