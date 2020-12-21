package by.pva.hibernate.part01.types.entity_types.associations.many_to_one.join_column_or_formula;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinFormula;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestManyToOneJoinColumnOrFormula extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Query query1 = entityManager.createQuery("delete from User2");
			Query query2 = entityManager.createQuery("delete from Country4");
			query1.executeUpdate();
			query2.executeUpdate();

			Country US = new Country();
			US.setId(1);
			US.setDefault(true);
			US.setPrimaryLanguage("English");
			US.setName("United States");

			Country Romania = new Country();
			Romania.setId(40);
			Romania.setDefault(true);
			Romania.setName("Romania");
			Romania.setPrimaryLanguage("Romanian");

			entityManager.persist(US);
			entityManager.persist(Romania);

			User user1 = new User();
			user1.setId(1L);
			user1.setFirstName("John");
			user1.setLastName("Doe");
			user1.setLanguage("English");
			entityManager.persist(user1);

			User user2 = new User();
			user2.setId(2L);
			user2.setFirstName("Vlad");
			user2.setLastName("Mihalcea");
			user2.setLanguage("Romanian");
			entityManager.persist(user2);

			entityManager.flush();
			entityManager.clear();

			User john = entityManager.find(User.class, 1L);
			System.out.println(john.getCountry().getName());

			User vlad = entityManager.find(User.class, 2L);
			System.out.println(vlad.getCountry().getName());

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "User2")
@Table(name = "users2")
class User {

	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private String language;

	@ManyToOne
	@JoinColumnOrFormula(column = @JoinColumn(name = "language", referencedColumnName = "primaryLanguage", insertable = false, updatable = false))
	@JoinColumnOrFormula(formula = @JoinFormula(value = "true", referencedColumnName = "is_default"))
	private Country country;

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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}

@Entity(name = "Country4")
@Table(name = "countries4")
class Country implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private String name;
	private String primaryLanguage;
	@Column(name = "is_default")
	private boolean _default;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrimaryLanguage() {
		return primaryLanguage;
	}

	public void setPrimaryLanguage(String primaryLanguage) {
		this.primaryLanguage = primaryLanguage;
	}

	public boolean isDefault() {
		return _default;
	}

	public void setDefault(boolean _default) {
		this._default = _default;
	}

}

// CREATE TABLE countries (
//     id INTEGER NOT NULL,
//     is_default boolean,
//     name VARCHAR(255),
//     primaryLanguage VARCHAR(255),
//     PRIMARY KEY ( id )
// )
//
// CREATE TABLE users (
//     id BIGINT NOT NULL,
//     firstName VARCHAR(255),
//     language VARCHAR(255),
//     lastName VARCHAR(255),
//     PRIMARY KEY ( id )
// )
