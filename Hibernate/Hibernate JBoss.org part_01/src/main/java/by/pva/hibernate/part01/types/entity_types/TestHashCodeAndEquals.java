package by.pva.hibernate.part01.types.entity_types;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;

public class TestHashCodeAndEquals {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		Book5 book5 = new Book5();
		book5.setId(1L);
		book5.setAuthor("Author");
		book5.setTitle("Title");
		
		Library library = new Library();
		library.setId(1L);
		library.setName("library");
		library.setBooks(new HashSet<>());
		library.getBooks().add(book5);
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(library);
		
		Book5 book51 = entityManager.find(Book5.class, 1L);
		Book5 book52 = entityManager.find(Book5.class, 1L);
		
		// Set<Book> set - new HashSet<>(); set.add(book51); set.add(book52); set.size == 1? - true;
		
		System.out.println("book51 == book52: " + (book51 == book52));
		
		entityManager.remove(library);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}

@Entity(name = "Libraries")
class Library {

	@Id
	private Long id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "library_id")
	private Set<Book5> books = new HashSet<>();

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

	public Set<Book5> getBooks() {
		return books;
	}

	public void setBooks(Set<Book5> books) {
		this.books = books;
	}

}

@Entity(name = "books5")
class Book5 {

	@Id
	private Long id;
	private String title;
	private String author;

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
