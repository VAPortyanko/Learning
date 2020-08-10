package by.pva.hibernate.part01.types.value_types.basic_types.mapping;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.CharTypeDescriptor;

@SuppressWarnings("serial")
public class GenderType extends AbstractSingleColumnStandardBasicType<Gender> {

	public static final GenderType INSTANCE = new GenderType();

	public GenderType() {
		super(CharTypeDescriptor.INSTANCE, GenderJavaTypeDescriptor.INSTANCE);
	}

	public String getName() {
		return "gender";
	}

	@Override
	protected boolean registerUnderJavaType() {
		return true;
	}
}