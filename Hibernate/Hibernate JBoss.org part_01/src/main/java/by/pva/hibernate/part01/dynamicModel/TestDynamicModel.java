package by.pva.hibernate.part01.dynamicModel;

//import java.util.HashMap;
//import java.util.Map;
//import org.hibernate.Session;

// Persistent entities do not necessarily have to be represented as POJO/JavaBean classes.
// Hibernate also supports dynamic models (using Map of Maps at runtime). With this approach,
// you do not write persistent classes, only mapping files.

// The main advantage of dynamic models is the quick turnaround time for prototyping without
// the need for entity class implementation.

public class TestDynamicModel {

	public static void main(String[] args) {

//		Map<String, String> book = new HashMap<>();
//		
//		book.put("isbn", "978-9730228236");
//		book.put("title", "High-Performance Java Persistence");
//		book.put("author", "Vlad Mihalcea");
//
//		entityManager.unwrap(Session.class).save("Book", book);

	}

}

//settings.put( "hibernate.default_entity_mode", "dynamic-map" );

//<!DOCTYPE hibernate-mapping PUBLIC
//    "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
//    "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
//
//<hibernate-mapping>
//    <class entity-name="Book">
//        <id name="isbn" column="isbn" length="32" type="string"/>
//
//        <property name="title" not-null="true" length="50" type="string"/>
//
//        <property name="author" not-null="true" length="50" type="string"/>
//
//    </class>
//</hibernate-mapping>

