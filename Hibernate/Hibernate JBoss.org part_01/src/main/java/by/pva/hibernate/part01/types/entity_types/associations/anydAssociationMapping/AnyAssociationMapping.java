package by.pva.hibernate.part01.types.entity_types.associations.anydAssociationMapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Any;

public class AnyAssociationMapping {

	public static void main(String[] args) {

	}
}

interface Property<T> {

	String getName();

	T getValue();
}

@Entity(name = "IntegerProperty")
@Table(name = "integer_properties")
class IntegerProperty implements Property<Integer> {

	@Id
	private Long id;
	@Column(name = "`name`")
	private String name;
	@Column(name = "`value`")
	private Integer value;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}

@Entity(name = "StringProperty")
@Table(name = "string_properties")
class StringProperty implements Property<String> {

	@Id
	private Long id;
	@Column(name = "`name`")
	private String name;
	@Column(name = "`value`")
	private String value;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getValue() {
		return value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

@Entity(name = "PropertyHolder")
@Table(name = "property_holders")
class PropertyHolder {

	@Id
	private Long id;
	@Any(metaDef = "PropertyMetaDef",
		 metaColumn = @Column(name = "property_type"))
	@JoinColumn(name = "property_id")
	private Property<?> property;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Property<?> getProperty() {
		return property;
	}

	public void setProperty(Property<?> property) {
		this.property = property;
	}

}
