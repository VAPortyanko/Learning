package _by.pva.hibernate.part01.hql_jpql.domain_model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "WireTransferPayment")
@Table(name = "WireTransferPayments")
public class WireTransferPayment extends Payment {
}
