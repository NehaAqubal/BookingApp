package com.car.rental.bookingApp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.rental.bookingApp.Service.BookingService;
import com.car.rental.bookingApp.dao.BookingRepository;
import com.car.rental.bookingApp.model.Booking;

@RequestMapping("/Booking")
@RestController
public class BookingController {

	@Autowired
	BookingService bookingService;

	@Autowired
	BookingRepository bookingRepository;

	@GetMapping("/MyBookInfo")
	public String MyEnd() {
		return "Hello for Booking INfo";
	}

	@PostMapping("/bookingRequest/{carID}")
	public /* ResponseEntity */ String bookCar(@RequestBody Booking bookcar, @PathVariable("carID") long carID) {

		return bookingService.bookingRequest(bookcar, carID);
		// return ResponseEntity.status(HttpStatus.CREATED);
	}

	@GetMapping("/bookingRequestById/{id}")
	public ResponseEntity<Booking> bookingRequestById(@PathVariable("id") long id) {
		Optional<Booking> bookData = bookingRepository.findById(id);

		if (bookData.isPresent()) {
			Booking booked = bookData.get();
//			HashMap<String,Long> map =new HashMap<String,Long>();
//			map.put("C_ID", car.getC_Id());
//			map.put("Price", car.getPrice());
			// publishMessageService.publish(car.getC_Id());
			return new ResponseEntity<>(booked, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/cancleBooking/{BookingId}")
	public /* ResponseEntity<Booking> */ String cancleBooking(@PathVariable("BookingId") long BookingId) {

		return bookingService.cancleBooking(BookingId);

		// return "Payment done !!!";
	}

//	@GetMapping("/generateBillByBooking_ID/{book_Id}")
//	public ResponseEntity<Booking> generateBillByBookingId(@PathVariable("book_Id") long bookId) {
//
//		// return bookingService.generateBillByBookingId(bookId);
//		Optional<Booking> bookData = bookingRepository.findById(bookId);
//
//		if (bookData.isPresent()) {
//			Booking booked = bookData.get();
//			
////			HashMap<String,Long> map =new HashMap<String,Long>();
////			map.put("C_ID", car.getC_Id());
////			map.put("Price", car.getPrice());
//			// publishMessageService.publish(car.getC_Id());
//			return new ResponseEntity<>(booked, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}

		// return bookData.get();
//		// return
//		Payment pay= paymentService.generateBillByBookingId(bookId);
//		long payId = pay.getPayment_ID();
//		Optional<Payment> payData = paymentRepository.findById(payId);
//
//		if (payData.isPresent()) {
//
//			return new ResponseEntity<>(payData.get(), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
	//}

}
