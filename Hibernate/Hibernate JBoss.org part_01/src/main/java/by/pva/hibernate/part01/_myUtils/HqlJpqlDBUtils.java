package by.pva.hibernate.part01._myUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.persistence.Query;

import _by.pva.hibernate.part01.hql_jpql.domain_model.AddressType;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Call;
import _by.pva.hibernate.part01.hql_jpql.domain_model.CreditCardPayment;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Partner;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Phone;
import _by.pva.hibernate.part01.hql_jpql.domain_model.PhoneType;

//Don't forget to close the entityManagerFactory.
public class HqlJpqlDBUtils extends BaseTest{
	
	public static final boolean IS_DATABASE_EXIST = true;
	
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
			phone1_1.setPerson(person1);
			phone1_2.setId(2L);
			phone1_2.setNumber("+375 (33) 012-38-22");
			phone1_2.setType(PhoneType.MOBILE);
			phone1_2.setPerson(person1);
			phone2_1.setId(3L);
			phone2_1.setNumber("+375 (33) 675-00-01");
			phone2_1.setType(PhoneType.LAND_LINE);
			phone2_1.setPerson(person2);
			phone2_2.setId(4L);
			phone2_2.setNumber("+375 (33) 612-11-29");
			phone2_2.setType(PhoneType.MOBILE);
			phone2_2.setPerson(person2);
			phone3_1.setId(5L);
			phone3_1.setNumber("+375 (33) 222-45-45");
			phone3_1.setType(PhoneType.LAND_LINE);
			phone3_1.setPerson(person3);
			phone3_2.setId(6L);
			phone3_2.setNumber("+375 (33) 489-01-41");
			phone3_2.setType(PhoneType.MOBILE);
			phone3_2.setPerson(person3);
			phone4_1.setId(7L);
			phone4_1.setNumber("+375 (33) 656-13-53");
			phone4_1.setType(PhoneType.LAND_LINE);
			phone4_2.setId(8L);
			phone4_1.setPerson(person4);
			phone4_2.setNumber("+375 (33) 111-11-11");
			phone4_2.setType(PhoneType.MOBILE);
			phone4_2.setPerson(person4);
			phone5_1.setId(9L);
			phone5_1.setNumber("+375 (33) 222-22-22");
			phone5_1.setType(PhoneType.LAND_LINE);
			phone5_1.setPerson(person5);
			phone5_2.setId(10L);
			phone5_2.setNumber("+375 (33) 101-10-01");
			phone5_2.setType(PhoneType.MOBILE);
			phone5_2.setPerson(person5);
			
			Call call_1_1_1 = new Call();
			Call call_1_1_2 = new Call();
			Call call_1_2_1 = new Call();
			Call call_1_2_2 = new Call();
			Call call_2_1_1 = new Call();
			Call call_2_1_2 = new Call();
			Call call_2_2_1 = new Call();
			Call call_2_2_2 = new Call();
			Call call_3_1_1 = new Call();
			Call call_3_1_2 = new Call();
			Call call_3_2_1 = new Call();
			Call call_3_2_2 = new Call();
			Call call_4_1_1 = new Call();
			Call call_4_1_2 = new Call();
			Call call_4_2_1 = new Call();
			Call call_4_2_2 = new Call();
			Call call_5_1_1 = new Call();
			Call call_5_1_2 = new Call();
			Call call_5_2_1 = new Call();
			Call call_5_2_2 = new Call();
			
