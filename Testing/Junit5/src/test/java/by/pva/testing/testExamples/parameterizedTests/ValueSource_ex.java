package by.pva.testing.testExamples.parameterizedTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;

import by.pva.testing.testExamples.utils.StringUtils;
import org.junit.jupiter.params.provider.ValueSource;

public class ValueSource_ex {

	@ParameterizedTest
	@ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba", "omega" })
	void testWithStringValueSource(String candidate) {
	    assertTrue(StringUtils.isPalindrome(candidate));
	}
	
	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3, 5 })
	void testWithIntValueSource(int argument) {
	    assertTrue(argument > 0 && argument < 4);
	}
	
	@ParameterizedTest
	@ValueSource(booleans = { true, false })
	void testWithBooleanValueSource(boolean argument) {
	    assertTrue(argument);
	}

	@ParameterizedTest
	@ValueSource(classes = { Sample.class })
	void testWithClassValueSource(Class<Sample> argument) throws InstantiationException, IllegalAccessException {
		assertTrue(argument.newInstance().getGreeting().equalsIgnoreCase("Hello!"));
	}
	
	static class Sample {
		public String getGreeting() {
			return "Hello!";
		}
	}
}

/*
Supported types for the @ValueSource annotation: 
   short
   byte
   int
   long
   float
   double
   char
   boolean
   java.lang.String
   java.lang.Class
*/