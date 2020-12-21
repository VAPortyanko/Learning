package by.pva.hibernate.part01.persistenceContext.filtering_entities_and_associations.dynamic_filtering;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SqlFragmentAlias;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestFilterWithSQLFragmentAliasmapping extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Query query = entityManager.createQuery("delete from Account9");
			query.executeUpdate();

			Account9 account9_1 = new Account9();
			account9_1.setId(1L);
			account9_1.setActive(true);
			account9_1.setAmount(200d);
			account9_1.setDeleted(false);
			account9_1.setRate(1.05 / 100);

			Account9 account9_2 = new Account9();
			account9_2.setId(2L);
			account9_2.setActive(false);
			account9_2.setAmount(200d);
			account9_2.setDeleted(false);
			account9_2.setRate(1.05 / 100);

			Account9 account9_3 = new Account9();
			account9_3.setId(3L);
			account9_3.setActive(true);
			account9_3.setAmount(200d);
			account9_3.setDeleted(false);
			account9_3.setRate(1.05 / 100);

			entityManager.persist(account9_1);
			entityManager.persist(account9_2);
			entityManager.persist(account9_3);

			entityManager.flush();
			entityManager.clear();

			entityManager.unwrap(Session.class).enableFilter("activeAccount").setParameter("active", true);

			List<Account9> accounts = entityManager.createQuery("select a from Account9 a", Account9.class)
					.getResultList();
			accounts.stream().forEach(System.out::println);

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Account9")
@Table(name = "Accounts9")
@SecondaryTable(name = "Account9_details")
@FilterDef(name = "activeAccount", parameters = @ParamDef(name = "active", type = "boolean"))
@Filter(name = "activeAccount", condition = "{a}.active = :active and {ad}.deleted = false", aliases = {
		@SqlFragmentAlias(alias = "a", table = "accounts9"),
		@SqlFragmentAlias(alias = "ad", table = "account9_details"), })
class Account9 {

	@Id
	private Long id;
	private Double amount;
	private Double rate;
	private boolean active;
	@Column(table = "account9_details")
	private boolean deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Account9 [id=" + id + ", amount=" + amount + ", rate=" + rate + ", active=" + active + ", deleted= "
				+ deleted + "]";
	}
}
