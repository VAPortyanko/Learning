package by.pva.hibernate.part01.types.value_types.collection_types.collectionAsBasicValueType;

import java.util.List;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

@SuppressWarnings("all")
public class CommaDelimitedStringsType extends AbstractSingleColumnStandardBasicType<List> {

	private static final long serialVersionUID = 1L;

	public CommaDelimitedStringsType() {
		super(VarcharTypeDescriptor.INSTANCE, new CommaDelimitedStringsJavaTypeDescriptor());
	}

	@Override
	public String getName() {
		return "comma_delimited_strings";
	}
}
