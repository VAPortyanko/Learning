package by.pva.hibernate.part01.types.entity_types.associations.one_to_many.many_to_any;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.MetaValue;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestManyToAny extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			// ToDo: Refactor the next block of the code (And the entire example).
			// How to clear all tables with queries?
			PropertyRepository propertyRepository = entityManager.find(PropertyRepository.class, 1L);
			if (null != propertyRepository) {
				Property<?> p1 = propertyRepository.getProperties().get(0);
				Property<?> p2 = propertyRepository.getProperties().get(1);
				propertyRepository.getProperties().clear();
				entityManager.remove(p1);
				entityManager.remove(p2);
				entityManager.remove(propertyRepository);
				entityManager.flush();

			}

			IntegerProperty ageProperty = new IntegerProperty();
			ageProperty.setId(1L);
			ageProperty.setName("age");
			ageProperty.setValue(23);

			entityManager.persist(ageProperty);

			StringProperty nameProperty = new StringProperty();
			nameProperty.setId(1L);
			nameProperty.setName("name");
			nameProperty.setValue("John Doe");

			entityManager.persist(nameProperty);

			propertyRepository = new PropertyRepository();
			propertyRepository.setId(1L);

			propertyRepository.getProperties().add(ageProperty);
			propertyRepository.getProperties().add(nameProperty);

			entityManager.persist(propertyRepository);

			PropertyRepository propertyRepository2 = entityManager.find(PropertyRepository.class, 1L);

			System.out.println("Properties:");
			propertyRepository2.getProperties().forEach(System.out::println);

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "PropertyRepository")
@Table(name = "property_repositories")
class PropertyRepository {

	@Id
	private Long id;
	@ManyToAny(metaDef = "PropertyMetaDef3", metaColumn = @Column(name = "property_type"))
	@AnyMetaDef(name = "PropertyMetaDef3", metaType = "string", idType = "long", metaValues = {
			@MetaValue(value = "S", targetEntity = StringProperty.class),
			@MetaValue(value = "I", targetEntity = IntegerProperty.class) })
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	@JoinTable(name = "repository_properties", joinColumns = @JoinColumn(name = "repository_id"), inverseJoinColumns = @JoinColumn(name = "property_id"))
	private List<Property<?>> properties = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Property<?>> getProperties() {
		return properties;
	}

	public void setProperties(List<Property<?>> properties) {
		this.properties = properties;
	}

}

//CREATE TABLE property_repository (
//    id BIGINT NOT NULL,
//    PRIMARY KEY ( id )
//)
//
//CREATE TABLE repository_properties (
//    repository_id BIGINT NOT NULL,
//    property_type VARCHAR(255),
//    property_id BIGINT NOT NULL
//)

interface Property<T> {

	String getName();

	T getValue();
}

@Entity(name = "IntegerProperty2")
@Table(name = "integer_properties2")
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

	@Override
	public String toString() {
		return "Property: " + name + ":" + value;
	}

}

@Entity(name = "StringProperty2")
@Table(name = "string_properties2")
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

	@Override
	public String toString() {
		return "Property: " + name + ":" + value;
	}
}
