package by.pva.hibernate.part01.persistenceContext.filteringEntitiesAndAssociations.dynamicFiltering;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

public class TestFilterMapping {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Query query = entityManager.createQuery("delete from Account8");
		Query query2 = entityManager.createQuery("delete from Client3");
		query.executeUpdate();
		query2.executeUpdate();
		
		Client client = new Client();
		client.setId( 1L );
		client.setName( "John Doe" );
		
		Account account1 = new Account();
		account1.setId(1L);
		account1.setType(AccountType.CREDIT);
		account1.setAmount(5000d);
		account1.setRate(1.25 / 100);
		account1.setActive(true);
		
		Account account2 = new Account();
		account2.setId(2L);
		account2.setType(AccountType.DEBIT);
		account2.setAmount(0d);
		account2.setRate(1.05 / 100);
		account2.setActive(false);
		
		Account account3 = new Account();
		account3.setId(3L);
		account3.setType(AccountType.DEBIT);
		account3.setAmount(250d);
		account3.setRate(1.05 / 100);
		account3.setActive(true);

		client.addAccount(account1);
		client.addAccount(account2);
		client.addAccount(account3);
		
		entityManager.persist( client );
		
		entityManager.flush();
		entityManager.clear();
		
		// Without the "activeAccount" filter.
		List<Account> accounts = entityManager.createQuery(
			    "select a from Account8 a", Account.class)
			    .getResultList();
		
		accounts.stream().forEach(System.out::println);
		
		entityManager.unwrap(Session.class)
	                 .enableFilter("activeAccount")
	                 .setParameter("active", true);

		entityManager.flush();
		entityManager.clear();
		
		// With the "activeAccount" filter.		
		List<Account> accounts2 = entityManager.createQuery(
				"select a from Account8 a", Account.class)
				.getResultList();
		
		accounts2.stream().forEach(System.out::println);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
	}

}

@Entity(name = "Account8")
@Table(name = "Accounts8")
@FilterDef(
    name="activeAccount",
    parameters = @ParamDef(
        name="active",
        type="boolean"
    )
)
@Filter(
    name="activeAccount",
    condition="active_status = :active"
)
class Account {

    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private Double amount;
    private Double rate;
    @Column(name = "active_status")
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
		return "Account [id=" + id + ", client=" + client.getName() + ", type=" + type + ", amount=" + amount + ", rate=" + rate + ", active=" + active + "]";
	}
	
}

enum AccountType {
	DEBIT, CREDIT
}

@Entity(name = "Client3")
@Table(name = "Clients3")
class Client {

    @Id
    private Long id;
    private String name;
    @OneToMany(
        mappedBy = "client",
        cascade = CascadeType.ALL
    )
    @Filter(
        name="activeAccount",
        condition="active_status = :active"
    )
    private List<Account> accounts = new ArrayList<>( );

    public void addAccount(Account account) {
        account.setClient( this );
        this.accounts.add( account );
    }

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
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
    
}


