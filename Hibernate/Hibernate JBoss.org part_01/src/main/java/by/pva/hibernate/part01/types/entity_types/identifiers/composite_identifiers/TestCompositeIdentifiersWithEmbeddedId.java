package by.pva.hibernate.part01.types.entity_types.identifiers.composite_identifiers;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import by.pva.hibernate.part01._myUtils.BaseTest;

// another example - https://www.concretepage.com/hibernate/example-embeddedid-hibernate.
public class TestCompositeIdentifiersWithEmbeddedId extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			PK pk = new PK("subsystem", "username");
			SystemUser user = new SystemUser();
			user.setPk(pk);
			user.setName("Name");

			entityManager.persist(user);

		});

		entityManagerFactory.close();
	}
}

@Entity(name = "SystemUsers")
class SystemUser {

	@EmbeddedId
	private PK pk;
	private String name;

	public PK getPk() {
		return pk;
	}

	public void setPk(PK pk) {
		this.pk = pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

@SuppressWarnings("serial")
@Embeddable
class PK implements Serializable {

	private String subsystem;
	private String username;

	public PK(String subsystem, String username) {
		this.subsystem = subsystem;
		this.username = username;
	}

	@SuppressWarnings("unused")
	private PK() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PK pk = (PK) o;
		return Objects.equals(subsystem, pk.subsystem) && Objects.equals(username, pk.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(subsystem, username);
	}
}
