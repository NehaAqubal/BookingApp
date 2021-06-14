package com.car.rental.bookingApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car.rental.bookingApp.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	//Optional<Payment> findByBookingId(long bookingId);
}
	