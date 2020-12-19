package by.pva.hibernate.part01.types.entity_types.identifiers.natural_id;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.annotations.NaturalId;

public class TestNaturalIdUsingSingleEmbeddedAttribute {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Query query = entityManager.createQuery("delete from Book8");
		query.executeUpdate();

		Isbn isbn = new Isbn("973022823X",
				             "978-9730228236"); 
		
		Book8 book = new Book8();
		book.setId(1L);
		book.setAuthor("Author");
		book.setTitle("Title");
		book.setIsbn(isbn);

		entityManager.persist(book);

		entityManager.flush();
		entityManager.clear();

		Book8 book2 = entityManager
			.unwrap(Session.class)
			.byNaturalId(Book8.class)
			.using("isbn", new Isbn("973022823X",
					                "978-9730228236"))
			.load();

//      or		
//		Book8 book2 = entityManager
//			.unwrap(Session.class)
//			.bySimpleNaturalId(Book2.class)
//			.load(
//				new Isbn(
//					"973022823X",
//					"978-9730228236"
//				)
//			);


		
		System.out.println(book2);

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}
}

@Entity(name = "Book8")
@Table(name = "Book8")
class Book8 {

	@Id
	private Long id;
	private String title;
	private String author;
	@NaturalId
	@Embedded
	private Isbn isbn;

	public Book8() {
	}
	
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
	public Isbn getIsbn() {
		return isbn;
	}
	public void setIsbn(Isbn isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Book8 [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn.getIsbn10() + ", " + isbn.getIsbn13() + "]";
	}

}

@SuppressWarnings("serial")
@Embeddable
class Isbn implements Serializable {

	private String isbn10;
	private String isbn13;

	public Isbn() {
	}
	
	public Isbn(String isbn10, String isbn13) {
		this.isbn10 = isbn10;
		this.isbn13 = isbn13;
	}

	public String getIsbn10() {
		return isbn10;
	}

	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Isbn isbn = (Isbn) o;
		return Objects.equals(isbn10, isbn.isbn10) && Objects.equals(isbn13, isbn.isbn13);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn10, isbn13);
	}
}
