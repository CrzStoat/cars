package com.example.cars.repository;

import com.example.cars.model.Receipt;
import com.example.cars.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}
