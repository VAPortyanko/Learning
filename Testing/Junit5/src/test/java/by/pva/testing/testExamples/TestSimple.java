package by.pva.testing.testExamples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import by.pva.testing.classesForTesting.Calculator;

class TestSimple {

	private final Calculator calculator = new Calculator();

	@Test
	void addition1() {
		assertEquals(2, calculator.add(1, 1));
	}

	@Test
	void addition2() {
		assertEquals(4, calculator.add(2, 2));
	}

}