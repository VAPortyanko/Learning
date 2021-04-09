package by.pva.hibernate.part01.types.attribute_conventer;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.query.Query;

import org.hibernate.metamodel.spi.MetamodelImplementor;
import org.hibernate.type.Type;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestAttributeConventerEntityPropertyAsQueryParameter extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Caption caption = new Caption("Caption");
			Photo photo = new Photo();
			photo.setCaption(caption);
			photo.setName("Photo name");

			entityManager.persist(photo);

			// Traditionally, you could only use the DB data Caption representation,
			// which in our case is a String, when referencing the caption entity property.
			Photo photo2 = entityManager
					.createQuery("select p " + "from Photos p " + "where upper(caption) = upper(:caption) ", Photo.class)
					.setMaxResults(1)
					.setParameter("caption", "Caption")
					.getSingleResult();
			
			System.out.println(photo2);

			// In order to use the Java object Caption representation, you have to get the
			// associated Hibernate Type.
			MetamodelImplementor metamodelImplementor = (MetamodelImplementor) entityManagerFactory.getMetamodel();
			Type captionType = metamodelImplementor
					           .entityPersister(Photo.class.getName())
					           .getPropertyType("caption");

			Photo photo3 = (Photo) entityManager
					.createQuery("select p " + "from Photos p " + "where upper(caption) = upper(:caption) ", Photo.class)
					.unwrap(Query.class)
					.setMaxResults(1)
					.setParameter("caption", caption, captionType)
					.getSingleResult();
			
			System.out.println(photo3);

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Photos")
class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Convert(converter = CaptionConverter.class)
	private Caption caption;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Caption getCaption() {
		return caption;
	}

	public void setCaption(Caption caption) {
		this.caption = caption;
	}

	@Override
	public String toString() {
		return name;
	}

}

class Caption {

	private String text;

	public Caption(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Caption caption = (Caption) o;
		return text != null ? text.equals(caption.text) : caption.text == null;
	}

	@Override
	public int hashCode() {
		return text != null ? text.hashCode() : 0;
	}
}

class CaptionConverter implements AttributeConverter<Caption, String> {

	@Override
	public String convertToDatabaseColumn(Caption attribute) {
		return attribute.getText();
	}

	@Override
	public Caption convertToEntityAttribute(String dbData) {
		return new Caption(dbData);
	}
}