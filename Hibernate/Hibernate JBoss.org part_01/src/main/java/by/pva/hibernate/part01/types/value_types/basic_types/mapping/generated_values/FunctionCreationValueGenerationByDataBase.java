package by.pva.hibernate.part01.types.value_types.basic_types.mapping.generated_values;

import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;

@SuppressWarnings("serial")
public class FunctionCreationValueGenerationByDataBase implements AnnotationValueGeneration<FunctionCreationTimestampByDataBase> {

	@Override
	public void initialize(FunctionCreationTimestampByDataBase annotation, Class<?> propertyType) {
	}

	/**
	 * Generate value on INSERT
	 * 
	 * @return when to generate the value
	 */
	public GenerationTiming getGenerationTiming() {
		return GenerationTiming.INSERT;
	}

	/**
	 * Returns null because the value is generated by the database.
	 * 
	 * @return null
	 */
	public ValueGenerator<?> getValueGenerator() {
		return null;
	}

	/**
	 * Returns true because the value is generated by the database.
	 * 
	 * @return true
	 */
	public boolean referenceColumnInSql() {
		return true;
	}

	/**
	 * Returns the database-generated value
	 * 
	 * @return database-generated value
	 */
	public String getDatabaseGeneratedReferencedColumnValue() {
		return "current_timestamp";
	}
}
