package by.pva.hibernate.part01.locking.optimistic;

import java.sql.Connection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.Session;
import org.hibernate.annotations.OptimisticLock;

import by.pva.hibernate.part01._myUtils.BaseTest;


// In the example "The lost update problem" happens. (Transaction isolation level is 4 () in MySql doesn't prevent this problem).
// https://stackoverflow.com/questions/53562850/mysql-repeatable-read-isolation-level-and-lost-update-phenomena
// https://stackoverflow.com/questions/10040785/mysql-repeatable-read-and-lost-update-phantom-reads
// https://stackoverflow.com/questions/9060400/repeatable-read-and-second-lost-updates-issue
// https://vladmihalcea.com/a-beginners-guide-to-database-locking-and-the-lost-update-phenomena/
public class TestExcludingTrackingForPropertyWithOptimistikLocking2 extends BaseTest{

	public static void main(String[] args) {

		// Clearing the database and creating a new record moved into an isolated transaction. 
		// https://www.sql.ru/forum/1335949/hibernate-blokirovki-kak-rabotaet-primer
		doInJPA(entityManager -> {

			Query query = entityManager.createQuery("Delete from Phone24");
			query.executeUpdate();
			
			Phone24 phone = new Phone24();
			phone.setId(1L);
			phone.setNumber("+123-456-3333");
			phone.setCallCount(0);
			
			entityManager.persist(phone);
			
		});
		
		doInJPA(entityManager -> {
			
			int isolationLevel = entityManager.unwrap(Session.class).doReturningWork(Connection::getTransactionIsolation);
			System.out.println("Isolation level is [" + isolationLevel + "]");
			
			Phone24 phone = entityManager.find(Phone24.class, 1L);
			phone.setNumber("+123-456-0000");

			doInJPA(entityManager2 -> {
				Phone24 _phone = entityManager2.find(Phone24.class, 1L);
				_phone.incrementCallCount();
				//_phone.setNumber("+7098765443"); // Caused by: org.hibernate.StaleObjectStateException: Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect) : [by.pva.hibernate.part01.locking.optimistic.Phone24#1]
				System.out.println("Bob changes the Phone call count");
			});
			
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Call count was incremented by Bob, but when alice's changes will be applied 
			// the "lost update" will happen.
			// See the database.

			System.out.println("Alice changes the Phone number");
			
		});
		
		entityManagerFactory.close();
		
	}
}

@Entity(name = "Phone24")
@Table(name = "Phones24")
class Phone24 {

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