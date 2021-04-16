package by.pva.hibernate.part01.types.entity_types;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.annotations.Proxy;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestCustomEntityProxy extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			EBook book = new EBook();
			book.setId(1L);
			book.setTitle("High-Performance Java Persistence");
			book.setAuthor("Vlad Mihalcea");

			entityManager.unwrap(Session.class).persist(book);
			
			entityManager.flush();
			entityManager.clear();
			
			book = entityManager.unwrap(Session.class).getReference(EBook.class, 1L);
			
			System.out.println("book instanceof Identifiable: " + (book instanceof Identifiable));
			System.out.println("book instanceof EBook:        " + (book instanceof EBook));
			
			entityManager.remove(book);
			
			
		});

		entityManagerFactory.close();
	}

}

interface Identifiable {
	Long getId();
	void setId(Long id);
}

@Entity(name = "EBook")
@Table(name = "ebooks")
//The @Proxy annotation is used to specify a custom proxy implementation for the current annotated entity.
@Proxy(proxyClass = Identifiable.class) 
final class EBook implements Identifiable {

	@Id
	private Long id;
	private String title;
	private String author;

	@Override
	public Long getId() {
		return id;
	}
	@Override
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