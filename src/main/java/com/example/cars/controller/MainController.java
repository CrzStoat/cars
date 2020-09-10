package com.example.cars.controller;


import com.example.cars.kafka.producer.Sender;
import com.example.cars.model.Receipt;
import com.example.cars.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("car")
public class MainController {
    private final Sender sender;

    @Autowired
    private final ReceiptRepository receiptRepository;

    @Autowired
    public MainController(Sender sender, ReceiptRepository receiptRepository) {
        this.sender = sender;
        this.receiptRepository = receiptRepository;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> messageToTopic(@Valid @RequestBody Receipt receipt){
        this.sender.send(receipt);
        return ResponseEntity.ok("Receipt successfully saved!");
    }

    @GetMapping("/get")
    public List<Receipt> getAllReceipt(){
        return receiptRepository.findAll();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
