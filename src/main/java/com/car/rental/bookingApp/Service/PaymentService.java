package com.car.rental.bookingApp.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.car.rental.bookingApp.dao.BookingRepository;
import com.car.rental.bookingApp.dao.PaymentRepository;
import com.car.rental.bookingApp.model.Booking;
import com.car.rental.bookingApp.model.Payment;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	BookingService bookingService;

//	public Payment generateBillByBookingId(long bookId) {
//		Optional<Booking> bookData = bookingRepository.findById(bookId);
//		Payment pay = new Payment();
//		
//		long price = 10;
//		if (bookData.isPresent()) {
//		//	pay.getBooking().setBookId(bookData.get().getBookId());
// 
//			
//			// Calucalte time difference in milliseconds
//			long time_difference = bookData.get().getToDate().getDate() - bookData.get().getFromDate().getDate();
//            pay.setBooking(bookData.get());
//			pay.setAmount(price * time_difference);
//			pay.setPayment_status("DUE");
//			return paymentRepository.save(pay);
//			// return pay;
//		} else {
//			return pay;
//		}

	// }

	public String doPayment(long paymentId) {
		// TODO Auto-generated method stub

		Optional<Payment> paymentInfo = paymentRepository.findById(paymentId);
		if (paymentInfo.isPresent() && (paymentInfo.get().getPayment_status().equals("DUE")) ) {
			Payment paydata = paymentInfo.get();
			paydata.setPayment_status("DONE");
			paymentRepository.save(paydata);
			bookingService.closeBoking(paydata.getBooking().getBookId());

			return "Payment is done";
		} else {
			return "Payment failed !!!";
		}
	}

	public Payment paymentGeneration(Booking car,long price) {
		Optional<Booking> bookData = bookingRepository.findById(car.getBookId());
		Payment pay = new Payment();

		//long price = 10;
		if (bookData.isPresent()) {
			// pay.getBooking().setBookId(bookData.get().getBookId());

			// Calucalte time difference in milliseconds
			long time_difference = bookData.get().getToDate().getDate() - bookData.get().getFromDate().getDate();
			pay.setBooking(bookData.get());
			pay.setAmount(price * time_difference);
			pay.setPayment_status("DUE");
			return paymentRepository.save(pay);
			// return pay;
		} else {
			return pay;
		}

	}

	public void canclePayment(long bookId) {
		// TODO Auto-generated method stub

		Optional<Payment> paymentInfo = paymentRepository.findById(bookId);
		if (paymentInfo.isPresent() && (paymentInfo.get().getPayment_status().equals("BOOKED"))) {
			Payment paydata = paymentInfo.get();
			paydata.setPayment_status("CANCLED");
			paymentRepository.save(paydata);
			// bookingService.closeBoking(paydata.getBooking().getBookId());

		}

	}

	public Payment paymentInfo(long bookId) {
		// TODO Auto-generated method stub
		return paymentRepository.findById(bookId).get();
	}

	public Optional<Payment> generateBillByBooking(long bookId) {
		// TODO Auto-generated method stub
		
		Optional<Booking> book= bookingRepository.findById(bookId);
		
		//book.get();
    	Payment pay = new Payment();
    	pay.setAmount(book.get().getPayment().getAmount());
    	//pay.setBooking(book.get());
    	pay.setPayment_ID(book.get().getPayment().getPayment_ID());
    	pay.setPayment_status(book.get().getPayment().getPayment_status());
		//pay.setBooking(book.get());
		//pay.getBooking().setBookId(bookId);

	//Example<Payment> example = Example.of(book.get().getPayment());
//		Iterable<Person> searchResult = repository.findAll(example);
		
		
		
		
		//Optional<Payment> payData = paymentRepository.findById(book.get().getPayment().getPayment_ID());
				//paymentRepository.findOne(example);
		
		
		return Optional.ofNullable(pay);
	}

}
