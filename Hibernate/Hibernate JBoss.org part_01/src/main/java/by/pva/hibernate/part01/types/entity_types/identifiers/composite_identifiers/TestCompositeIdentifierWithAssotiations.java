package by.pva.hibernate.part01.types.entity_types.identifiers.composite_identifiers;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestCompositeIdentifierWithAssotiations {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		Author author = new Author();
		author.setName("Author");
		Publisher publisher = new Publisher();
		publisher.setName("Publisher");
		
		Book book = new Book(author, publisher, "title");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("delete from Books6");
		query.executeUpdate();
		query = entityManager.createQuery("delete from Publishers");
		query.executeUpdate();
		query = entityManager.createQuery("delete from Authors");
		query.executeUpdate();
				
		entityManager.persist(author);
		entityManager.persist(publisher);
		entityManager.persist(book);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}
}

@Entity(name = "Books6")
class Book implements Serializable {

	private static final long serialVersionUID = -5161877909819631685L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	private Author author;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	private Publisher publisher;

	@Id
	private String title;

	public Book(Author author, Publisher publisher, String title) {
		this.author = author;
		this.publisher = publisher;
		this.title = title;
	}

	@SuppressWarnings("unused")
	private Book() {
	}

	// Getters and setters are omitted for brevity

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Book book = (Book) o;
		return Objects.equals(author, book.author) && Objects.equals(publisher, book.publisher)
				&& Objects.equals(title, book.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, publisher, title);
	}
}

@Entity(name = "Authors")
class Author implements Serializable {

	private static final long serialVersionUID = 6275878786341751344L;

	@Id
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Author author = (Author) o;
		return Objects.equals(name, author.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}

@Entity(name = "Publishers")
class Publisher implements Serializable {

	private static final long serialVersionUID = 5764426216828202377L;
	@Id
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Publisher publisher = (Publisher) o;
		return Objects.equals(name, publisher.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
