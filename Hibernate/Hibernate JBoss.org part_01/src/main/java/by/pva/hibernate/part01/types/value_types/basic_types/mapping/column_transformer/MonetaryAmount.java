package by.pva.hibernate.part01.types.value_types.basic_types.mapping.column_transformer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

public class MonetaryAmount implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private BigDecimal amount;
	private Currency currency;

	public MonetaryAmount(BigDecimal amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}