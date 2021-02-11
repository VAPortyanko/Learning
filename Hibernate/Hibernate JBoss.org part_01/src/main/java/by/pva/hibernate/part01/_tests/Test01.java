package by.pva.hibernate.part01._tests;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.Table;

import org.jboss.logging.Logger;

import by.pva.hibernate.part01._myUtils.BaseTest;
// private final static Logger log = Logger.getLogger("org.hibernate.SQL");
@SuppressWarnings("unused")
public class Test01 extends BaseTest{
	
	public static void main(String[] args) {

		doInJPA(entityManager -> {
			
			Query query = entityManager.createQuery("delete from Test");
			query.executeUpdate();
			
			Test test = new Test();
			test.setId(1L);
			test.setName("Name");
			
			entityManager.persist(test);
			
			entityManager.flush();
			entityManager.clear();
			
			entityManager.find(Test.class, 1L, LockModeType.PESSIMISTIC_WRITE);
		});
		
		entityManagerFactory.close();
	}
}

@Entity(name = "Test")
@Table(name = "Tests")
class Test{
	
	@Id
	private long id;
	private String name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}


