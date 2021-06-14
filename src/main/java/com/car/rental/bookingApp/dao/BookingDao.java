package com.car.rental.bookingApp.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BookingDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Session getSession() {
		Session session = (Session) entityManager.unwrap(Session.class);
//		    Student student = session.get(Student.class, id);
//		    return student;
		// Session session = entityManager.getDelegate(Booking.class);
		// .unwrap(Booking.class);

		return session;
	}

	public boolean isAvilableBoooking(long carID, Date fromdate) {

		// Criteria c = getSession().createCriteria(Booking.class);
		Session session = getSession();
		Query query = session.createQuery(
				"select count(*) from Booking where c_Id=:carId and status = 'BOOKED' and to_date > :date ");
		query.setParameter("carId", carID); // and status = 'BOOKED'
		query.setParameter("date", fromdate);

		System.out.println("date is   " + fromdate);
		// System.out.println("checking value " + query.getSingleResult());

//	   if (query.getSingleResult() == null || query.getSingleResult(). <1) {
//           return true;
//     
		if (query.getSingleResult().toString().equals("0")) {
			System.out.println("checking value check " + query.getSingleResult());
			return true;

		}
//	     
		return false;
	}

//	  public void save(Employee emp) {
//
//	    getSession().save(emp);
//
//	  }
}
