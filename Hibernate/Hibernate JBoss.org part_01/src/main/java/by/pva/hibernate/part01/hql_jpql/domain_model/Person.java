package by.pva.hibernate.part01.hql_jpql.domain_model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Id;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@NamedQuery(
    name = "get_person_by_name",
    query = "select p from Person44 p where name = :name"
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