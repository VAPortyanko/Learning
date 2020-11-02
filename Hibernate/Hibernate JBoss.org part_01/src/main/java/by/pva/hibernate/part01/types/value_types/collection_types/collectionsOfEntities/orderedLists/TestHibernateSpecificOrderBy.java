package by.pva.hibernate.part01.types.value_types.collection_types.collectionsOfEntities.orderedLists;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

public class TestHibernateSpecificOrderBy {
	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Query query = entityManager.createQuery("delete from Article");
		Query query2 = entityManager.createQuery("delete from Person22");
		query.executeUpdate();
		query2.executeUpdate();

		Person22 person = new Person22();
		person.setId(1L);
		person.setName("David");
		person.addArticle(new Article("Article11" , "Content11"));
		person.addArticle(new Article("Article123", "Content123"));
		person.addArticle(new Article("Article4"  , "Content4"));
		entityManager.persist(person);
		
		entityManager.flush();
		entityManager.clear();
		
		Person22 person2 = entityManager.find(Person22.class, 1L); 
		person2.getArticles().forEach(System.out::println);

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
	}
}

@Entity(name = "Person22")
@Table(name = "Persons22")
class Person22 {

	@Id
	private Long id;
	private String name;
	@OneToMany(
		mappedBy = "person",
		cascade = CascadeType.ALL
	)
	@org.hibernate.annotations.OrderBy(
		clause = "CHAR_LENGTH(name) DESC"
	)
	private List<Article> articles = new ArrayList<>();
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
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	public void addArticle(Article article) {
		articles.add(article);
		article.setPerson(this);
	}

	public void removeArticle(Article article) {
		articles.remove(article);
		article.setPerson(null);
	}
}

@Entity(name = "Article")
@Table(name = "Articles")
class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String content;
	@ManyToOne(fetch = FetchType.LAZY)
	private Person22 person;
	
	public Article() {
	}
	public Article(String name, String content) {
		this.name = name;
		this.content = content;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Person22 getPerson() {
		return person;
	}
	public void setPerson(Person22 person) {
		this.person = person;
	}
	@Override
	public String toString() {
		return this.name + " " + this.content;
	}
}

