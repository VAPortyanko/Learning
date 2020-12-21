package by.pva.hibernate.part01.types.entity_types;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

public class TestAccessStrategy {
	
	public static void main(String[] args) {

	}
}

@Entity(name = "animals1")
class Animal1 {
	@Id // Field based access.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

@Entity(name = "animals2")
class Animal2 {
	private Long id;
	private String name;
	@Access(AccessType.FIELD) // Overriding the access type for the "version" attribute. Getters and setters are not needed.
	@Version
	private int version;

	@Id // Property based access.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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