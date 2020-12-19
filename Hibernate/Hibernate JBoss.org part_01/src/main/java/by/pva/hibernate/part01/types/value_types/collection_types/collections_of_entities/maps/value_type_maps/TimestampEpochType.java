package by.pva.hibernate.part01.types.value_types.collection_types.collections_of_entities.maps.value_type_maps;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.LiteralType;
import org.hibernate.type.StringType;
import org.hibernate.type.VersionType;
import org.hibernate.type.descriptor.java.JdbcTimestampTypeDescriptor;
import org.hibernate.type.descriptor.sql.BigIntTypeDescriptor;

@SuppressWarnings("serial")
public class TimestampEpochType extends AbstractSingleColumnStandardBasicType<Date> implements VersionType<Date>, LiteralType<Date> {

	public static final TimestampEpochType INSTANCE = new TimestampEpochType();

	public TimestampEpochType() {
		super(BigIntTypeDescriptor.INSTANCE, JdbcTimestampTypeDescriptor.INSTANCE);
	}

	@Override
	public String getName() {
		return "epoch";
	}

	@Override
	public Date next(Date current, SharedSessionContractImplementor session) {
		return seed(session);
	}

	@Override
	public Date seed(SharedSessionContractImplementor session) {
		return new Timestamp(System.currentTimeMillis());
	}

	@Override
	public Comparator<Date> getComparator() {
		return getJavaTypeDescriptor().getComparator();
	}

	@Override
	public String objectToSQLString(Date value, Dialect dialect) throws Exception {
		final Timestamp ts = Timestamp.class.isInstance(value) ? (Timestamp) value : new Timestamp(value.getTime());
		return StringType.INSTANCE.objectToSQLString(ts.toString(), dialect);
	}

	@Override
	public Date fromStringValue(String xml) throws HibernateException {
		return fromString(xml);
	}
}
