package by.pva.testing.testExamples.parameterizedTests;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CSVSource_ex {
	@ParameterizedTest
	@CsvSource({
	    "apple,         1",
	    "banana,        2",
	    "'lemon, lime', 0xF1"
	})
	void testWithCsvSource(String fruit, int rank) {
	    assertNotNull(fruit);
	    assertNotEquals(0, rank);
	}

	@ParameterizedTest
	@CsvSource({
		 "apple, banana",
		 "apple, 'lemon, lime'",
		 "apple, ''",                 // fruit2 is Empty.
		 "apple, "                    // fruit2 is null. 
	})	
	void testWithCsvSource2(String fruit, String fruit2) {
	    assertTrue(fruit2 != null && !fruit2.isEmpty());
	}
}
