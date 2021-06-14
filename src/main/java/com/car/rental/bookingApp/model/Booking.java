package com.car.rental.bookingApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long bookId;

	private long c_Id;

	@Column(name = "From_Date", nullable = false)
	private Date fromDate;
	
	@Column(name = "To_Date", nullable = false)
	private Date toDate;

	@Column(name = "S_address", nullable = false)
	private String sourceAddress;

	@Column(name = "D_address", nullable = false)
	private String destinationAddress;

	@Column(name = "Email_Id", nullable = false)
	private String emailID;

	@Column(name = "Contact_No", nullable = false)
	private int contactNo;

	private String status;

	@OneToOne(mappedBy = "booking")
	private Payment payment;

	
	
	public Booking(long bookId, long c_Id, Date fromDate, Date toDate, String sourceAddress, String destinationAddress,
			String emailID, int contactNo, String status, Payment payment) {
		super();
		this.bookId = bookId;
		this.c_Id = c_Id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.sourceAddress = sourceAddress;
		this.destinationAddress = destinationAddress;
		this.emailID = emailID;
		this.contactNo = contactNo;
		this.status = status;
		this.payment = payment;
	}


	public long getBookId() {
		return bookId;
	}


	public void setBookId(long bookId) {
		this.bookId = bookId;
	}


	public long getC_Id() {
		return c_Id;
	}


	public void setC_Id(long c_Id) {
		this.c_Id = c_Id;
	}


	public Date getFromDate() {
		return fromDate;
	}


	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}


	public Date getToDate() {
		return toDate;
	}


	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	public String getSourceAddress() {
		return sourceAddress;
	}


	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}


	public String getDestinationAddress() {
		return destinationAddress;
	}


	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}


	public String getEmailID() {
		return emailID;
	}


	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}


	public int getContactNo() {
		return contactNo;
	}


	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Payment getPayment() {
		return payment;
	}


	public void setPayment(Payment payment) {
		this.payment = payment;
	}


	Booking() {
	}

}
