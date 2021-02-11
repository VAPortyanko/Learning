package by.pva.hibernate.part01._tests;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import by.pva.hibernate.part01._myUtils.BaseTest;
// ToDo: move the example to the appropriate package! 
public class Test03 extends BaseTest{
	public static void main(String[] args) {
		
		doInJPA(entityManager -> {
			
			entityManager.createQuery("delete from Foo").executeUpdate();
			entityManager.createQuery("delete from Bar").executeUpdate();
			
			Bar bar = new Bar();
			Foo foo = new Foo();
			
			bar.setId(1L);
			foo.setId(1L);
			foo.setBar(bar);
			
			entityManager.persist(foo);
			flushAndClear(entityManager);
	
			foo = entityManager.find(Foo.class, foo.getId());
			bar = entityManager.find(Bar.class, bar.getId());
			entityManager.remove(bar);
			flushAndClear(entityManager);       // No deletion here! - https://www.baeldung.com/delete-with-hibernate.
			                                    // Because a PERSIST operation has been applied to a removed instance.
	
			bar = entityManager.find(Bar.class, bar.getId());
			foo = entityManager.find(Foo.class, foo.getId());
			foo.setBar(null);
			entityManager.remove(bar);
			flushAndClear(entityManager);
	
		
		});
	}

	private static void flushAndClear(EntityManager entityManager) {
		entityManager.flush();
		entityManager.clear();
	}
	
	
}

@Entity
class Foo {
	
	@Id
	private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Bar bar;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Bar getBar() {
		return bar;
	}
	public void setBar(Bar bar) {
		this.bar = bar;
	}

}

@Entity
class Bar {

	@Id
    private Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}