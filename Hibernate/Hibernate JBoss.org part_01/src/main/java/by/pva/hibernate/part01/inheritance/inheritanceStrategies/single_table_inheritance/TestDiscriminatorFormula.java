package by.pva.hibernate.part01.inheritance.inheritanceStrategies.single_table_inheritance;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorFormula;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestDiscriminatorFormula extends BaseTest{

	public static void main(String[] args) {
	
		doInJPA(entityManager ->{

		DebitAccount3 debitAccount = new DebitAccount3();
		debitAccount.setOwner("John Doe");
		debitAccount.setBalance(BigDecimal.valueOf(100));
		debitAccount.setInterestRate(BigDecimal.valueOf(1.5d));
		debitAccount.setOverdraftFee(BigDecimal.valueOf(25));
		debitAccount.setDebitKey("debitKey");

		CreditAccount3 creditAccount = new CreditAccount3();
		creditAccount.setOwner("John Doe");
		creditAccount.setBalance(BigDecimal.valueOf(1000));
		creditAccount.setInterestRate(BigDecimal.valueOf(1.9d));
		creditAccount.setCreditLimit(BigDecimal.valueOf(5000));
		creditAccount.setCreditKey("creditKey");

		entityManager.persist(debitAccount);
		entityManager.persist(creditAccount);
		entityManager.flush();
		entityManager.clear();
		
		@SuppressWarnings("unchecked")
		List<Account3> accounts = entityManager
				.createQuery("select a from Account3 a")
				.getResultList();
		
		accounts.stream()
		        .forEach(System.out::println);
				
		});
		
		entityManagerFactory.close();
		
	}
}



@Entity(name = "Account3")
@Table(name = "Accounts3")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula(
	"case when debitKey is not null " +
	"then 'Debit' " +
	"else ( " +
	"   case when creditKey is not null " +
	"   then 'Credit' " +
	"   else 'Unknown' " +
	"   end ) " +
	"end "
)
class Account3 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String owner;
	protected BigDecimal balance;
	protected BigDecimal interestRate;
	protected Long getId() {
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

}

@Entity(name = "DebitAccount3")
@DiscriminatorValue(value = "Debit")
class DebitAccount3 extends Account3 {

	private String debitKey;
	private BigDecimal overdraftFee;
	public String getDebitKey() {
		return debitKey;
	}
	public void setDebitKey(String debitKey) {
		this.debitKey = debitKey;
	}
	public BigDecimal getOverdraftFee() {
		return overdraftFee;
	}
	public void setOverdraftFee(BigDecimal overdraftFee) {
		this.overdraftFee = overdraftFee;
	}
	@Override
	public String toString() {
		return "DebitAccount3 [debitKey=" + debitKey + ", overdraftFee=" + overdraftFee + ", id=" + id + ", owner="
				+ owner + ", balance=" + balance + ", interestRate=" + interestRate + "]";
	}
	
}

@Entity(name = "CreditAccount3")
@DiscriminatorValue(value = "Credit")
class CreditAccount3 extends Account3 {

	private String creditKey;
	private BigDecimal creditLimit;
	public String getCreditKey() {
		return creditKey;
	}
	public void setCreditKey(String creditKey) {
		this.creditKey = creditKey;
	}
	public BigDecimal getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}
	@Override
	public String toString() {
		return "CreditAccount3 [creditKey=" + creditKey + ", creditLimit=" + creditLimit + ", id=" + id + ", owner="
				+ owner + ", balance=" + balance + ", interestRate=" + interestRate + "]";
	}

}

// CREATE TABLE Account (
//    id int8 NOT NULL ,
//    balance NUMERIC(19, 2) ,
//    interestRate NUMERIC(19, 2) ,
//    owner VARCHAR(255) ,
//    debitKey VARCHAR(255) ,
//    overdraftFee NUMERIC(19, 2) ,
//    creditKey VARCHAR(255) ,
//    creditLimit NUMERIC(19, 2) ,
//    PRIMARY KEY ( id )
// )

