package by.pva.hibernate.part01.types.value_types.basic_types.mapping.columnTransformer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TestColumnTransformerForMultiColumn {
	public static void main(String[] args) {

	}
}

@Entity(name = "Savings")
class Savings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Type(type = "org.hibernate.userguide.mapping.basic.MonetaryAmountUserType") // ToDo - need implement the user type.
//	@Columns(columns = {
//		@Column(name = "money"),
//		@Column(name = "currency")
//	})
//	@ColumnTransformer(
//		forColumn = "money",
//		read = "money / 100",
//		write = "? * 100"
//	)
//	private MonetaryAmount wallet; // ToDo - need implement the MonetaryAmount class.
}
