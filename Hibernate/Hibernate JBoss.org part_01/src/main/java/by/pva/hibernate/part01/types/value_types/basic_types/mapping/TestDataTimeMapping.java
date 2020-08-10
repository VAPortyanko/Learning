package by.pva.hibernate.part01.types.value_types.basic_types.mapping;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class TestDataTimeMapping {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		DateEvent dateEvent = new DateEvent();
		dateEvent.setDate(new Date(System.currentTimeMillis()));
		dateEvent.setTime(new java.util.Date(System.currentTimeMillis()));
		dateEvent.setDate2(new java.util.Date(System.currentTimeMillis()));
		dateEvent.setTimestamp(new java.util.Date(System.currentTimeMillis()));
		dateEvent.setTimestamp2(Calendar.getInstance(TimeZone.getTimeZone("GMT+3:00")));
		dateEvent.setDate3(LocalDate.now());
		dateEvent.setTime2(LocalTime.now());
		dateEvent.setTimestamp3(Instant.now());

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(dateEvent);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}

@Entity(name = "DateEvents")
class DateEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// java.sql.Date (, java.sql.Time, java.sql.TimeStamp). 
	@Column(name = "date")
	private Date date;
	
	// java.util.Date
	@Column(name = "time")
	@Temporal(TemporalType.TIME)
	private java.util.Date time;
	@Column(name = "date2")
	@Temporal(TemporalType.DATE)
	private java.util.Date date2;
	@Column(name = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date timestamp;
	
	// java.util.Calendar
	@Column(name = "timestamp2")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar timestamp2;
	
	// Java 8 Data/Time (for java 8 no @Temporal annotation required).
	@Column(name = "date3")
	private LocalDate date3;    // java.time.LocalDate. 
	@Column(name = "time2")
	private LocalTime time2;    // java.time.LocalTime, java.time.OffsetTime.
	@Column(name = "timestamp3")
	private Instant timestamp3; // java.time.Instant, java.time.LocalDateTime, java.time.OffsetDateTime and java.time.ZonedDateTime.

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public java.util.Date getTime() {
		return time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}
	public java.util.Date getDate2() {
		return date2;
	}
	public void setDate2(java.util.Date date2) {
		this.date2 = date2;
	}
	public java.util.Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(java.util.Date timestamp) {
		this.timestamp = timestamp;
	}
	public Calendar getTimestamp2() {
		return timestamp2;
	}
	public void setTimestamp2(Calendar timestamp2) {
		this.timestamp2 = timestamp2;
	}
	public LocalDate getDate3() {
		return date3;
	}
	public void setDate3(LocalDate date3) {
		this.date3 = date3;
	}
	public LocalTime getTime2() {
		return time2;
	}
	public void setTime2(LocalTime time2) {
		this.time2 = time2;
	}
	public Instant getTimestamp3() {
		return timestamp3;
	}
	public void setTimestamp3(Instant timestamp3) {
		this.timestamp3 = timestamp3;
	}
}
