package by.pva.hibernate.part01.types.entity_types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestMappingEntityToSqlQuery extends BaseTest{

	public static void main(String[] args) {
		
		doInJPA(entityManager -> {
			
			Client client = new Client();
			client.setId(1L);
			client.setFirstName("John");
			client.setLastName("Doe");
			entityManager.persist( client );

			Account account = new Account();
			account.setId(1L);
			account.setClient(client);
			account.setDescription("Checking account");
			entityManager.persist(account);

			AccountTransaction transaction1 = new AccountTransaction();
			transaction1.setAccount(account);
			transaction1.setDescription("Salary");
			transaction1.setCents(100 * 7000);
			entityManager.persist(transaction1);

			AccountSummary summary = entityManager.createQuery(
				"select s " +
				"from AccountSummary s " +
				"where s.id = :id", AccountSummary.class)
			.setParameter("id", account.getId())
			.getSingleResult();

			System.out.println(summary.getClientName());
			System.out.println(summary.getBalance());
			
			AccountTransaction transaction2 = new AccountTransaction();
			transaction2.setAccount(account);
			transaction2.setDescription("Shopping");
			transaction2.setCents(-100 * 2200);
			entityManager.persist(transaction2);
			entityManager.flush();

			entityManager.refresh(summary);
			
			System.out.println(summary.getClientName());
			System.out.println(summary.getBalance());
			
			entityManager.remove(transaction1);
			entityManager.remove(transaction2);
			entityManager.remove(account);
			entityManager.remove(client);
			
		});

		entityManagerFactory.close();
	}
	
}

@Entity(name = "Client5")
@Table(name = "clients5")
class Client {

	@Id
	private Long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}

@Entity(name = "Account11")
@Table(name = "accounts11")
class Account {

	@Id
	private Long id;
	@ManyToOne
	private Client client;
	private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}

@Entity(name = "AccountTransaction")
@Table(name = "account_transactions")
class AccountTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Account account;
	private Integer cents;
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Integer getCents() {
		return cents;
	}
	public void setCents(Integer cents) {
		this.cents = cents;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}

@Entity(name = "AccountSummary") // There is no entity representation in the DB (The table doesn't exist).
@Subselect(
	"select " +
	"	a.id as id, " +
	"	concat(concat(c.first_name, ' '), c.last_name) as clientName, " +
	"	sum(atr.cents) as balance " +
	"from accounts11 a " +
	"join clients5 c on c.id = a.client_id " +
	"join account_transactions atr on a.id = atr.account_id " +
	"group by a.id, concat(concat(c.first_name, ' '), c.last_name)"
)
// The goal of the @Synchronize annotation in the AccountSummary entity mapping 
// is to instruct Hibernate which database tables are needed by the underlying @Subselect SQL query.
@Synchronize( {"client", "account", "account_transaction"} )
class AccountSummary {

	@Id
	private Long id;
	private String clientName;
	private int balance;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}

}

