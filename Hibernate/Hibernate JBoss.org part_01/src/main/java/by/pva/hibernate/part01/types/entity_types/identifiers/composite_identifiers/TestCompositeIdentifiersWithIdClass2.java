package by.pva.hibernate.part01.types.entity_types.identifiers.composite_identifiers;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Query;
import javax.persistence.SequenceGenerator;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestCompositeIdentifiersWithIdClass2 extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			SystemUser4 user4 = new SystemUser4();
			user4.setId(new PK4("subsystem", "username"));
			user4.setName("Name");

			Query query = entityManager.createQuery("delete from SystemUsers4");
			query.executeUpdate();
			entityManager.persist(user4);
		});

		entityManagerFactory.close();
	}
}

@Entity(name = "SystemUsers4")
@IdClass(PK4.class)
class SystemUser4 {

	@Id
	private String subsystem;
	@Id
	private String username;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
	@SequenceGenerator(name = "users_seq", sequenceName = "SystemUser4Sequence", allocationSize = 10)
	private Integer registrationId;

	private String name;

	public PK4 getId() {
		return new PK4(subsystem, username, registrationId);
	}

	public void setId(PK4 id) {
		this.subsystem = id.getSubsystem();
		this.username = id.getUsername();
		this.registrationId = id.getRegistrationId();
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

	public Integer getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(Integer registrationId) {
		this.registrationId = registrationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

@SuppressWarnings("serial")
class PK4 implements Serializable {

	private String subsystem;
	private String username;
	private Integer registrationId;

	public PK4(String subsystem, String username) {
		this.subsystem = subsystem;
		this.username = username;
	}

	public PK4(String subsystem, String username, Integer registrationId) {
		this.subsystem = subsystem;
		this.username = username;
		this.registrationId = registrationId;
	}

	@SuppressWarnings("unused")
	private PK4() {
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

	public Integer getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(Integer registrationId) {
		this.registrationId = registrationId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PK4 pk = (PK4) o;
		return Objects.equals(subsystem, pk.subsystem) && Objects.equals(username, pk.username)
				&& Objects.equals(registrationId, pk.registrationId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(subsystem, username, registrationId);
	}
}