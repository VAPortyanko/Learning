package by.pva.hibernate.part01.caching;

import static by.pva.hibernate.part01._myUtils.MyUtils.doInHibernateWithDefaultPersistanceUnit;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Cacheable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CacheConcurrencyStrategy;

public class TestEntityCache {
	public static void main(String[] args) {
		
		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.cache.use_second_level_cache", "true");
		properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.jcache.JCacheRegionFactory");
		properties.put("hibernate.javax.cache.provider", "org.ehcache.jsr107.EhcacheCachingProvider");
		
		
		doInHibernateWithDefaultPersistanceUnit(entityManager -> {

			Query query1 = entityManager.createQuery("Delete from Phone25");
			query1.executeUpdate();
			
			Phone phone = new Phone();
			phone.setId(1L);
			phone.setNumber("+37529 567-98-11");
			entityManager.persist(phone);
			
			entityManager.flush();
			entityManager.clear();
			
			Phone phone1 = entityManager.find(Phone.class, 1L);
			entityManager.flush();
			entityManager.clear();
			Phone phone2 = entityManager.find(Phone.class, 1L);
			entityManager.flush();
			entityManager.clear();
			Phone phone3 = entityManager.find(Phone.class, 1L);
			
		}, properties);
	}
}

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity(name = "Phone25")
@Table(name = "Phones25")
class Phone {

	@Id
	private Long id;
	private String number;
//	@ManyToOne
//	private Person person;
	@Version
//  private int version;
	
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
	//	public Person getPerson() {
//		return person;
//	}
//	public void setPerson(Person person) {
//		this.person = person;
//	}
//	public int getVersion() {
//		return version;
//	}

}

//@Entity(name = "Person41")
//@Table(name = "Persons41")
//class Person{
//	@Id
//	private long id;
//	private String name;
//	public long getId() {
//		return id;
//	}
//	public void setId(long id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//}