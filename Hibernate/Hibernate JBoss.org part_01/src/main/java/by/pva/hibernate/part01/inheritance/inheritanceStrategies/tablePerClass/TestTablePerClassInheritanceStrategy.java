package by.pva.hibernate.part01.inheritance.inheritanceStrategies.tablePerClass;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

public class TestTablePerClassInheritanceStrategy {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Query query1 = entityManager.createQuery("delete from Account6");
		query1.executeUpdate(); // Also delete records from two other tables.
		
		Account account = new Account();
		account.setId(1L);
		account.setOwner("John Doe");
		account.setBalance(BigDecimal.valueOf(100));
		account.setInterestRate(BigDecimal.valueOf(1.5d));
		
		// A requirement for all child objects of the same parent entity is that they have unique IDs among them.
		// https://stackoverflow.com/questions/47455957/how-can-i-combine-table-per-class-and-generationtype-identity
		DebitAccount debitAccount = new DebitAccount();
		debitAccount.setId(2L);
		debitAccount.setOwner("John Doe");
		debitAccount.setBalance(BigDecimal.valueOf(100));
		debitAccount.setInterestRate(BigDecimal.valueOf(1.5d));
		debitAccount.setOverdraftFee(BigDecimal.valueOf(25));

		CreditAccount creditAccount = new CreditAccount();
		creditAccount.setId(3L);
		creditAccount.setOwner("John Doe");
		creditAccount.setBalance(BigDecimal.valueOf(1000));
		creditAccount.setInterestRate(BigDecimal.valueOf(1.9d));
		creditAccount.setCreditLimit(BigDecimal.valueOf(5000));

		entityManager.persist(account);
		entityManager.persist(debitAccount);
		entityManager.persist(creditAccount);

		entityManager.flush();
		entityManager.clear();
		
		@SuppressWarnings("unchecked")
		List<Account> accounts = entityManager
				.createQuery("select a from Account6 a")
				.getResultList();
		accounts.stream().forEach(System.out::println);

		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
	}
}

@Entity(name = "Account6")
@Table(name = "Accounts6")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class Account {

	@Id
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
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", owner=" + owner + ", balance=" + balance + ", interestRate=" + interestRate
				+ "]";
	}

}

@Entity(name = "DebitAccount6")
@Table(name = "DebitAccounts6")
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

@Entity(name = "CreditAccount6")
@Table(name = "CreditAccounts6")
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
//    id BIGINT NOT NULL ,
//    balance NUMERIC(19, 2) ,
//    interestRate NUMERIC(19, 2) ,
//    owner VARCHAR(255) ,
//    creditLimit NUMERIC(19, 2) ,
//    PRIMARY KEY ( id )
//)
//
//CREATE TABLE DebitAccount (
//    id BIGINT NOT NULL ,
//    balance NUMERIC(19, 2) ,
//    interestRate NUMERIC(19, 2) ,
//    owner VARCHAR(255) ,
//    overdraftFee NUMERIC(19, 2) ,
//    PRIMARY KEY ( id )
//)

// Select a from Account6 a:

//SELECT tablepercl0_.id AS id1_0_ ,
//       tablepercl0_.balance AS balance2_0_ ,
//       tablepercl0_.interestRate AS interest3_0_ ,
//       tablepercl0_.owner AS owner4_0_ ,
//       tablepercl0_.overdraftFee AS overdraf1_2_ ,
//       tablepercl0_.creditLimit AS creditLi1_1_ ,
//       tablepercl0_.clazz_ AS clazz_
//FROM (
//    SELECT    id ,
//             balance ,
//             interestRate ,
//             owner ,
//             CAST(NULL AS INT) AS overdraftFee ,
//             CAST(NULL AS INT) AS creditLimit ,
//             0 AS clazz_
//    FROM     Account
//    UNION ALL
//    SELECT   id ,
//             balance ,
//             interestRate ,
//             owner ,
//             overdraftFee ,
//             CAST(NULL AS INT) AS creditLimit ,
//             1 AS clazz_
//    FROM     DebitAccount
//    UNION ALL
//    SELECT   id ,
//             balance ,
//             interestRate ,
//             owner ,
//             CAST(NULL AS INT) AS overdraftFee ,
//             creditLimit ,
//             2 AS clazz_
//    FROM     CreditAccount
//) tablepercl0_

// Additional read - https://in.relation.to/2005/07/20/multitable-bulk-operations/
