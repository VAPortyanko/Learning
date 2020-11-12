package by.pva.hibernate.part01.inheritance.inheritanceStrategies;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Persistence;
import javax.persistence.Table;

public class TestMappedSuperClass {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		DebitAccount account1 = new DebitAccount();
		account1.setOwner("Owner");
		account1.setBalance(BigDecimal.valueOf(1_000));
		account1.setInterestRate(BigDecimal.valueOf(2));
		account1.setOverdraftFee(BigDecimal.valueOf(10));
		entityManager.persist(account1);

		CreditAccount account2 = new CreditAccount();
		account2.setOwner("Owner");
		account2.setBalance(BigDecimal.valueOf(1_000));
		account2.setInterestRate(BigDecimal.valueOf(2));
		account2.setCreditLimit(BigDecimal.valueOf(10));
		entityManager.persist(account2);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}
}

@MappedSuperclass
class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String owner;
	private BigDecimal balance;
	private BigDecimal interestRate;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public Long getId() {
		return id;
	}
}

@Entity(name = "DebitAccount")
@Table(name = "DebitAccounts")
class DebitAccount extends Account {

	private BigDecimal overdraftFee;

	public BigDecimal getOverdraftFee() {
		return overdraftFee;
	}

	public void setOverdraftFee(BigDecimal overdraftFee) {
		this.overdraftFee = overdraftFee;
	}
}

@Entity(name = "CreditAccount")
@Table(name = "CreditAccounts")
class CreditAccount extends Account {

	private BigDecimal creditLimit;

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}
}

// CREATE TABLE DebitAccount (
//     id BIGINT NOT NULL ,
//     balance NUMERIC(19, 2) ,
//     interestRate NUMERIC(19, 2) ,
//     owner VARCHAR(255) ,
//     overdraftFee NUMERIC(19, 2) ,
//     PRIMARY KEY ( id )
// )
//
// CREATE TABLE CreditAccount (
//     id BIGINT NOT NULL ,
//     balance NUMERIC(19, 2) ,
//     interestRate NUMERIC(19, 2) ,
//     owner VARCHAR(255) ,
//     creditLimit NUMERIC(19, 2) ,
//     PRIMARY KEY ( id )
// )
