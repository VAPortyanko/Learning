package by.pva.testing.testExamples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import by.pva.testing.classesForTesting.Calculator;
import by.pva.testing.customAnnotations.composedAnnotation.FastTest;
import by.pva.testing.customAnnotations.metaAnnotation.Fast;

class TestWithMetaAndComposedAnnotations {

	private final Calculator calculator = new Calculator();

	@Test
	@Fast // Meta annotation.
	void addition1() {
		assertEquals(4, calculator.add(1, 1));
	}
	
	@FastTest // Composed annotation.
	void addition2() {
		assertEquals(2, calculator.add(1, 1));
	}
}
