package by.pva.hibernate.part01.types.entity_types.identifiers.naturalID;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.annotations.NaturalId;

public class TestNaturalIDNaturalUsingMultiplePersistentAttributes {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Query query = entityManager.createQuery("delete from Book9");
		Query query2 = entityManager.createQuery("delete from Publisher2");
		query.executeUpdate();
		query2.executeUpdate();

		Publisher publisher = new Publisher();
		publisher.setId(1L);
		publisher.setName("Publisher");
		
		Book9 book = new Book9();
		book.setId(1L);
		book.setAuthor("Author");
		book.setTitle("Title");
		book.setProductNumber("973022823X");
		book.setPublisher(publisher);

		entityManager.persist(book);

		entityManager.flush();
		entityManager.clear();

		Book9 book2 = entityManager
					.unwrap(Session.class)
					.byNaturalId(Book9.class)
					.using("productNumber", "973022823X")
					.using("publisher", publisher)
					.load();

		System.out.println(book2);

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
	}
}

@Entity(name = "Book9")
@Table(name = "Book9")
class Book9 {

	@Id
	private Long id;
	private String title;
	private String author;
	@NaturalId
	private String productNumber;
	@NaturalId
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Publisher publisher;
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
	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	@Override
	public String toString() {
		return "Book9 [id=" + id + ", title=" + title + ", author=" + author + ", productNumber=" + productNumber
				+ ", publisher=" + publisher + "]";
	}

}

@SuppressWarnings("serial")
@Entity(name = "Publisher2")
@Table(name = "Publishers2")
class Publisher implements Serializable {

	@Id
	private Long id;
	private String name;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Publisher publisher = (Publisher) o;
		return Objects.equals(id, publisher.id) && Objects.equals(name, publisher.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	@Override
	public String toString() {
		return "Publisher [name=" + name + "]";
	}
	
}
