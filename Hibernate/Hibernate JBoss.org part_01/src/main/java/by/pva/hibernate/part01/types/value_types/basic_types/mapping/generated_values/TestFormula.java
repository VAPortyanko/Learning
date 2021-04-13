package by.pva.hibernate.part01.types.value_types.basic_types.mapping.generated_values;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestFormula extends BaseTest {

	public static void main(String[] args) {
		
		doInJPA(entityManager -> {
			Account account = new Account();
			account.setCredit(5000d);
			account.setRate(1.25 / 100);
			entityManager.persist(account);
		});
		
		doInJPA(entityManager -> {
			Account account = entityManager.find(Account.class, 1L);
			System.out.println(account.getInterest());
		} );

		entityManagerFactory.close();
	}
	
}

@Entity(name = "Account")
@Table(name = "Accounts")
class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double credit;
	private Double rate;
	@Formula(value = "credit * rate")
	private Double interest;
	
	public Long getId() {
		return id;
	}
	public Double getCredit() {
		return credit;
	}
	public void setCredit(Double credit) {
		this.credit = credit;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getInterest() {
		return interest;
	}
	public void setInterest(Double interest) {
		this.interest = interest;
	}
}

