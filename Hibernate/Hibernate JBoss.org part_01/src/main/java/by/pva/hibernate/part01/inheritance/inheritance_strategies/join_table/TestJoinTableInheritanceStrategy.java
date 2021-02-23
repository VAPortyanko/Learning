package by.pva.hibernate.part01.inheritance.inheritance_strategies.join_table;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestJoinTableInheritanceStrategy extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			DebitAccount debitAccount = new DebitAccount();
			debitAccount.setOwner("John Doe");
			debitAccount.setBalance(BigDecimal.valueOf(100));
			debitAccount.setInterestRate(BigDecimal.valueOf(1.5d));
			debitAccount.setOverdraftFee(BigDecimal.valueOf(25));

			CreditAccount creditAccount = new CreditAccount();
			creditAccount.setOwner("John Doe");
			creditAccount.setBalance(BigDecimal.valueOf(1000));
			creditAccount.setInterestRate(BigDecimal.valueOf(1.9d));
			creditAccount.setCreditLimit(BigDecimal.valueOf(5000));

			entityManager.persist(debitAccount);
			entityManager.persist(creditAccount);

			entityManager.flush();
			entityManager.clear();

			@SuppressWarnings("unchecked")
			List<Account> accounts = entityManager.createQuery("select a from Account4 a").getResultList();
			accounts.forEach(System.out::println);

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Account4")
@Inheritance(strategy = InheritanceType.JOINED)
class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String owner;
	BigDecimal balance;
	BigDecimal interestRate;

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

@Entity(name = "DebitAccount4")
class DebitAccount extends Account {

	private BigDecimal overdraftFee;

	public BigDecimal getOverdraftFee() {
		return overdraftFee;
	}

	public void setOverdraftFee(BigDecimal overdraftFee) {
		this.overdraftFee = overdraftFee;
	}

	@Override
	public String toString() {
		return "DebitAccount [overdraftFee=" + overdraftFee + ", id=" + id + ", owner=" + owner + ", balance=" + balance
				+ ", interestRate=" + interestRate + "]";
	}

}

@Entity(name = "CreditAccount4")
class CreditAccount extends Account {

	private BigDecimal creditLimit;

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Override
	public String toString() {
		return "CreditAccount [creditLimit=" + creditLimit + ", id=" + id + ", owner=" + owner + ", balance=" + balance
				+ ", interestRate=" + interestRate + "]";
	}

}

//CREATE TABLE Account (
//    id BIGINT NOT NULL ,
//    balance NUMERIC(19, 2) ,
//    interestRate NUMERIC(19, 2) ,
//    owner VARCHAR(255) ,
//    PRIMARY KEY ( id )
//)
//
//CREATE TABLE CreditAccount (
//    creditLimit NUMERIC(19, 2) ,
//    id BIGINT NOT NULL ,
//    PRIMARY KEY ( id )
//)
//
//CREATE TABLE DebitAccount (
//    overdraftFee NUMERIC(19, 2) ,
//    id BIGINT NOT NULL ,
//    PRIMARY KEY ( id )
//)
//
//ALTER TABLE CreditAccount
//ADD CONSTRAINT FKihw8h3j1k0w31cnyu7jcl7n7n
//FOREIGN KEY (id) REFERENCES Account
//
//ALTER TABLE DebitAccount
//ADD CONSTRAINT FKia914478noepymc468kiaivqm
//FOREIGN KEY (id) REFERENCES Account
