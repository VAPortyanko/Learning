package by.pva.hibernate.part01.locking.optimistic;

import static by.pva.hibernate.part01._myUtils.MyUtils.doInHibernateWithDefaultPersistanceUnit;

import java.sql.Connection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.OptimisticLock;

public class TestExcludingTrackingForPropertyWithOptimistikLocking {

	public static void main(String[] args) {

		doInHibernateWithDefaultPersistanceUnit(entityManager -> {
			
			Query query = entityManager.createQuery("delete from Phone23");
			query.executeUpdate();
			
			Phone23 phone = new Phone23();
			phone.setId(1L);
			phone.setNumber("+375(29)00-00-007");
			phone.setCallCount(100);
			
			entityManager.persist(phone);
			
			entityManager.flush();
			entityManager.clear();
			
			Phone23 phone2 = entityManager.find(Phone23.class, 1L);
			phone2.setNumber("+7(44)01-01-001");
			
			entityManager.merge(phone2);
			
			entityManager.flush();
			entityManager.clear();
			
			Phone23 phone3 = entityManager.find(Phone23.class, 1L);
			phone3.setCallCount(1000);
			entityManager.merge(phone3); // The version value will not be updated
			
		});
		
	}
}

@Entity(name = "Phone23")
@Table(name = "Phones23")
class Phone23 {

	@Id
	private Long id;
	@Column(name = "`number`")
	private String number;
	@OptimisticLock(excluded = true)
	private long callCount;
	@Version
	private Long version;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public long getCallCount() {
		return callCount;
	}
	public void setCallCount(long callCount) {
		this.callCount = callCount;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}

	public void incrementCallCount() {
		this.callCount++;
	}
}

