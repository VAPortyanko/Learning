package by.pva.hibernate.part01.types.entity_types.associations.many_to_one.any;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestManyToOneAny extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Query query = entityManager.createQuery("delete from IntegerProperty");
			Query query2 = entityManager.createQuery("delete from StringProperty");
			Query query3 = entityManager.createQuery("delete from PropertyHolder");
			query.executeUpdate();
			query2.executeUpdate();
			query3.executeUpdate();

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

			PropertyHolder namePropertyHolder = new PropertyHolder();
			namePropertyHolder.setId(1L);
			namePropertyHolder.setProperty(nameProperty);
			PropertyHolder agePropertyHolder = new PropertyHolder();
			agePropertyHolder.setId(2L);
			agePropertyHolder.setProperty(ageProperty);

			entityManager.persist(namePropertyHolder);
			entityManager.persist(agePropertyHolder);

		});

		entityManagerFactory.close();

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
	@Any(metaDef = "PropertyMetaDef", metaColumn = @Column(name = "property_type"))
//	@AnyMetaDef(name= "PropertyMetaDef", metaType = "string", idType = "long",
//    metaValues = {
//            @MetaValue(value = "S", targetEntity = StringProperty.class),
//            @MetaValue(value = "I", targetEntity = IntegerProperty.class)
//        }
//    ) // Instead of this the package-info.java is used!
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
