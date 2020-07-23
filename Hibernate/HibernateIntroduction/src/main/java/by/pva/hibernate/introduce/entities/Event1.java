package by.pva.hibernate.introduce.entities;

import java.util.Date;

public class Event1 {

	private Long id;
	private String title;
	private Date date;

	public Event1() {
		// this form used by Hibernate
	}

	public Event1(String title, Date date) {
		// for application use, to create new events
		this.title = title;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}