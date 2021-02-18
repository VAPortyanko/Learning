package _by.pva.hibernate.part01.hql_jpql.domain_model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity(name = "Partner")
@Table(name = "Partners")
public class Partner {

	@Id
	private Long id;
	private String name;
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
	public int getVersion() {
		return version;
	}

}
