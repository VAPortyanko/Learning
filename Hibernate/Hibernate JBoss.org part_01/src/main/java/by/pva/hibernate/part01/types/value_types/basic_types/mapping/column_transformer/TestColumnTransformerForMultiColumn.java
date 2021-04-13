package by.pva.hibernate.part01.types.value_types.basic_types.mapping.column_transformer;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestColumnTransformerForMultiColumn extends BaseTest{
	
	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Savings savings = new Savings();
			savings.setWallet(new MonetaryAmount(BigDecimal.TEN, Currency.getInstance(Locale.CHINA)));
			entityManager.persist(savings);

		});
		
		doInJPA(entityManager -> {
			Savings savings = entityManager.find(Savings.class, 1L);
			System.out.println(savings.getWallet().getAmount().intValue());
		} );

		entityManagerFactory.close();
		
	}
}

@Entity(name = "Saving")
@Table(name = "Savings")
class Savings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Type(type = "by.pva.hibernate.part01.types.value_types.basic_types.mapping.column_transformer.MonetaryAmountUserType")
	@Columns(columns = {
		@Column(name = "money"),
		@Column(name = "currency")
	})
	@ColumnTransformer(
		forColumn = "money",
		read = "money / 100",
		write = "? * 100"
	)
	private MonetaryAmount wallet;

	public Long getId() {
		return id;
	}

	public MonetaryAmount getWallet() {
		return wallet;
	}

	public void setWallet(MonetaryAmount wallet) {
		this.wallet = wallet;
	}
	
}