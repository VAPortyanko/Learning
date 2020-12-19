package by.pva.hibernate.part01.types.value_types.basic_types.mapping.generated_values;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;

import org.hibernate.annotations.UpdateTimestamp;

public class TestUpdateTimestampAnnotation {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		Bid bid = new Bid();
		bid.setUpdatedBy("John Doe");
		bid.setCents(150 * 100L);

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(bid);
		entityManager.getTransaction().commit();
		entityManager.close();

		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		bid = entityManager.find(Bid.class, 1L);
		bid.setUpdatedBy("John Doe Jr.");
		bid.setCents(160 * 100L);
		entityManager.persist(bid);
		entityManager.getTransaction().commit();
		entityManager.close();

		entityManagerFactory.close();
	}
}

@Entity(name = "Bids")
class Bid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "updated_on")
	@UpdateTimestamp
	private Date updatedOn;

	@Column(name = "updated_by")
	private String updatedBy;

	private Long cents;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getCents() {
		return cents;
	}

	public void setCents(Long cents) {
		this.cents = cents;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}
}
