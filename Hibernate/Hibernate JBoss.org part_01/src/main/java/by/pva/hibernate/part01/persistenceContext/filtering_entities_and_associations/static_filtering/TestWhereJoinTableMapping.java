package by.pva.hibernate.part01.persistenceContext.filtering_entities_and_associations.static_filtering;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.WhereJoinTable;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestWhereJoinTableMapping extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Query query = entityManager.createNativeQuery("Delete from book11_reader");
			query.executeUpdate();
			query = entityManager.createQuery("Delete from Book11");
			query.executeUpdate();
			query = entityManager.createQuery("Delete from Reader");
			query.executeUpdate();

			Book book = new Book();
			book.setId(1L);
			book.setTitle("High-Performance Java Persistence");
			book.setAuthor("Vad Mihalcea");
			entityManager.persist(book);

			Reader reader1 = new Reader();
			reader1.setId(1L);
			reader1.setName("John Doe");
			entityManager.persist(reader1);

			Reader reader2 = new Reader();
			reader2.setId(2L);
			reader2.setName("John Doe Jr.");
			entityManager.persist(reader2);

			Query query1 = entityManager.createNativeQuery("INSERT INTO Book11_Reader "
					+ "	(book_id, reader_id, created_on) " + "VALUES " + "	(1, 1, '2021-01-01') ");
			query1.executeUpdate();

			Query query2 = entityManager.createNativeQuery("INSERT INTO Book11_Reader"
					+ "	(book_id, reader_id, created_on)" + "VALUES " + "	(1, 2,  '2019-01-01')");
			query2.executeUpdate();

			entityManager.flush();
			entityManager.clear();

			Book book2 = entityManager.find(Book.class, 1L);
			System.out.println(book2.getCurrentWeekReaders().size());

		});

		entityManagerFactory.close();
	}

}

@Entity(name = "Book11")
@Table(name = "Books11")
class Book {

	@Id
	private Long id;
	private String title;
	private String author;

	@ManyToMany
	@JoinTable(name = "Book11_Reader", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "reader_id"))
	@WhereJoinTable(clause = "created_on > '2020-01-01'")
	private List<Reader> currentWeekReaders = new ArrayList<>();

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

	public List<Reader> getCurrentWeekReaders() {
		return currentWeekReaders;
	}

	public void setCurrentWeekReaders(List<Reader> currentWeekReaders) {
		this.currentWeekReaders = currentWeekReaders;
	}
}

@Entity(name = "Reader")
@Table(name = "Readers")
class Reader {

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

}

// In this example, you need to generate the database structure manually! (Because we need to add the additional column ("created_on") to the join table) 

//create table Book (
//    id bigint not null,
//    author varchar(255),
//    title varchar(255),
//    primary key (id)
//)
//
//create table Book_Reader (
//    book_id bigint not null,
//    reader_id bigint not null
//)
//
//create table Reader (
//    id bigint not null,
//    name varchar(255),
//    primary key (id)
//)
//
//alter table Book_Reader
//    add constraint FKsscixgaa5f8lphs9bjdtpf9g
//    foreign key (reader_id)
//    references Reader
//
//alter table Book_Reader
//    add constraint FKoyrwu9tnwlukd1616qhck21ra
//    foreign key (book_id)
//    references Book
//
//alter table Book_Reader
//    add created_on timestamp
//    default current_timestamp