			call_1_1_1.setId(1L);
			call_1_1_1.setPhone(phone1_1);
			call_1_1_1.setDuration(10);
			call_1_1_1.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 20, 22).toInstant(ZoneOffset.UTC)));
			call_1_1_2.setId(2L);
			call_1_1_2.setPhone(phone1_1);
			call_1_1_2.setDuration(15);
			call_1_1_2.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 23, 22).toInstant(ZoneOffset.UTC)));
			call_1_2_1.setId(3L);
			call_1_2_1.setPhone(phone1_2);
			call_1_2_1.setDuration(80);
			call_1_2_1.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 24, 22).toInstant(ZoneOffset.UTC)));
			call_1_2_2.setId(4L);
			call_1_2_2.setPhone(phone1_2);
			call_1_2_2.setDuration(70);
			call_1_2_2.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 25, 22).toInstant(ZoneOffset.UTC)));
			call_2_1_1.setId(5L);
			call_2_1_1.setPhone(phone2_1);
			call_2_1_1.setDuration(10);
			call_2_1_1.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 26, 22).toInstant(ZoneOffset.UTC)));
			call_2_1_2.setId(6L);
			call_2_1_2.setPhone(phone2_1);
			call_2_1_2.setDuration(90);
			call_2_1_2.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 26, 22).toInstant(ZoneOffset.UTC)));
			call_2_2_1.setId(7L);
			call_2_2_1.setPhone(phone2_2);
			call_2_2_1.setDuration(30);
			call_2_2_1.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 27, 22).toInstant(ZoneOffset.UTC)));
			call_2_2_2.setId(8L);
			call_2_2_2.setPhone(phone2_2);
			call_2_2_2.setDuration(30);
			call_2_2_2.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 28, 22).toInstant(ZoneOffset.UTC)));
			call_3_1_1.setId(9L);
			call_3_1_1.setPhone(phone3_1);
			call_3_1_1.setDuration(55);
			call_3_1_1.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 29, 22).toInstant(ZoneOffset.UTC)));
			call_3_1_2.setId(10L);
			call_3_1_2.setPhone(phone3_1);
			call_3_1_2.setDuration(45);
			call_3_1_2.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 30, 22).toInstant(ZoneOffset.UTC)));
			call_3_2_1.setId(11L);
			call_3_2_1.setPhone(phone3_2);
			call_3_2_1.setDuration(70);
			call_3_2_1.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 31, 22).toInstant(ZoneOffset.UTC)));
			call_3_2_2.setId(12L);
			call_3_2_2.setPhone(phone3_2);
			call_3_2_2.setDuration(20);
			call_3_2_2.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 32, 22).toInstant(ZoneOffset.UTC)));
			call_4_1_1.setId(13L);
			call_4_1_1.setPhone(phone4_1);
			call_4_1_1.setDuration(90);
			call_4_1_1.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 33, 22).toInstant(ZoneOffset.UTC)));
			call_4_1_2.setId(14L);
			call_4_1_2.setPhone(phone4_1);
			call_4_1_2.setDuration(80);
			call_4_1_2.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 34, 22).toInstant(ZoneOffset.UTC)));
			call_4_2_1.setId(15L);
			call_4_2_1.setPhone(phone4_2);
			call_4_2_1.setDuration(70);
			call_4_2_1.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 35, 22).toInstant(ZoneOffset.UTC)));
			call_4_2_2.setId(16L);
			call_4_2_2.setPhone(phone4_2);
			call_4_2_2.setDuration(60);
			call_4_2_2.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 36, 22).toInstant(ZoneOffset.UTC)));
			call_5_1_1.setId(17L);
			call_5_1_1.setPhone(phone5_1);
			call_5_1_1.setDuration(50);
			call_5_1_1.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 37, 22).toInstant(ZoneOffset.UTC)));
			call_5_1_2.setId(18L);
			call_5_1_2.setPhone(phone5_1);
			call_5_1_2.setDuration(40);
			call_5_1_2.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 38, 22).toInstant(ZoneOffset.UTC)));
			call_5_2_1.setId(19L);
			call_5_2_1.setPhone(phone5_2);
			call_5_2_1.setDuration(30);
			call_5_2_1.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 39, 22).toInstant(ZoneOffset.UTC)));
			call_5_2_2.setId(20L);
			call_5_2_2.setPhone(phone5_2);
			call_5_2_2.setDuration(20);
			call_5_2_2.setTimestamp(Timestamp.from(LocalDateTime.of(2021, 2, 20, 19, 40, 22).toInstant(ZoneOffset.UTC)));
			
			person1.getPhones().add(phone1_1);
			person1.getPhones().add(phone1_2);
			person2.getPhones().add(phone2_1);
			person2.getPhones().add(phone2_2);
			person3.getPhones().add(phone3_1);
			person3.getPhones().add(phone3_2);
			person4.getPhones().add(phone4_1);
			person4.getPhones().add(phone4_2);
			person5.getPhones().add(phone5_1);
			person5.getPhones().add(phone5_2);
			
			phone1_1.getCalls().add(call_1_1_1);
			phone1_1.getCalls().add(call_1_1_2);
			phone1_2.getCalls().add(call_1_2_1);
			phone1_2.getCalls().add(call_1_2_2);
			phone2_1.getCalls().add(call_2_1_1);
			phone2_1.getCalls().add(call_2_1_2);
			phone2_2.getCalls().add(call_2_2_1);
			phone2_2.getCalls().add(call_2_2_2);
			phone3_1.getCalls().add(call_3_1_1);
			phone3_1.getCalls().add(call_3_1_2);
			phone3_2.getCalls().add(call_3_2_1);
			phone3_2.getCalls().add(call_3_2_2);
			phone4_1.getCalls().add(call_4_1_1);
			phone4_1.getCalls().add(call_4_1_2);
			phone4_2.getCalls().add(call_4_2_1);
			phone4_2.getCalls().add(call_4_2_2);
			phone5_1.getCalls().add(call_5_1_1);
			phone5_1.getCalls().add(call_5_1_2);
			phone5_2.getCalls().add(call_5_2_1);
			phone5_2.getCalls().add(call_5_2_2);
			
			Partner partner1 = new Partner();
			partner1.setId(1L);
			partner1.setName("Partner1");
			Partner partner2 = new Partner();
			partner2.setId(2L);
			partner2.setName("Partner2");
			
			CreditCardPayment creditcardPayment = new CreditCardPayment();
			creditcardPayment.setId(1L);
			creditcardPayment.setPerson(person1);
			creditcardPayment.setAmount(BigDecimal.ONE);
			creditcardPayment.setCompleted(false);
			
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
			
			entityManager.persist(call_1_1_1);
			entityManager.persist(call_1_1_2);
			entityManager.persist(call_1_2_1);
			entityManager.persist(call_1_2_2);
			entityManager.persist(call_2_1_1);
			entityManager.persist(call_2_1_2);
			entityManager.persist(call_2_2_1);
			entityManager.persist(call_2_2_2);
			entityManager.persist(call_3_1_1);
			entityManager.persist(call_3_1_2);
			entityManager.persist(call_3_2_1);
			entityManager.persist(call_3_2_2);
			entityManager.persist(call_4_1_1);
			entityManager.persist(call_4_1_2);
			entityManager.persist(call_4_2_1);
			entityManager.persist(call_4_2_2);
			entityManager.persist(call_5_1_1);
			entityManager.persist(call_5_1_2);
			entityManager.persist(call_5_2_1);
			entityManager.persist(call_5_2_2);
			
			entityManager.persist(partner1);
			entityManager.persist(partner2);
			
			entityManager.persist(creditcardPayment);
		});
	}
	
	public static void clearHqlJpqlDB() {
		doInJPA(entityManager -> {
			Query query1 = entityManager.createQuery("delete from Call");
			query1.executeUpdate();
			
			
			Query query2_1 = entityManager.createNativeQuery("delete from phone27_repairtimestamps");
			Query query2_2 = entityManager.createQuery("delete from Phone27");
			query2_1.executeUpdate();
			query2_2.executeUpdate();
			
			Query query3 = entityManager.createNativeQuery("delete from Person44_addresses");
			Query query4 = entityManager.createQuery("delete from Person44");
			query3.executeUpdate();
			query4.executeUpdate();
			
			Query query5 = entityManager.createQuery("delete from Partner");
			query5.executeUpdate();
			
			Query query6 = entityManager.createQuery("delete from Payment");
			query6.executeUpdate();
			
			Query query7 = entityManager.createQuery("delete from Partner");
			query7.executeUpdate();
			
		});
	}
	
	public static void prepareDomainModel() {
		if (!IS_DATABASE_EXIST) {
			clearHqlJpqlDB();
			populateHqlJpqlDB();
		}
	}
	
}
