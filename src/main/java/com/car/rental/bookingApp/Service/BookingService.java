package com.car.rental.bookingApp.Service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.rental.bookingApp.dao.BookingDao;
import com.car.rental.bookingApp.dao.BookingRepository;
import com.car.rental.bookingApp.model.Booking;
import com.car.rental.bookingApp.model.Payment;

@Service
public class BookingService {

	private static long carId;
	
	private static long price;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	PaymentService paymentService;
	// Session session = getSession();

	public void saveCardId( Object myList) {
		// carId=map.get(0);
		//carId = car_Id;
//		carId=myList.get(0);
//		price=myList.get(1);
		List<Long> newList = (List<Long>) myList;
		
		carId=newList.get(0);
		price=newList.get(1);
		System.out.println(" inside bookingservice " + carId);
	}

	public String bookingRequest(Booking bookcar, long carID2) {
		// TODO Auto-generated method stub
		if (carId == carID2) {
			bookcar.setC_Id(carId);

			System.out.println("car requested for " + carId);
			// bookingRepository.save(bookcar);

			if (bookingDao.isAvilableBoooking(carId, bookcar.getFromDate())) {

				// System.out.println("car requested can be booked " + carId);
				bookcar.setStatus("BOOKED");
				bookingRepository.save(bookcar);

				paymentService.paymentGeneration(bookcar,price);

				return "car requested can be booked" + carId;
			}
		}
		System.out.println("car requested can not be booked  " + carId);
		// bookingRepository.save(bookcar);
		return "car requested can not be booked" + carId;

	}

	// Session session= (Session) entityManager.getDelegate();

	// String hql = "from Booking where c_Id=:carId and status = 'BOOKED'";
//		Query query =  entityManager.createQuery(hql);
//		query.setParameter("carId", carId);
//		//setLong("carId", carId);
//		String results = query.getQueryString();

	// System.out.println(results);

//		Query query = entityManager.createQuery("Select sm from Booking where c_Id=:carId and status = 'BOOKED'");
//		query.setParameter("carId",carId);

	public void closeBoking(long booking_id) {
		Optional<Booking> bookingInfo = bookingRepository.findById(booking_id);
		if (bookingInfo.isPresent() && (bookingInfo.get().getStatus().equals("BOOKED"))) {
			// Payment paydata = paymentInfo.get();
			bookingInfo.get().setStatus("CLOSED");
			bookingRepository.save(bookingInfo.get());

		}
	}

	public String cancleBooking(long bookingId) {
		// TODO Auto-generated method stub
		Optional<Booking> bookingInfo = bookingRepository.findById(bookingId);
		if (bookingInfo.isPresent() && (bookingInfo.get().getStatus().equals("BOOKED"))) {
			// Payment paydata = paymentInfo.get();
			bookingInfo.get().setStatus("CANCLED");
			bookingRepository.save(bookingInfo.get());
			paymentService.canclePayment(bookingInfo.get().getBookId());
			return "Booking is cancled";

		}
		return "Booking is not cancled";
	}

	public Optional<Booking> generateBillByBookingId(long bookId) {

		Optional<Booking> bookingInfo = bookingRepository.findById(bookId);
		if (bookingInfo.isPresent()) {
			Payment pay = paymentService.paymentInfo(bookingInfo.get().getBookId());
			bookingInfo.get().setPayment(pay);
		}

		return bookingInfo;
	}

//	public Optional<Booking> getBookingDetails(long bookId) {
//		// TODO Auto-generated method stub
//		return bookingRepository.findById(bookId);
//	}


}
