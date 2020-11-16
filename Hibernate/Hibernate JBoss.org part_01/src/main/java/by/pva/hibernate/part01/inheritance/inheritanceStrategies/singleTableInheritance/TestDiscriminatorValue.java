package by.pva.hibernate.part01.inheritance.inheritanceStrategies.singleTableInheritance;

import java.math.BigDecimal;
import java.sql.Statement;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;

// The Account class has a @DiscriminatorValue( "null" ) mapping, meaning that any account row which does not contain
// any discriminator value will be mapped to an Account base class entity. The DebitAccount and CreditAccount entities
// use explicit discriminator values. The OtherAccount entity is used as a generic account type because it maps any database
// row whose discriminator column is not explicitly assigned to any other entity in the current inheritance tree.

public class TestDiscriminatorValue {
	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Query query = entityManager.createQuery("delete from Account5");
		query.executeUpdate();
		
		DebitAccount debitAccount = new DebitAccount();
		debitAccount.setId(1L);
		debitAccount.setOwner("John Doe");
		debitAccount.setBalance(BigDecimal.valueOf(100));
		debitAccount.setInterestRate(BigDecimal.valueOf(1.5d));
		debitAccount.setOverdraftFee(BigDecimal.valueOf(25));

		CreditAccount creditAccount = new CreditAccount();
		creditAccount.setId(2L);
		creditAccount.setOwner("John Doe");
		creditAccount.setBalance(BigDecimal.valueOf(1000));
		creditAccount.setInterestRate(BigDecimal.valueOf(1.9d));
		creditAccount.setCreditLimit(BigDecimal.valueOf(5000));

		Account5 account = new Account5();
		account.setId(3L);
		account.setOwner("John Doe");
		account.setBalance(BigDecimal.valueOf(1000));
		account.setInterestRate(BigDecimal.valueOf(1.9d));

		entityManager.persist(debitAccount);
		entityManager.persist(creditAccount);
		entityManager.persist(account);

		entityManager.unwrap(Session.class).doWork(connection -> {
			try (Statement statement = connection.createStatement()) {
				statement.executeUpdate("insert into Account5 (DTYPE, active, balance, interestRate, owner, id) "
						+ "values ('Other', true, 25, 0.5, 'Vlad', 4)");
			}
		});

		Map<Long, Account5> accounts = entityManager.createQuery("select a from Account5 a", Account5.class)
				                                   .getResultList()
				                                   .stream()
				                                   .collect(Collectors.toMap(Account5::getId, Function.identity()));
		
		accounts.entrySet().stream().map(x -> x.getKey() + ":" + x.getValue()).forEach(System.out::println);

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}
}

@Entity(name = "Account5")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("null") // Uncheck the NotNull property of the DType column in the database. Todo: how to do this with table auto generation?
class Account5 {

	@Id
	Long id;
	String owner;
	BigDecimal balance;
	BigDecimal interestRate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "Account [id=" + id + ", owner=" + owner + ", balance=" + balance + ", interestRate=" + interestRate
				+ "]";
	}

}

@Entity(name = "DebitAccount5")
@DiscriminatorValue("Debit")
class DebitAccount extends Account5 {

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

@Entity(name = "CreditAccount5")
@DiscriminatorValue("Credit")
class CreditAccount extends Account5 {

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

@Entity(name = "OtherAccount5")
@DiscriminatorValue("not null")
class OtherAccount extends Account5 {

	private boolean active;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "OtherAccount [active=" + active + ", id=" + id + ", owner=" + owner + ", balance=" + balance
				+ ", interestRate=" + interestRate + "]";
	}

}