package com.car.rental.bookingApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car.rental.bookingApp.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {}
	