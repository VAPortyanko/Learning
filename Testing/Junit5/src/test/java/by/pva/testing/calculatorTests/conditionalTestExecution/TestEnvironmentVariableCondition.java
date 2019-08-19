package by.pva.testing.calculatorTests.conditionalTestExecution;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

public class TestEnvironmentVariableCondition {

	@Test
	@EnabledIfEnvironmentVariable(named = "USERNAME", matches = "Vitaly")
	void onlyIfDeveloerName() {
	    // ...
	}

	@Test
	@DisabledIfEnvironmentVariable(named = "USERNAME", matches = "Vitaly")
	void notDeveloperName() {
	    // ...
	}
	
}

//System.getenv(); // - System variables.