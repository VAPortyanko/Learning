package by.pva.hibernate.part01.types.entity_types;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestTableAnnotation extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Book book = new Book();
			book.setTitle("Title");
			book.setAuthor("Author");

			entityManager.persist(book);
		});

		entityManagerFactory.close();
	}
}

@Entity(name = "Book1") // The name provided for using in JPQL queries.
@Table(
//	catalog = "public",
//	schema = "store",
		name = "books1" // The table name.
)
class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
