package by.pva.hibernate.part01.interceptors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestInterceptor extends BaseTest {

	public static void main(String[] args) {

		SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = sessionFactory.withOptions()
				                        .interceptor(new LoggingInterceptor())
				                        .openSession();
		
		session.getTransaction().begin();

		Customer customer = new Customer();
		customer.setId(1L);
		customer.setName("Name");
		session.save(customer);
		session.flush();
		session.clear();
		
		Customer customer2 = session.get(Customer.class, 1L);
		customer2.setName("Mr. John Doe");
		// Entity Customer#1 changed from [Name, 0] to [Mr. John Doe, 0]

		Query query = session.createQuery("Delete from Customer2");
		query.executeUpdate();
		
		session.getTransaction().commit();
		session.close();
		entityManagerFactory.close();
	}
}

@Entity(name = "Customer2")
@Table(name = "Customers2")
class Customer {
	
	@Id
	private long id;
	private String Name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
}