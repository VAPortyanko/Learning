package by.pva.hibernate.part01.types.value_types.embeddable_types;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestEmbeddable extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Book book = new Book();
			Publisher publisher = new Publisher();
			Location location = new Location("Country", "City");
			publisher.setLocation(location);
			publisher.setName("PublisherName");
			book.setAuthor("BookAutor");
			book.setTitle("BookTitle");
			book.setPublisher(publisher);

			entityManager.persist(book);
		});

		entityManagerFactory.close();
	}
}

@Entity(name = "Book")
@Table(name = "books")
class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String author;
	@Embedded // This is an optional annotation. 
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

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
}

@Embeddable
class Publisher {

	@Column(name = "publisher_name")
	private String name;
	private Location location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}

@Embeddable
class Location {

	@Column(name = "location_country")
	private String country;
	@Column(name = "location_city")
	private String city;

	public Location(String country, String city) {
		this.country = country;
		this.city = city;
	}

	@SuppressWarnings("unused")
	private Location() {
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
