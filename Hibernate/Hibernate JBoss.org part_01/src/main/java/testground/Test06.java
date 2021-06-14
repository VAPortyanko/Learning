package testground;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class Test06 extends BaseTest{
	
	public static void main(String[] args) {

		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.format_sql", "true");
		
		buildEntityManagerFactory(properties);
		
		doInJPA(entityManager -> {
			
			entityManager.createQuery("delete from Child1").executeUpdate();
			entityManager.createQuery("delete from Child2").executeUpdate();
			entityManager.createQuery("delete from Child3").executeUpdate();
			entityManager.createQuery("delete from Child4").executeUpdate();
			entityManager.createQuery("delete from Child5").executeUpdate();
			
			Child1Entity child1_01 = new Child1Entity();
			child1_01.setType("Type1");
			child1_01.setStatus("Enable");
			Child1Entity child1_02 = new Child1Entity();
			child1_02.setType("Type2");
			child1_02.setStatus("Disable");
			
			Child2Entity child2_01 = new Child2Entity();
			child2_01.setType("Type1");
			child2_01.setStatus("Enable");
			Child2Entity child2_02 = new Child2Entity();
			child2_02.setType("Type2");
			child2_02.setStatus("Disable");
			
			Child3Entity child3_01 = new Child3Entity();
			child3_01.setType("Type1");
			child3_01.setStatus("Enable");
			Child3Entity child3_02 = new Child3Entity();
			child3_02.setType("Type2");
			child3_02.setStatus("Disable");
			
			Child4Entity child4_01 = new Child4Entity();
			child4_01.setType("Type1");
			child4_01.setStatus("Enable");
			Child4Entity child4_02 = new Child4Entity();
			child4_02.setType("Type2");
			child4_02.setStatus("Disable");
			
			Child5Entity child5_01 = new Child5Entity();
			child5_01.setType("Type1");
			child5_01.setStatus("Enable");
			Child5Entity child5_02 = new Child5Entity();
			child5_02.setType("Type2");
			child5_02.setStatus("Disable");
			
			entityManager.persist(child1_01);
			entityManager.persist(child1_02);
			entityManager.persist(child2_01);
			entityManager.persist(child2_02);
			entityManager.persist(child3_01);
			entityManager.persist(child3_02);
			entityManager.persist(child4_01);
			entityManager.persist(child4_02);
			entityManager.persist(child5_01);
			entityManager.persist(child5_02);
			
			entityManager.flush();
			entityManager.clear();

			List<Object[]> entities0 = entityManager.createQuery(
					" select treat(p as Child2).type, treat(p as Child2).status " +
					" from Parent p " +
					" where type(p) = Child2 " 
			, Object[].class).getResultList();
			
			entities0.forEach(e -> System.out.println(e[0] + " " + e[1]));
			
			List<Long> entities = entityManager.createQuery(
					"select p.id " +
					"from Parent p " +
					"left join Child1 c1 on p.id = c1.id " +
					"left join Child2 c2 on p.id = c2.id " +
					"left join Child3 c3 on p.id = c3.id " +
					"where (type(p) = Child1 and c1.status like 'Enable') " +
					"   or (type(p) = Child2 and c2.status like 'Disable') " +
					"   or (type(p) = Child3 and c3.status like 'Enable') " 
			, Long.class).getResultList();
			
			entities.forEach(e -> System.out.println(e));
			
		});
	
	}
}



@Entity(name = "Parent")
@Inheritance(strategy = InheritanceType.JOINED)
class ParentEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    
	public Long getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
}

@Entity(name = "Child1")
class Child1Entity extends ParentEntity {
	
    private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}

@Entity(name = "Child2")
class Child2Entity extends ParentEntity {

    private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

@Entity(name = "Child3")
class Child3Entity extends ParentEntity {

    private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

@Entity(name = "Child4")
class Child4Entity extends ParentEntity {

    private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

@Entity(name = "Child5")
class Child5Entity extends ParentEntity {
    
    private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
