package com.car.rental.bookingApp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long payment_ID;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_Id", referencedColumnName = "bookId")
	private Booking booking;

	@Column(name = "amount", nullable = false)
	private long amount;
	
	@Column(name = "payment_status", nullable = false)
	private String payment_status;

	public Payment() {
	}

	public long getPayment_ID() {
		return payment_ID;
	}

	public void setPayment_ID(long payment_ID) {
		this.payment_ID = payment_ID;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}


	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public Payment(long payment_ID, Booking booking, long amount, String payment_status) {
		super();
		this.payment_ID = payment_ID;
		this.booking = booking;
		this.amount = amount;
		this.payment_status = payment_status;
	}

	
	
}
