package by.pva.hibernate.part01.test;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

public class Test {
	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		User user1 = new User();
		user1.setName("User1");

		EntityA entityA = new EntityA();
		EntityB entityB = new EntityB();

		entityB.setName("EntityB");
		entityB.setUser(user1);

		entityA.setName("EntityA");
		entityA.setUser(user1);
		entityA.setEntityB(entityB);

		entityManager.persist(entityA);

		entityManager.flush();
		entityManager.clear();

		System.out.println(entityManager.find(EntityA.class, 1L));

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}
}

@Entity(name = "EntityA")
@Table(name = "EntitiesA")
class EntityA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;
	@OneToOne
	@JoinFormula(value = "(SELECT e.id FROM EntityB e WHERE e.user_id = user_id)", referencedColumnName = "id")
	private EntityB entityB;
	private String name;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EntityB getEntityB() {
		return entityB;
	}

	public void setEntityB(EntityB entityB) {
		this.entityB = entityB;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "EntityA [id=" + id + ", user=" + user.getName() + ", entityB=" + entityB.getName() + ", name=" + name
				+ "]";
	}

}

@Entity(name = "EntityB")
@Table(name = "EntitiesB")
class EntityB {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;
	private String name;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

}

@Entity(name = "User3")
@Table(name = "Users3")
class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}