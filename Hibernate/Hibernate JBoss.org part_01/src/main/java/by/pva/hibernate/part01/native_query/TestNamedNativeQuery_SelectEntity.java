package by.pva.hibernate.part01.native_query;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.EntityResult;
import javax.persistence.ColumnResult;

import org.hibernate.Session;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class TestNamedNativeQuery_SelectEntity extends BaseTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			// Named SQL queries selecting entities
			// JPA named native entity query:
			List<Person> persons1 = entityManager.createNamedQuery(
				"find_person_by_name" )
			.setParameter("name", "name%")
			.getResultList();

			// Hibernate named native entity query:
			List<Person> persons2 = entityManager.unwrap(Session.class).getNamedQuery(
				"find_person_by_name" )
			.setParameter("name", "name%")
			.list();
																															
			persons1.forEach(System.out::println);
			persons2.forEach(System.out::println);
			
			// To join multiple entities, you need to use a SqlResultSetMapping for each entity the SQL query is going to fetch.
			// JPA named native entity query with joined associations:
			List<Object[]> tuples1 = entityManager.createNamedQuery(
				"find_person_with_phones_by_name" )
			.setParameter("name", "name%")
			.getResultList();

			// Hibernate named native entity query with joined associations
			List<Object[]> tuples2 = entityManager.unwrap(Session.class).getNamedQuery(
				"find_person_with_phones_by_name" )
			.setParameter("name", "name%")
			.list();

			tuples1.forEach(e -> System.out.println(e[0] + ", " + e[1]));
			tuples2.forEach(e -> System.out.println(e[0] + ", " + e[1]));
			
		});

		entityManagerFactory.close();
	}
	
}

@Embeddable
class Dimensions {

    private int length;
    private int width;
	
    public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
    
}

@SuppressWarnings("serial")
@Embeddable
class Identity implements Serializable {

    private String firstname;
    private String lastname;

    public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        final Identity identity = (Identity) o;

        if ( !firstname.equals( identity.firstname ) ) return false;
        if ( !lastname.equals( identity.lastname ) ) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = firstname.hashCode();
        result = 29 * result + lastname.hashCode();
        return result;
    }
}

@Entity(name = "Captain")
@Table(name = "captains")
class Captain {

    @EmbeddedId
    private Identity id;

	public Identity getId() {
		return id;
	}

	public void setId(Identity id) {
		this.id = id;
	}

}

@NamedNativeQueries({
    @NamedNativeQuery(name = "find_all_spaceships",
        query =
            "SELECT " +
            "   name as \"name\", " +
            "   model, " +
            "   speed, " +
            "   lname as lastn, " +
            "   fname as firstn, " +
            "   length, " +
            "   width, " +
            "   length * width as surface, " +
            "   length * width * 10 as volume " +
            "FROM SpaceShip",
        resultSetMapping = "spaceship"
    )
})
@SqlResultSetMapping(
    name = "spaceship",
    entities = @EntityResult(
        entityClass = SpaceShip.class,
        fields = {
            @FieldResult(name = "name", column = "name"),
            @FieldResult(name = "model", column = "model"),
            @FieldResult(name = "speed", column = "speed"),
            @FieldResult(name = "captain.lastname", column = "lastn"),
            @FieldResult(name = "captain.firstname", column = "firstn"),
            @FieldResult(name = "dimensions.length", column = "length"),
            @FieldResult(name = "dimensions.width", column = "width"),
        }
    ),
    columns = {
        @ColumnResult(name = "surface"),
        @ColumnResult(name = "volume")
    }
)
@Entity(name = "SpaceShip")
@Table(name = "spaceShips")
class SpaceShip {

    @Id
    private String name;
    private String model;
    private double speed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "fname", referencedColumnName = "firstname"),
        @JoinColumn(name = "lname", referencedColumnName = "lastname")
    })
    private Captain captain;

    private Dimensions dimensions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Captain getCaptain() {
		return captain;
	}

	public void setCaptain(Captain captain) {
		this.captain = captain;
	}

	public Dimensions getDimensions() {
		return dimensions;
	}

	public void setDimensions(Dimensions dimensions) {
		this.dimensions = dimensions;
	}

}