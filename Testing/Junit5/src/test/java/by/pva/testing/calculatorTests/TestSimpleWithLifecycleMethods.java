package by.pva.testing.calculatorTests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import by.pva.testing.classesForTesting.Calculator;

public class TestSimpleWithLifecycleMethods {

	private static Calculator calculator;
	
	@BeforeAll
	static void initAll() {
		calculator = new Calculator(); 
	}

	@BeforeEach
	void init() {
	}

	@Test
	void succeedingTest() {
		assertTrue(calculator != null);
	}

	@Test
	void failingTest() {
		fail("a failing test");
	}

	@Test
	@Disabled("for demonstration purposes")
	void skippedTest() {
		// not executed
	}

	@Test
	void abortedTest() {
		assumeTrue("abc".contains("Z"));
		fail("test should have been aborted");
	}

	@AfterEach
	void tearDown() {
	}

	@AfterAll
	static void tearDownAll() {
		calculator = null;
	}

}
