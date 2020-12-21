package by.pva.hibernate.part01.types.value_types.basic_types.mapping.column_transformer;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.engine.jdbc.BlobProxy;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestColumnTransformer extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Employee employee = new Employee();
			employee.setUsername("UserName2");
			employee.setAccessLevel(0);
			byte[] buff = "Password".getBytes();
			employee.setPassword(BlobProxy.generateProxy("Password".getBytes()));

			entityManager.persist(employee);

			Employee storedEmployee = entityManager.find(Employee.class, 1l);
			Arrays.fill(buff, (byte) 0);
			try {
				storedEmployee.getPassword().getBinaryStream().read(buff);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
			System.out.println(new String(buff));

		});

		entityManagerFactory.close();
	}
}

@Entity(name = "employees")
class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
	@Column(name = "pswd")
	@ColumnTransformer(read = "aes_decrypt(pswd, 'mysecretkey')", write = "aes_encrypt(?, 'mysecretkey')")
	private Blob password;
	private int accessLevel;

//	@ManyToOne(fetch = FetchType.LAZY)
//	private Department department;
//
//	@ManyToMany(mappedBy = "employees")
//	private List<Project> projects = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Blob getPassword() {
		return password;
	}

	public void setPassword(Blob clob) {
		this.password = clob;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
}
