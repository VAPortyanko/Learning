package by.pva.testing.calculatorTests.conditionalTestExecution.deprecated;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.EnabledIf;

// Conditional test execution via @EnabledIf and @DisabledIf is deprecated
// for removal in a future release of JUnit Jupiter. 

// JUnit Jupiter provides the ability to either enable or disable 
// a container or test depending on the evaluation of a script configured
// via the @EnabledIf or @DisabledIf annotation. Scripts can be written in 
// JavaScript, Groovy, or any other scripting language for which there is 
// support for the Java Scripting API, defined by JSR 223.

@SuppressWarnings("deprecation")
public class TestScriptBasedCondition {

	@Test // Static JavaScript expression.
	@EnabledIf("2 * 3 == 6")
	void willBeExecuted() {
		// ...
	}

	@RepeatedTest(10) // Dynamic JavaScript expression.
	@DisabledIf("Math.random() < 0.314159")
	void mightNotBeExecuted() {
		// ...
	}

	@Test // Regular expression testing bound system property.
	@DisabledIf("/32/.test(systemProperty.get('os.arch'))")
	void disabledOn32BitArchitectures() {
		assertFalse(System.getProperty("os.arch").contains("32"));
	}

	@Test
	@EnabledIf("'CI' == systemEnvironment.get('ENV')")
	void onlyOnCiServer() {
		assertTrue("CI".equals(System.getenv("ENV")));
	}

	@Test // Multi-line script, custom engine name and custom reason.
	@EnabledIf(value = { "load('nashorn:mozilla_compat.js')", "importPackage(java.time)", "",
			"var today = LocalDate.now()", "var tomorrow = today.plusDays(1)",
			"tomorrow.isAfter(today)" }, engine = "nashorn", reason = "Self-fulfilling: {result}")
	void theDayAfterTomorrow() {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);
		assertTrue(tomorrow.isAfter(today));
	}

}
