package by.pva.testing.testExamples.parameterizedTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class MethodSource2_ex {
    
	@ParameterizedTest
    @MethodSource("by.pva.testing.testExamples.parameterizedTests.StringsProviders#tinyStrings")
    void testWithExternalMethodSource(String tinyString) {
        assertTrue(tinyString.startsWith("_"));
    }
	
}

class StringsProviders {

    static Stream<String> tinyStrings() {
        return Stream.of("_Hello", "_World", "_!");
    }
}