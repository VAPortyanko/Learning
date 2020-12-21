package by.pva.hibernate.part01.inheritance.inheritanceStrategies.single_table_inheritance;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestSingleTableInheritance extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

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

			entityManager.flush();
			entityManager.clear();

			@SuppressWarnings("unchecked")
			List<Account2> accounts = entityManager.createQuery("select a from Account2 a").getResultList();
			accounts.stream().forEach(System.out::println);

		});

		entityManagerFactory.close();

	}

}

// When omitting an explicit inheritance strategy (e.g. @Inheritance),
// JPA will choose the SINGLE_TABLE strategy by default.
//
//
@Entity(name = "Account2")
@Table(name = "Accounts2")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
class Account2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String owner;
	protected BigDecimal balance;
	protected BigDecimal interestRate;

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

// Version and id properties are assumed to be inherited from the root class.
@Entity(name = "DebitAccount2")
class DebitAccount2 extends Account2 {

	private BigDecimal overdraftFee;

	public BigDecimal getOverdraftFee() {
		return overdraftFee;
	}

	public void setOverdraftFee(BigDecimal overdraftFee) {
		this.overdraftFee = overdraftFee;
	}

	@Override
	public String toString() {
		return "DebitAccount2 [overdraftFee=" + overdraftFee + ", id=" + id + ", owner=" + owner + ", balance="
				+ balance + ", interestRate=" + interestRate + "]";
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

	@Override
	public String toString() {
		return "CreditAccount2 [creditLimit=" + creditLimit + ", id=" + id + ", owner=" + owner + ", balance=" + balance
				+ ", interestRate=" + interestRate + "]";
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
