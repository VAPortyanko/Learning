package by.pva.hibernate.part01.types.entity_types.associations.many_to_one.any;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.*;

public class TestManyToOneAny2 {

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Query query = entityManager.createQuery("delete from Cat");
		Query query2 = entityManager.createQuery("delete from Dog");
		Query query3 = entityManager.createQuery("delete from PetOwner");
		query.executeUpdate();
		query2.executeUpdate();
		query3.executeUpdate();
		
		Cat cat = new Cat();
		cat.setId(1L);
		cat.setName("Barsick");
		
		entityManager.persist(cat);
		
		Dog dog = new Dog();
		dog.setId(1L);
		dog.setName("Rex");
		
		entityManager.persist(dog);
		
		PetOwner petOwner1 = new PetOwner();
		petOwner1.setId(1L);
		petOwner1.setPet(cat);
		PetOwner petOwner2 = new PetOwner();
		petOwner2.setId(2L);
		petOwner2.setPet(dog);
		
		entityManager.persist(petOwner1);
		entityManager.persist(petOwner2);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
	}
}

interface Animal {
	String getName();
	String getAnimalType();
}

@Entity(name = "Cat")
@Table(name = "Cats")
class Cat implements Animal {

	@Id
	private Long id;
	@Column(name = "`name`")
	private String name;
	
	@Override
	public String getAnimalType() {
		return "Cat";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
}

@Entity(name = "Dog")
@Table(name = "Dogs")
class Dog implements Animal {

	@Id
	private Long id;
	@Column(name = "`name`")
	private String name;
	
	@Override
	public String getAnimalType() {
		return "Dog";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
}

@Entity(name = "PetOwner")
@Table(name = "PetOwners")
class PetOwner {

	@Id
	private Long id;
	@Any(metaDef = "PropertyMetaDef2",
		 metaColumn = @Column(name = "animal_type"))
	@AnyMetaDef(name= "PropertyMetaDef2", 
	             metaType = "string",
	             idType = "long",
			     metaValues = {
			            @MetaValue(value = "cat", targetEntity = Cat.class),
			            @MetaValue(value = "dog", targetEntity = Dog.class)
                 }
    )
	@JoinColumn(name = "animal_id")
	private Animal pet;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Animal getPet() {
		return pet;
	}
	public void setPet(Animal pet) {
		this.pet = pet;
	}
}
