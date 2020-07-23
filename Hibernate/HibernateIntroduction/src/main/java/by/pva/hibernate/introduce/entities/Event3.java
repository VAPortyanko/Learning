package by.pva.hibernate.introduce.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "EVENTS")
@Audited // <--- this tell Envers to audit (track changes to) this entity
public class Event3 {
	private Long id;

	private String title;
	private Date date;

	public Event3() {
		// this form used by Hibernate
	}

	public Event3(String title, Date date) {
		// for application use, to create new events
		this.title = title;
		this.date = date;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "EVENT_ID")
	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EVENT_DATE")
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

	@Override
	public int hashCode() {
		int result = title.hashCode();
		result = 31 * result + date.hashCode();
		return result;
	}
}