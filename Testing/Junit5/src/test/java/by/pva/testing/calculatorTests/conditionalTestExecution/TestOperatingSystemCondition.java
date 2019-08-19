package by.pva.testing.calculatorTests.conditionalTestExecution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import by.pva.testing.classesForTesting.Calculator;
import by.pva.testing.customAnnotations.composedAnnotation.TestOnWindows;

public class TestOperatingSystemCondition {
	
	 private final Calculator calculator = new Calculator(); 
	
	@Test
	@EnabledOnOs(OS.MAC)
	void onlyOnMacOs() {
		// ...
	}

	@Test
	@DisabledOnOs(OS.WINDOWS)
	void notOnWindows() {
		// ...
	}

	@TestOnWindows
	void testOnWindows() {
		assertEquals(2, calculator.add(1, 1));
	}

	@Test
	@EnabledOnOs({ OS.LINUX, OS.MAC })
	void onLinuxOrMac() {
		// ...
	}

}
