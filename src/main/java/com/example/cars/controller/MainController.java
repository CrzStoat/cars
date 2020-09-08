package com.example.cars.controller;


import com.example.cars.kafka.producer.Sender;
import com.example.cars.model.Car;
import com.example.cars.model.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("car")
public class MainController {
    private final Sender sender;

    @Autowired
    public MainController(Sender sender) {
        this.sender = sender;
    }

    @PostMapping("/publish")
    public void messageToTopic(@RequestBody Receipt receipt){
        this.sender.send(receipt);
    }
}
