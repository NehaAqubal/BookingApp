package com.car.rental.bookingApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

@Component
@EnableJms
public class ConsumerMessageService {

	@Autowired
	BookingService bookingService;

	@JmsListener(destination = "test-queue")
	public void listener(byte[] bs /* HashMap<String, Long> map */ /* long carId */ ) {
		// bookingService.saveCardId(carId);
		Object myList=SerializationUtils.deserialize(bs);
		bookingService.saveCardId(myList);
		// return carId;
	}

}
