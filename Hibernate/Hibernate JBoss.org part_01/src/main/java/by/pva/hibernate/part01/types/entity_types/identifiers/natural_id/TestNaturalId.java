package by.pva.hibernate.part01.types.entity_types.identifiers.natural_id;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.annotations.NaturalId;

import by.pva.hibernate.part01._myUtils.BaseTest;

// The additional select statement to get the primary key for the provided natural ID comes as a surprise
// in the beginning. But this should not be a performance issue, if you consider, that you normally add
// a database index to your natural identifier column. As soon as Hibernate knows the mapping between 
// the natural id and the primary key, it can use the known optimization and caching mechanisms.

public class TestNaturalId extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Query query = entityManager.createQuery("delete from Book7");
			query.executeUpdate();

			Book book = new Book();
			book.setId(1L);
			book.setAuthor("Author");
			book.setTitle("Title");
			book.setIsbn("978-9730228236");

			entityManager.persist(book);

			entityManager.flush();
			entityManager.clear();

			Book book2 = entityManager.unwrap(Session.class).byNaturalId(Book.class).using("isbn", "978-9730228236")
					.load(); // or getReference()

//		or
//		Book book2 = entityManager
//			.unwrap(Session.class)
//			.bySimpleNaturalId(Book.class)
//			.load("978-9730228236");

			System.out.println(book2);

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Book7")
@Table(name = "Books7")
//@NaturalIdCache
class Book {

	@Id
	private Long id;
	private String title;
	private String author;
	@NaturalId(mutable = false) // mutable = false is the default value.
	private String isbn;

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

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + "]";
	}

}
