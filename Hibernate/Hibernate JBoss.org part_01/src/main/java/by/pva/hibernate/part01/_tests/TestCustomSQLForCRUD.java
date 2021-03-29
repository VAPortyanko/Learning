package by.pva.hibernate.part01._tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.SQLUpdate;
import org.hibernate.annotations.Where;

import org.hibernate.annotations.ResultCheckStyle;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class TestCustomSQLForCRUD extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			// DDL for the example:
			entityManager.createNativeQuery("drop table crudperson_phones").executeUpdate();
			entityManager.createNativeQuery("drop table crudpersons").executeUpdate();
			entityManager.createNativeQuery("CREATE TABLE `crudpersons` (\r\n" + 
					"  `id` bigint(20) NOT NULL,\r\n" + 
					"  `name` varchar(255) DEFAULT NULL,\r\n" + 
					"  `valid` bit(1) DEFAULT NULL,\r\n" + 
					"  PRIMARY KEY (`id`)\r\n" + 
					") ENGINE=InnoDB DEFAULT CHARSET=latin1;\r\n" + 
					"").executeUpdate();
			entityManager.createNativeQuery("CREATE TABLE `crudperson_phones` (\r\n" + 
					"  `CRUDPerson_id` bigint(20) NOT NULL,\r\n" + 
					"  `phones` varchar(255) DEFAULT NULL,\r\n" + 
					"  `valid` bit(1) DEFAULT NULL,\r\n" + 
					"  KEY `FKb7dqj33o0pvlmubrtlwjkfmnw` (`CRUDPerson_id`),\r\n" + 
					"  CONSTRAINT `FKb7dqj33o0pvlmubrtlwjkfmnw` FOREIGN KEY (`CRUDPerson_id`) REFERENCES `crudpersons` (`id`)\r\n" + 
					") ENGINE=InnoDB DEFAULT CHARSET=latin1;\r\n" + 
					"").executeUpdate();
			
			// Example ("Soft deleting"):
			entityManager.createNativeQuery("delete from crudperson_phones").executeUpdate();
			entityManager.createNativeQuery("delete from crudpersons").executeUpdate();			
			final Long ID = 1L;
			
			CrudPerson person = new CrudPerson();
			person.setId(ID);
			person.setName("Vasia");
			person.setPhones(Arrays.asList("+375(29) 457-87-72"));
			
			entityManager.persist(person);
			
			entityManager.flush();
			entityManager.clear();
			
			CrudPerson person2 = entityManager.find(CrudPerson.class, ID);
			person2.setName("Petia");
			person2.getPhones().set(0, "+375(44) 222-33-44"); // The old phone number will be marked as [valid=false].  
			
			entityManager.flush();
			entityManager.clear();
			
			CrudPerson person3 = entityManager.find(CrudPerson.class, ID);
			entityManager.remove(person3); // The person and the new phone number will be marked as [valid=false].
			
		});

		entityManagerFactory.close();
	}
	
}

@SQLInsert(
		sql = "INSERT INTO crudpersons (name, id, valid) VALUES (?, ?, true) ",
		check = ResultCheckStyle.COUNT
	)
	@SQLUpdate(
		sql = "UPDATE crudpersons SET name = ? where id = ? "
	)
	@SQLDelete(
		sql = "UPDATE crudpersons SET valid = false WHERE id = ? "
	)
	@Loader(namedQuery = "find_valid_person")
	@NamedNativeQueries({
		@NamedNativeQuery(
			name = "find_valid_person",
			query = "SELECT id, name " +
					"FROM crudpersons " +
					"WHERE id = ? and valid = true",
			resultClass = CrudPerson.class
		)
	})
@Entity(name = "CRUDPerson")
@Table(name = "CrudPersons")
class CrudPerson {

	@Id
	private Long id;
	private String name;
	@ElementCollection
	@SQLInsert(
		sql = "INSERT INTO crudperson_phones (crudperson_id, phones, valid) VALUES (?, ?, true) ")
	@SQLDeleteAll(
		sql = "UPDATE crudperson_phones SET valid = false WHERE crudperson_id = ?")
	@Where( clause = "valid = true" )
	private List<String> phones = new ArrayList<>();	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
	
}