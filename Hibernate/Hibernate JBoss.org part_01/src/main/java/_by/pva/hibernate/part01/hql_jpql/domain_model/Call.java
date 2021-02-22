package _by.pva.hibernate.part01.hql_jpql.domain_model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Call")
@Table(name = "Phone_calls")
public class Call {

    @Id
    private Long id;
    @ManyToOne
    private Phone phone;
    @Column(name = "call_timestamp")
    private Date timestamp;
    private int duration;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Phone getPhone() {
		return phone;
	}
	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "Call [id=" + id + ", phone=" + "id=" + phone.getId() + " ("+ phone.getType() + ") "+ phone.getNumber() + ", timestamp=" + timestamp + ", duration=" + duration + "]";
	}
	
}
