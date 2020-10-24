@AnyMetaDef( name= "PropertyMetaDef", metaType = "string", idType = "long",
    metaValues = {
            @MetaValue(value = "S", targetEntity = StringProperty.class),
            @MetaValue(value = "I", targetEntity = IntegerProperty.class)
        }
    )
package by.pva.hibernate.part01.types.entity_types.associations.anydAssociationMapping;

import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;
