package com.car.rental.bookingApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.rental.bookingApp.Service.PaymentService;
import com.car.rental.bookingApp.dao.PaymentRepository;
import com.car.rental.bookingApp.model.Payment;

@RequestMapping("/Payment")
@RestController
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	@Autowired
	PaymentRepository paymentRepository;

	@GetMapping("/MyEndPaymentInfo")
	public String MyEnd() {
		return "Hello for PaymentInfo";
	}

	@GetMapping("/generateBillByBooking_ID/{book_Id}")
	public ResponseEntity<Payment> generateBillByBookingId(@PathVariable("book_Id") long bookId) {

		// return
		//Payment pay= paymentService.generateBillByBookingId(bookId);
	//	long payId = pay.getPayment_ID();
		Optional<Payment> payData = paymentService.generateBillByBooking(bookId);
		
		

		if (payData.isPresent()) {

			return new ResponseEntity<>(payData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/doPayment/{paymentId}")
	public /* ResponseEntity<Booking> */ String doPayment(@PathVariable("paymentId") long paymentId) {

		return paymentService.doPayment(paymentId);

		// return "Payment done !!!";
	}
}
