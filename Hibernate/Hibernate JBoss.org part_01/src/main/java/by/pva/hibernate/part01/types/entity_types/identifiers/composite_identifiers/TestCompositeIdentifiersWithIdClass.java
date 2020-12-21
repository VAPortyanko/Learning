package by.pva.hibernate.part01.types.entity_types.identifiers.composite_identifiers;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Query;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestCompositeIdentifiersWithIdClass extends BaseTest {

	public static void main(String[] args) {

		SystemUser3 user3 = new SystemUser3();
		user3.setId(new PK3("subsystem", "username"));
		user3.setName("name");

		doInJPA(entityManager -> {

			Query query = entityManager.createQuery("delete from SystemUsers3");
			query.executeUpdate();
			entityManager.persist(user3);

		});

		entityManagerFactory.close();
	}
}

@Entity(name = "SystemUsers3")
@IdClass(PK3.class)
class SystemUser3 {

	@Id
	private String subsystem;
	@Id
	private String username;
	private String name;

	public PK3 getId() {
		return new PK3(subsystem, username);
	}

	public void setId(PK3 id) {
		this.subsystem = id.getSubsystem();
		this.username = id.getUsername();
	}

	public String getSubsystem() {
		return subsystem;
	}

	public void setSubsystem(String subsystem) {
		this.subsystem = subsystem;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

@SuppressWarnings("serial")
class PK3 implements Serializable {

	private String subsystem;
	private String username;

	public PK3(String subsystem, String username) {
		this.subsystem = subsystem;
		this.username = username;
	}

	@SuppressWarnings("unused")
	private PK3() {
	}

	public String getSubsystem() {
		return subsystem;
	}

	public void setSubsystem(String subsystem) {
		this.subsystem = subsystem;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PK3 pk = (PK3) o;
		return Objects.equals(subsystem, pk.subsystem) && Objects.equals(username, pk.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(subsystem, username);
	}
}
