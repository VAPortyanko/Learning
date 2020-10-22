package by.pva.hibernate.part01.types.entity_types.associations.one_to_one;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

// Although you might annotate the parent-side association to be fetched lazily,
// Hibernate cannot honor this request since it cannot know whether the association is null or not.
//
// The only way to figure out whether there is an associated record on the child side is to fetch 
// the child association using a secondary query.Because this can lead to N+1 query issues,it’s much 
// more efficient to use unidirectional @OneToOne associations with the @MapsId annotation in place.
//
// However,if you really need to use a bidirectional association and want to make sure that this is 
// always going to be fetched lazily,then you need to enable lazy state initialization bytecode 
// enhancement and use the @LazyToOne annotation as well.

public class TestOneToOneBidirectionalLazy {

	public static void main(String[] args) {
		
	}

}

@Entity(name = "Phone7")
@Table(name = "Phones7")
class Phone7 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "`number`")
	private String number;

	@OneToOne(mappedBy = "phone",
			  cascade = CascadeType.ALL,
			  orphanRemoval = true,
			  fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY) // !!!
	private PhoneDetails4 details;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public PhoneDetails4 getDetails() {
		return details;
	}

	public void setDetails(PhoneDetails4 details) {
		this.details = details;
	}

	public void addDetails(PhoneDetails4 details) {
		details.setPhone(this);
		this.details = details;
	}

	public void removeDetails() {
		if (details != null) {
			details.setPhone(null);
			this.details = null;
		}
	}
}

@Entity(name = "PhoneDetail4")
@Table(name = "PhoneDetails4")
class PhoneDetails4 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String provider;

	private String technology;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "phone_id")
	private Phone7 phone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public Phone7 getPhone() {
		return phone;
	}

	public void setPhone(Phone7 phone) {
		this.phone = phone;
	}

}
