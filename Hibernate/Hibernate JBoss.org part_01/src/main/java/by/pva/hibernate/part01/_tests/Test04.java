package by.pva.hibernate.part01._tests;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import by.pva.hibernate.part01._myUtils.BaseTest;

// Persisting an object type member of entity as embeddable (component type) and as value type (binary).  
public class Test04 extends BaseTest{

	public static void main(String[] args) {
		
		doInJPA(entityManager -> {

			entityManager.createQuery("delete from TestEntity1").executeUpdate();
			
			EntityA field1 = new EntityA();
			field1.setNameA("Field1");
			EntityB field2 = new EntityB();
			field2.setNameB("Field2");
			
			TestEntity entity = new TestEntity();
			entity.setId(1L);
			entity.setField1(field1);
			entity.setField2(field2);
			
			entityManager.persist(entity);
			
			entityManager.flush();
			entityManager.clear();
			
			System.out.println(entityManager.find(TestEntity.class, 1L).getField2().getNameB());
			
		});

		entityManagerFactory.close();

	}
}

@Entity(name = "TestEntity1")
@Table(name = "TestEntities1")
class TestEntity {
	@Id
	private Long id;
	@Embedded
	private EntityA field1;
	private EntityB field2;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public EntityA getField1() {
		return field1;
	}
	public void setField1(EntityA field1) {
		this.field1 = field1;
	}
	public EntityB getField2() {
		return field2;
	}
	public void setField2(EntityB field2) {
		this.field2 = field2;
	}
}

@Embeddable
class EntityA {
	private String nameA;
	
	public String getNameA() {
		return nameA;
	}
	public void setNameA(String nameA) {
		this.nameA = nameA;
	}	
}

class EntityB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nameB;
	
	public String getNameB() {
		return nameB;
	}
	public void setNameB(String nameB) {
		this.nameB = nameB;
	}
}
