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
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterJoinTable;
import org.hibernate.annotations.ParamDef;

public class TestFilterJoinTableMapping {

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Query query = entityManager.createQuery("delete from Client4");
		Query query2 = entityManager.createQuery("delete from Account10");
		query.executeUpdate();
		query2.executeUpdate();
		
		Client4 client = new Client4();
		client.setId(1L);
		client.setName("John Doe");
		
		Account10 account10_1 = new Account10();
	    account10_1.setId(1L);
	    account10_1.setType(AccountType.CREDIT);
	    account10_1.setAmount(5000d);
	    account10_1.setRate(1.25 / 100);
		    
		Account10 account10_2 = new Account10();
	    account10_2.setId(2L);
	    account10_2.setType(AccountType.DEBIT);
	    account10_2.setAmount(0d);
	    account10_2.setRate(1.05 / 100);
		
	    Account10 account10_3 = new Account10();
	    account10_3.setId(3L);
	    account10_3.setType(AccountType.DEBIT);
	    account10_3.setAmount(250d);
	    account10_3.setRate(1.05 / 100);
		
		client.addAccount(account10_1);
		client.addAccount(account10_2);
		client.addAccount(account10_3);
		
		entityManager.persist(client);

		entityManager.flush();
		entityManager.clear();
		
		Client4 client2 = entityManager.find(Client4.class, 1L);
		client2.getAccounts().stream().forEach(System.out::println);
		
		entityManager.flush();
		entityManager.clear();
		
		Client4 client3 = entityManager.find(Client4.class, 1L);

		entityManager
		    .unwrap(Session.class)
		    .enableFilter("firstAccounts")
		    .setParameter("maxOrderId", 1);
		
		client3.getAccounts().stream().forEach(System.out::println);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}



@Entity(name = "Client4")
@Table(name = "Clients4")
@FilterDef(
    name="firstAccounts",
    parameters=@ParamDef(
        name="maxOrderId",
        type="int"
    )
)
@Filter(
    name="firstAccounts",
    condition="order_id <= :maxOrderId"
)
class Client4 {

    @Id
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @OrderColumn(name = "order_id")
    @FilterJoinTable(
        name="firstAccounts",
        condition="order_id <= :maxOrderId"
    )
    private List<Account10> accounts = new ArrayList<>( );

    public void addAccount(Account10 account) {
        this.accounts.add(account);
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
	public List<Account10> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account10> accounts) {
		this.accounts = accounts;
	}

}

@Entity(name = "Account10")
@Table(name = "Accounts10")
class Account10 {

    @Id
    private Long id;
    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private Double amount;
    private Double rate;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Account10 [id=" + id + ", type=" + type + ", amount=" + amount + ", rate=" + rate + "]";
	}
}

