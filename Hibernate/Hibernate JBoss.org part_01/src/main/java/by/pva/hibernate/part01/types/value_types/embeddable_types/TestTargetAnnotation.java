package by.pva.hibernate.part01.types.value_types.embeddable_types;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Target;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestTargetAnnotation extends BaseTest {

	public static void main(String[] args) {
		
		doInJPA(entityManager -> {
			
			City city = new City();
			city.setName("Minsk");
			city.setCoordinates(new GPS(53.9, 27.56667));

			entityManager.persist( city );
			
		});

		entityManagerFactory.close();
	}
}



interface Coordinates {
	double x();
	double y();
}

@Embeddable
class GPS implements Coordinates {

	private double latitude;
	private double longitude;

	@SuppressWarnings("unused")
	private GPS() {
	}

	public GPS(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public double x() {
		return latitude;
	}

	@Override
	public double y() {
		return longitude;
	}
}

@Entity(name = "City1")
@Table(name = "cities1")
class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@Embedded
	@Target(GPS.class)
	private Coordinates coordinates;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

}

