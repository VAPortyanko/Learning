package by.pva.hibernate.part01.hql_jpql.domain_model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "CreditCardPayment")
@Table(name = "CreditCardPayments")
public class CreditCardPayment extends Payment {
}
