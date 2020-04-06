package by.pva.testing.testExamples.parameterizedTests;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CSVFileSource_ex {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/File.csv", numLinesToSkip = 1) // https://github.com/junit-team/junit5/issues/1327
	void testWithCsvFileSource(String country, int reference) {
	    assertNotNull(country);
	    assertNotEquals(0, reference);
	}
}
