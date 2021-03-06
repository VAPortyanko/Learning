package by.pva.hibernate.part01.types.value_types.basic_types.mapping.generated_values;

import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestGeneratedValue extends BaseTest {

	public static void main(String[] args) {

		buildEntityManagerFactory(Collections.singletonMap("hibernate.format_sql", "true"));
		
		doInJPA(entityManager -> {

			Person3 person3 = new Person3();
			person3.setFirstName("F");
			person3.setMiddleName1("M1");
			person3.setMiddleName2("M2");
			// person3.setMiddleName3("M3");
			person3.setMiddleName4("M4");
			person3.setMiddleName5("M5");
			person3.setLastName("L");

			entityManager.persist(person3);
			
			entityManager.flush();
			entityManager.clear();
			
			System.out.println("Full name: " + person3.getFullName());
		});

		entityManagerFactory.close();
	}
}

@Entity(name = "Persons3")
class Person3 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	private String lastName;
	private String middleName1;
	private String middleName2;
	private String middleName3;
	private String middleName4;
	private String middleName5;

	@Generated(value = GenerationTime.ALWAYS) // if you will change columnDefinition drop the table before run the test.
	@Column(columnDefinition = " varchar(255) AS (CONCAT(" + "	COALESCE(firstName,'*'), "
			+ "	COALESCE(middleName1, '*'), " + "	COALESCE(middleName2, '*'), " + "	COALESCE(middleName3, '*'), "
			+ "	COALESCE(middleName4, '*'), " + "	COALESCE(middleName5, '*'), " + "	COALESCE(lastName, '*') "
			+ "))")
	private String fullName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName1() {
		return middleName1;
	}

	public void setMiddleName1(String middleName1) {
		this.middleName1 = middleName1;
	}

	public String getMiddleName2() {
		return middleName2;
	}

	public void setMiddleName2(String middleName2) {
		this.middleName2 = middleName2;
	}

	public String getMiddleName3() {
		return middleName3;
	}

	public void setMiddleName3(String middleName3) {
		this.middleName3 = middleName3;
	}

	public String getMiddleName4() {
		return middleName4;
	}

	public void setMiddleName4(String middleName4) {
		this.middleName4 = middleName4;
	}

	public String getMiddleName5() {
		return middleName5;
	}

	public void setMiddleName5(String middleName5) {
		this.middleName5 = middleName5;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
