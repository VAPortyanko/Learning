package by.pva.hibernate.part01.inheritance.inheritanceStrategies.implicitAndExplicitPolymorphism;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

public class TestImplicitAndExplicitPolymorphism {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Query query = entityManager.createQuery("delete from Book10");
		query.executeUpdate();
		Query query2 = entityManager.createQuery("delete from Blog");
		query2.executeUpdate();

		Book book = new Book();
		book.setId(1L);
		book.setAuthor("Vlad Mihalcea");
		book.setTitle("High-Performance Java Persistence");
		entityManager.persist(book);

		Blog blog = new Blog();
		blog.setId(1L);
		blog.setSite("vladmihalcea.com");
		entityManager.persist(blog);

		entityManager.flush();
		entityManager.clear();
		
		// We can now query against the DomainModelEntity interface, and Hibernate is going to fetch only the entities 
		// that are either mapped with @Polymorphism(type = PolymorphismType.IMPLICIT) or they are not annotated at all
		// with the @Polymorphism annotation (implying the IMPLICIT behavior)
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<DomainModelEntity> accounts = entityManager
				.createQuery("select e " + "from by.pva.hibernate.part01.inheritance.inheritanceStrategies.implicitAndExplicitPolymorphism.DomainModelEntity e")
				.getResultList();

		System.out.println("Size of resultSet = " + accounts.size());
		accounts.stream().forEach(System.out::println);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}

}

interface DomainModelEntity<ID> {
	ID getId();
	Integer getVersion();
}

@Entity(name = "Book10")
@Table(name = "Books10")
class Book implements DomainModelEntity<Long> {

	@Id
	Long id;
	@Version
	Integer version;
	String title;
	String author;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
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
	@Override
	public String toString() {
		return "Book [id=" + id + ", version=" + version + ", title=" + title + ", author=" + author + "]";
	}

}

@Entity(name = "Blog")
@Table(name = "Blogs")
@Polymorphism(type = PolymorphismType.EXPLICIT)
class Blog implements DomainModelEntity<Long> {

	@Id
	private Long id;
	@Version
	private Integer version;
	private String site;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", version=" + version + ", site=" + site + "]";
	}

}