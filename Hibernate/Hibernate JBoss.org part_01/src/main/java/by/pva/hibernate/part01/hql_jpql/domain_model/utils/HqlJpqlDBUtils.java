package by.pva.hibernate.part01.hql_jpql.domain_model.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.persistence.Query;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.AddressType;
import by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01.hql_jpql.domain_model.Phone;
import by.pva.hibernate.part01.hql_jpql.domain_model.PhoneType;

//Don't forget to close the entityManagerFactory.
public class HqlJpqlDBUtils extends BaseTest{
	
	public static void populateHqlJpqlDB() {
		doInJPA(entityManager -> {
			
			Person person1 = new Person();
			Person person2 = new Person();
			Person person3 = new Person();
			Person person4 = new Person();
			Person person5 = new Person();
			
			person1.setId(1L);
			person2.setId(2L);
			person3.setId(3L);
			person4.setId(4L);
			person5.setId(5L);
			
			person1.setAddress("Earth1");
			person2.setAddress("Earth2");
			person3.setAddress("Earth3");
			person4.setAddress("Earth4");
			person5.setAddress("Earth5");
			
			person1.getAddresses().put(AddressType.HOME  , "Home address1"  );
			person1.getAddresses().put(AddressType.OFFICE, "Office address1");
			person2.getAddresses().put(AddressType.HOME  , "Home address2"  );
			person2.getAddresses().put(AddressType.OFFICE, "Office address2");
			person3.getAddresses().put(AddressType.HOME  , "Home address3"  );
			person3.getAddresses().put(AddressType.OFFICE, "Office address3");
			person4.getAddresses().put(AddressType.HOME  , "Home address4"  );
			person4.getAddresses().put(AddressType.OFFICE, "Office address4");
			person5.getAddresses().put(AddressType.HOME  , "Home address5"  );
			person5.getAddresses().put(AddressType.OFFICE, "Office address5");
			
			person1.setName("name1");
			person2.setName("name2");
			person3.setName("name3");
			person4.setName("name4");
			person5.setName("name5");
			
			person1.setNickName("nickName1");
			person2.setNickName("nickName2");
			person3.setNickName("nickName3");
			person4.setNickName("nickName4");
			person5.setNickName("nickName5");
			
			person1.setCreatedOn(Timestamp.from(LocalDateTime.of(2021, 1, 22, 21, 20, 1).toInstant(ZoneOffset.UTC)));
			person2.setCreatedOn(Timestamp.from(LocalDateTime.of(2021, 1, 22, 21, 20, 2).toInstant(ZoneOffset.UTC)));
			person3.setCreatedOn(Timestamp.from(LocalDateTime.of(2021, 1, 22, 21, 20, 3).toInstant(ZoneOffset.UTC)));
			person4.setCreatedOn(Timestamp.from(LocalDateTime.of(2021, 1, 22, 21, 20, 4).toInstant(ZoneOffset.UTC)));
			person5.setCreatedOn(Timestamp.from(LocalDateTime.of(2021, 1, 22, 21, 20, 5).toInstant(ZoneOffset.UTC)));
			
			Phone phone1_1 = new Phone();
			Phone phone1_2 = new Phone();
			Phone phone2_1 = new Phone();
			Phone phone2_2 = new Phone();
			Phone phone3_1 = new Phone();
			Phone phone3_2 = new Phone();
			Phone phone4_1 = new Phone();
			Phone phone4_2 = new Phone();
			Phone phone5_1 = new Phone();
			Phone phone5_2 = new Phone();
			
			phone1_1.setId(1L);
			phone1_1.setNumber("+375 (33) 675-23-43");
			phone1_1.setType(PhoneType.LAND_LINE);
			phone1_2.setId(2L);
			phone1_2.setNumber("+375 (33) 012-38-22");
			phone1_2.setType(PhoneType.MOBILE);
			phone2_1.setId(3L);
			phone2_1.setNumber("+375 (33) 675-00-01");
			phone2_1.setType(PhoneType.LAND_LINE);
			phone2_2.setId(4L);
			phone2_2.setNumber("+375 (33) 612-11-29");
			phone2_2.setType(PhoneType.MOBILE);
			phone3_1.setId(5L);
			phone3_1.setNumber("+375 (33) 222-45-45");
			phone3_1.setType(PhoneType.LAND_LINE);
			phone3_2.setId(6L);
			phone3_2.setNumber("+375 (33) 489-01-41");
			phone3_2.setType(PhoneType.MOBILE);
			phone4_1.setId(7L);
			phone4_1.setNumber("+375 (33) 656-13-53");
			phone4_1.setType(PhoneType.LAND_LINE);
			phone4_2.setId(8L);
			phone4_2.setNumber("+375 (33) 111-11-11");
			phone4_2.setType(PhoneType.MOBILE);
			phone5_1.setId(9L);
			phone5_1.setNumber("+375 (33) 222-22-22");
			phone5_1.setType(PhoneType.LAND_LINE);
			phone5_2.setId(10L);
			phone5_2.setNumber("+375 (33) 101-10-01");
			phone5_2.setType(PhoneType.MOBILE);
			
			entityManager.persist(person1);
			entityManager.persist(person2);
			entityManager.persist(person3);
			entityManager.persist(person4);
			entityManager.persist(person5);
			
			entityManager.persist(phone1_1);
			entityManager.persist(phone1_2);
			entityManager.persist(phone2_1);
			entityManager.persist(phone2_2);
			entityManager.persist(phone3_1);
			entityManager.persist(phone3_2);
			entityManager.persist(phone4_1);
			entityManager.persist(phone4_2);
			entityManager.persist(phone5_1);
			entityManager.persist(phone5_2);
			
		});
	}
	
	public static void clearHqlJpqlDB() {
		doInJPA(entityManager -> {
			Query query1 = entityManager.createNativeQuery("delete from Person44_addresses");
			Query query2 = entityManager.createQuery("delete from Person44");
			query1.executeUpdate();
			query2.executeUpdate();
			
			Query query3 = entityManager.createQuery("delete from Phone27");
			query3.executeUpdate();
		});
	}
	
	public static void prepareDomainModel() {
		clearHqlJpqlDB();
		populateHqlJpqlDB();
	}
	
}