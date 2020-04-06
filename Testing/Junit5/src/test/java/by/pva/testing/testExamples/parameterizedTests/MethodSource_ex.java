package by.pva.testing.testExamples.parameterizedTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MethodSource_ex {
	@ParameterizedTest
	@MethodSource("stringProvider")
	void testWithExplicitLocalMethodSource(String argument) {
	    assertNotNull(argument);
	}
	static Stream<String> stringProvider() {
	    return Stream.of("apple", "banana");
	}
	
	// The test and factory methods have the same names. 
	// In this case we can omit the argument of the MethodSource annotation.   
	@ParameterizedTest
	@MethodSource
	void testWithDefaultLocalMethodSource(String argument) {
	    assertNotNull(argument);
	}
	static Stream<String> testWithDefaultLocalMethodSource() {
	    return Stream.of("apple", "banana");
	}
	
	@ParameterizedTest
	@MethodSource("range")
	void testWithRangeMethodSource(int argument) {
	    assertNotEquals(9, argument);
	}
	static IntStream range() {
	    return IntStream.range(0, 20).skip(10);
	}
	
	@ParameterizedTest
	@MethodSource("stringIntAndListProvider")
	void testWithMultiArgMethodSource(String str, int num, List<String> list) {
	    assertEquals(5, str.length());
	    assertTrue(num >=1 && num <=2);
	    assertEquals(2, list.size());
	}
	static Stream<Arguments> stringIntAndListProvider() {
	    return Stream.of(
	        arguments("apple", 1, Arrays.asList("a", "b")),
	        arguments("lemon", 2, Arrays.asList("x", "y"))
	    );
	}
}
