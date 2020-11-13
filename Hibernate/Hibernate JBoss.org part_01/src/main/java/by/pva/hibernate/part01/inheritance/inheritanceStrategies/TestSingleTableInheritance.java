package by.pva.hibernate.part01.inheritance.inheritanceStrategies;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Persistence;
import javax.persistence.Table;

public class TestSingleTableInheritance {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		DebitAccount2 debitAccount = new DebitAccount2();
		debitAccount.setOwner("John Doe");
		debitAccount.setBalance(BigDecimal.valueOf(100));
		debitAccount.setInterestRate(BigDecimal.valueOf(1.5d));
		debitAccount.setOverdraftFee(BigDecimal.valueOf(25));

		CreditAccount2 creditAccount = new CreditAccount2();
		creditAccount.setOwner("John Doe");
		creditAccount.setBalance(BigDecimal.valueOf(1000));
		creditAccount.setInterestRate(BigDecimal.valueOf(1.9d));
		creditAccount.setCreditLimit(BigDecimal.valueOf(5000));

		entityManager.persist(debitAccount);
		entityManager.persist(creditAccount);

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}

}

@Entity(name = "Account2")
@Table(name = "Accounts2")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
class Account2 {

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

@Entity(name = "DebitAccount2")
class DebitAccount2 extends Account2 {

	private BigDecimal overdraftFee;

	public BigDecimal getOverdraftFee() {
		return overdraftFee;
	}

	public void setOverdraftFee(BigDecimal overdraftFee) {
		this.overdraftFee = overdraftFee;
	}

}

@Entity(name = "CreditAccount2")
class CreditAccount2 extends Account2 {

	private BigDecimal creditLimit;

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

}

//CREATE TABLE Account (
//    DTYPE VARCHAR(31) NOT NULL ,
//    id BIGINT NOT NULL ,
//    balance NUMERIC(19, 2) ,
//    interestRate NUMERIC(19, 2) ,
//    owner VARCHAR(255) ,
//    overdraftFee NUMERIC(19, 2) ,
//    creditLimit NUMERIC(19, 2) ,
//    PRIMARY KEY ( id )
//)
