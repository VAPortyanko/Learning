package by.pva.hibernate.part01.persistenceContext.filtering_entities_and_associations.static_filtering;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestWhereMapping extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Query query = entityManager.createQuery("delete from Account7");
			Query query2 = entityManager.createQuery("delete from Client2");
			query.executeUpdate();
			query2.executeUpdate();

			Client client = new Client();
			client.setId(1L);
			client.setName("John Doe");
			entityManager.persist(client);

			Account account1 = new Account();
			account1.setId(1L);
			account1.setType(AccountType.CREDIT);
			account1.setAmount(5000d);
			account1.setRate(1.25 / 100);
			account1.setActive(true);
			account1.setClient(client);
			client.getCreditAccounts().add(account1);
			entityManager.persist(account1);

			Account account2 = new Account();
			account2.setId(2L);
			account2.setType(AccountType.DEBIT);
			account2.setAmount(0d);
			account2.setRate(1.05 / 100);
			account2.setActive(false);
			account2.setClient(client);
			client.getDebitAccounts().add(account2);
			entityManager.persist(account2);

			Account account3 = new Account();
			account3.setType(AccountType.DEBIT);
			account3.setId(3L);
			account3.setAmount(250d);
			account3.setRate(1.05 / 100);
			account3.setActive(true);
			account3.setClient(client);
			client.getDebitAccounts().add(account3);
			entityManager.persist(account3);

			entityManager.flush();
			entityManager.clear();

//		SELECT
//		    a.id as id1_0_,
//		    a.active as active2_0_,
//		    a.amount as amount3_0_,
//		    a.client_id as client_i6_0_,
//		    a.rate as rate4_0_,
//		    a.account_type as account_5_0_
//		FROM
//		    Account a
//		WHERE ( a.active = true )

			List<Account> accounts = entityManager.createQuery("select a from Account7 a", Account.class)
					.getResultList();
			System.out.println(accounts.get(0));
			System.out.println(accounts.get(1));

			entityManager.flush();
			entityManager.clear();

//		SELECT
//		    c.client_id as client_i6_0_0_,
//		    c.id as id1_0_0_,
//		    c.id as id1_0_1_,
//		    c.active as active2_0_1_,
//		    c.amount as amount3_0_1_,
//		    c.client_id as client_i6_0_1_,
//		    c.rate as rate4_0_1_,
//		    c.account_type as account_5_0_1_
//		FROM
//		    Account c
//		WHERE ( c.active = true and c.account_type = 'CREDIT' ) AND c.client_id = 1
//		
//		SELECT
//		    d.client_id as client_i6_0_0_,
//		    d.id as id1_0_0_,
//		    d.id as id1_0_1_,
//		    d.active as active2_0_1_,
//		    d.amount as amount3_0_1_,
//		    d.client_id as client_i6_0_1_,
//		    d.rate as rate4_0_1_,
//		    d.account_type as account_5_0_1_
//		FROM
//		    Account d
//		WHERE ( d.active = true and d.account_type = 'DEBIT' ) AND d.client_id = 1

			Client client2 = entityManager.find(Client.class, 1L);
			System.out.println("Credit accounts:");
			client2.getCreditAccounts().forEach(System.out::println);
			System.out.println("Debit accounts:");
			client2.getDebitAccounts().forEach(System.out::println);

		});

		entityManagerFactory.close();

	}
}

enum AccountType {
	DEBIT, CREDIT
}

@Entity(name = "Client2")
@Table(name = "Clients2")
class Client {

	@Id
	private Long id;
	private String name;
	@Where(clause = "account_type = 'DEBIT'")
	@OneToMany(mappedBy = "client")
	private List<Account> debitAccounts = new ArrayList<>();
	@Where(clause = "account_type = 'CREDIT'")
	@OneToMany(mappedBy = "client")
	private List<Account> creditAccounts = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Account> getDebitAccounts() {
		return debitAccounts;
	}

	public void setDebitAccounts(List<Account> debitAccounts) {
		this.debitAccounts = debitAccounts;
	}

	public List<Account> getCreditAccounts() {
		return creditAccounts;
	}

	public void setCreditAccounts(List<Account> creditAccounts) {
		this.creditAccounts = creditAccounts;
	}

	@Override
	public String toString() {
		return name;
	}

}

@Entity(name = "Account7")
@Table(name = "Accounts7")
@Where(clause = "active = true")
class Account {

	@Id
	private Long id;
	@ManyToOne
	private Client client;
	@Column(name = "account_type")
	@Enumerated(EnumType.STRING)
	private AccountType type;
	private Double amount;
	private Double rate;
	private boolean active;

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

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
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

	@Override
	public String toString() {
		return "Account [id=" + id + ", client=" + client + ", type=" + type + ", amount=" + amount + ", rate=" + rate
				+ ", active=" + active + "]";
	}

}
