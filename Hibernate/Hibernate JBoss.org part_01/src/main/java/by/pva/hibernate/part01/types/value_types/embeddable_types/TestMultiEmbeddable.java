package by.pva.hibernate.part01.types.value_types.embeddable_types;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;

import org.hibernate.annotations.NaturalId;

public class TestMultiEmbeddable {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");
		
		Book2 book2 = new Book2();
		Country country1 = new Country("Belarus");
		Country country2 = new Country("Germany");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(country1);
		entityManager.persist(country2);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		Author author1 = new Author("Author1", country1);
		Author author2 = new Author("Author2", country2);
		book2.setTitle("BookTitle");
		book2.setAuthor1(author1);
		book2.setAuthor2(author2);
		
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(book2);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}

@Entity(name = "books2")
@AttributeOverrides({
	@AttributeOverride(
		name = "author1.name",
		column = @Column(name = "author1_name")
	),
	@AttributeOverride(
		name = "author2.name",
		column = @Column(name = "author2_name")
	)
})
@AssociationOverrides({
	@AssociationOverride(
		name = "author1.country",
		joinColumns = @JoinColumn(name = "author1_country_id")
	),
	@AssociationOverride(
		name = "author2.country",
		joinColumns = @JoinColumn(name = "author2_country_id")
	)
})
class Book2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private Author author1;
	private Author author2;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Author getAuthor1() {
		return author1;
	}
	public void setAuthor1(Author author1) {
		this.author1 = author1;
	}
	public Author getAuthor2() {
		return author2;
	}
	public void setAuthor2(Author author2) {
		this.author2 = author2;
	}

}

@Embeddable
class Author {

	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	private Country country;
	@SuppressWarnings("unused")
	private Author() {
	}
	
	public Author(String name, Country country) {
		this.name = name;
		this.country = country;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}

@Entity(name = "Countries")
class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NaturalId
	private String name;
	
	@SuppressWarnings("unused")
	private Country() {
	}
	
	public Country(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
}