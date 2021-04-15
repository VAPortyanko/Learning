// Uncomment all to run the test (comment again to run others test in the project).
// Problem: This example has name conflicts that are resolved using the ImplicitNamingStrategy with "component-path"
// Others examples use default ImplicitNamingStrategy and when EntityManagerFactory tries to load(?) entities they encounter with this conflicts.

package by.pva.hibernate.part01.types.value_types.embeddable_types;

//import javax.persistence.Embeddable;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Persistence;
//
//import org.hibernate.annotations.NaturalId;

public class TestMultiEmbeddableWithImplicitNamingStrategy {
	public static void main(String[] args) {
//		EntityManagerFactory entityManagerFactory = Persistence
//				.createEntityManagerFactory("by.pva.hibernate.part01.namingstrategy.implicit_naming_strategy_component_path_impl");
//		
//		Book3 book3 = new Book3();
//		Country2 country2_1 = new Country2("Belarus");
//		Country2 country2_2 = new Country2("Germany");
//		
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		entityManager.getTransaction().begin();
//		entityManager.persist(country2_1);
//		entityManager.persist(country2_2);
//		entityManager.getTransaction().commit();
//		entityManager.close();
//		
//		Author2 author2_1 = new Author2("Author1", country2_1);
//		Author2 author2_2 = new Author2("Author2", country2_2);
//		book3.setTitle("BookTitle");
//		book3.setAuthor1(author2_1);
//		book3.setAuthor2(author2_2);
//		
//		entityManager = entityManagerFactory.createEntityManager();
//		entityManager.getTransaction().begin();
//		entityManager.persist(book3);
//		entityManager.getTransaction().commit();
//		entityManager.close();
//		entityManagerFactory.close();
	}
}
//
//@Entity(name = "Book3")
//@Table(name = "books3")
//class Book3 {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	private String title;
//	private Author2 author1;
//	private Author2 author2;
//	
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public Author2 getAuthor1() {
//		return author1;
//	}
//	public void setAuthor1(Author2 author1) {
//		this.author1 = author1;
//	}
//	public Author2 getAuthor2() {
//		return author2;
//	}
//	public void setAuthor2(Author2 author2) {
//		this.author2 = author2;
//	}
//}
//
//@Embeddable
//class Author2 {
//
//	private String name;
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Country2 country2;
//	@SuppressWarnings("unused")
//	private Author2() {
//	}
//	
//	public Author2(String name, Country2 country2) {
//		this.name = name;
//		this.country2 = country2;
//	}
//	
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Country2 getCountry2() {
//		return country2;
//	}
//
//	public void setCountry2(Country2 country2) {
//		this.country2 = country2;
//	}
//}
//
//@Entity(name = "Countries2")
//class Country2 {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	@NaturalId
//	private String name;
//	@SuppressWarnings("unused")
//	private Country2() {
//	}
//	
//	public Country2(String name) {
//		this.name = name;
//	}
//	
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public Long getId() {
//		return id;
//	}
//}