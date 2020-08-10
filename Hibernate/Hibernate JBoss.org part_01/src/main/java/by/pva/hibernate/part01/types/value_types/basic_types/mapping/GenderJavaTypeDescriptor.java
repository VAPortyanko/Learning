package by.pva.hibernate.part01.types.value_types.basic_types.mapping;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.CharacterTypeDescriptor;

@SuppressWarnings("serial")
public class GenderJavaTypeDescriptor extends AbstractTypeDescriptor<Gender> {

    public static final GenderJavaTypeDescriptor INSTANCE =
        new GenderJavaTypeDescriptor();

    protected GenderJavaTypeDescriptor() {
        super( Gender.class );
    }

    public String toString(Gender value) {
        return value == null ? null : value.name();
    }

    public Gender fromString(String string) {
        return string == null ? null : Gender.valueOf( string );
    }

    public <X> X unwrap(Gender value, Class<X> type, WrapperOptions options) {
        return CharacterTypeDescriptor.INSTANCE.unwrap(
            value == null ? null : value.getCode(),
            type,
            options
        );
    }

    public <X> Gender wrap(X value, WrapperOptions options) {
        return Gender.fromCode(
            CharacterTypeDescriptor.INSTANCE.wrap( value, options )
        );
    }
}