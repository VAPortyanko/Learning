package by.pva.hibernate.part01.types.entity_types.identifiers.composite_identifiers;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestCompositeIdentifiersWithEmbeddedId2 {
	public static void main(String[] args) {
		
		Subsystem subsystem = new Subsystem();
		subsystem.setId("Subsystem");
		subsystem.setDescription("Description");
		PK2 pk2 = new PK2(subsystem, "username");
		SystemUser2 user2 = new SystemUser2();
		user2.setPk2(pk2);
		user2.setName("Name");

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("delete from SystemUsers2");
		Query query2 = entityManager.createQuery("delete from Subsystems");
		query.executeUpdate();
		query2.executeUpdate();
		entityManager.persist(subsystem);
		entityManager.persist(user2);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}

@Entity(name = "SystemUsers2")
class SystemUser2 {

	@EmbeddedId
	private PK2 pk;
	private String name;
	
	public PK2 getPk2() {
		return pk;
	}
	public void setPk2(PK2 pk2) {
		this.pk = pk2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}

@Entity(name = "Subsystems")
class Subsystem {

	@Id
	private String id;

	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}

@SuppressWarnings("serial")
@Embeddable
class PK2 implements Serializable {

	@ManyToOne(fetch = FetchType.LAZY)
	private Subsystem subsystem;

	private String username;

	public PK2(Subsystem subsystem, String username) {
		this.subsystem = subsystem;
		this.username = username;
	}

	@SuppressWarnings("unused")
	private PK2() {
	}

	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		PK2 pk = (PK2) o;
		return Objects.equals( subsystem, pk.subsystem ) &&
				Objects.equals( username, pk.username );
	}

	@Override
	public int hashCode() {
		return Objects.hash( subsystem, username );
	}
}