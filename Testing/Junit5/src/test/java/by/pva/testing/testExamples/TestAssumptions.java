package by.pva.testing.testExamples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.Test;

import by.pva.testing.classesForTesting.Calculator;

public class TestAssumptions {
	private final Calculator calculator = new Calculator();

	@Test
	void testOnlyOnCiServer() {
		assumeTrue("CI".equals(System.getenv("ENV")));
		// remainder of test
	}

	@Test
	void testOnlyOnDeveloperWorkstation() {
		assumeTrue("DEV".equals(System.getenv("ENV")), () -> "Aborting test: not on developer workstation");
		// remainder of test
	}

	@Test
	void testInAllEnvironments() {
		assumingThat("CI".equals(System.getenv("ENV")), () -> {
			// perform these assertions only on the CI server
			assertEquals(2, calculator.divide(4, 2));
		});

		// perform these assertions in all environments
		assertEquals(42, calculator.multiply(6, 7));
	}
	
	@Test
	void testMy(){
		assumingThat(2 > 1, () -> {
			// Always perform these assertions
			assertEquals(2, calculator.divide(4, 2));
		});

		assumeTrue("Hello".length() == 5);
		// Always perform these assertions
		assertEquals(42, calculator.multiply(6, 7));
	}	
}
