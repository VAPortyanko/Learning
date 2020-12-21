package by.pva.hibernate.part01.types.value_types.basic_types.mapping.generated_values;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.UpdateTimestamp;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestUpdateTimestampAnnotation extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Bid bid = new Bid();
			bid.setUpdatedBy("John Doe");
			bid.setCents(150 * 100L);

			entityManager.persist(bid);

			bid = entityManager.find(Bid.class, 1L);
			bid.setUpdatedBy("John Doe Jr.");
			bid.setCents(160 * 100L);
			entityManager.persist(bid);
		});

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
