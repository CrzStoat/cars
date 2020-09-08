package com.example.cars.repository;

import com.example.cars.model.Car;
import com.example.cars.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
