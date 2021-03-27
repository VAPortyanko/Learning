package _by.pva.hibernate.part01.hql_jpql.domain_model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Id;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.persistence.ConstructorResult;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;

import by.pva.hibernate.part01.native_query.PersonNames;

@NamedQuery(
    name = "get_person_by_name",
    query = "select p from Person44 p where p.name = :name"
)
@NamedNativeQuery(
	    name = "find_person_name",
	    query =
	        "SELECT name " +
	        "FROM Persons44 "
)
@NamedNativeQuery(
	    name = "find_person_name_and_nickName",
	    query =
	        "SELECT " +
	        "   name, " +
	        "   nickName " +
	        "FROM Persons44 "
)
@NamedNativeQuery(
    name = "find_person_name_and_nickName_dto",
    query =
        "SELECT " +
        "   name, " +
        "   nickName " +
        "FROM Persons44 ",
    resultSetMapping = "name_and_nickName_dto"
)
@SqlResultSetMapping(
    name = "name_and_nickName_dto",
    classes = @ConstructorResult(
        targetClass = PersonNames.class,
        columns = {
            @ColumnResult(name = "name"),
            @ColumnResult(name = "nickName")
        }
    )
)
@NamedNativeQuery(
	    name = "find_person_by_name",
	    query =
	        "SELECT " +
	        "   p.id AS \"id\", " +
	        "   p.name AS \"name\", " +
	        "   p.nickName AS \"nickName\", " +
	        "   p.address AS \"address\", " +
	        "   p.createdOn AS \"createdOn\", " +
	        "   p.version AS \"version\" " +
	        "FROM Persons44 p " +
	        "WHERE p.name LIKE :name",
	    resultClass = Person.class
)
@NamedNativeQuery(
	    name = "find_person_with_phones_by_name",
	    query =
	        "SELECT " +
	        "   pr.id AS \"pr.id\", " +
	        "   pr.name AS \"pr.name\", " +
	        "   pr.nickName AS \"pr.nickName\", " +
	        "   pr.address AS \"pr.address\", " +
	        "   pr.createdOn AS \"pr.createdOn\", " +
	        "   pr.version AS \"pr.version\", " +
	        "   ph.id AS \"ph.id\", " +
	        "   ph.person_id AS \"ph.person_id\", " +
	        "   ph.phone_number AS \"ph.number\", " +
	        "   ph.phone_type AS \"ph.type\" " +
	        "FROM Persons44 pr " +
	        "JOIN Phones27 ph ON pr.id = ph.person_id " +
	        "WHERE pr.name LIKE :name",
	    resultSetMapping = "person_with_phones"
	)
	 @SqlResultSetMapping(
	     name = "person_with_phones",
	     entities = {
	         @EntityResult(
	             entityClass = Person.class,
	             fields = {
	                 @FieldResult( name = "id", column = "pr.id" ),
	                 @FieldResult( name = "name", column = "pr.name" ),
	                 @FieldResult( name = "nickName", column = "pr.nickName" ),
	                 @FieldResult( name = "address", column = "pr.address" ),
	                 @FieldResult( name = "createdOn", column = "pr.createdOn" ),
	                 @FieldResult( name = "version", column = "pr.version" ),
	             }
	         ),
	         @EntityResult(
	             entityClass = Phone.class,
	             fields = {
	                 @FieldResult( name = "id", column = "ph.id" ),
	                 @FieldResult( name = "person", column = "ph.person_id" ),
	                 @FieldResult( name = "number", column = "ph.number" ),
	                 @FieldResult( name = "type", column = "ph.type" ),
	             }
	         )
	     }
	 )
@Entity(name = "Person44")
@Table(name = "Persons44")
public class Person {

    @Id
    private Long id;
    private String name;
    private String nickName;
    private String address;
    @Temporal(TemporalType.TIMESTAMP )
    private Date createdOn;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @OrderColumn(name = "order_id")
    private List<Phone> phones = new ArrayList<>();
    @ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    private Map<AddressType, String> addresses = new HashMap<>();
    @Version
    private int version;
    
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public List<Phone> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	public Map<AddressType, String> getAddresses() {
		return addresses;
	}
	public void setAddresses(Map<AddressType, String> addresses) {
		this.addresses = addresses;
	}
	public int getVersion() {
		return version;
	}
    public void addPhone(Phone phone) {
        phones.add(phone);
        phone.setPerson(this);
    }
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", nickName=" + nickName + "]";
	}

}