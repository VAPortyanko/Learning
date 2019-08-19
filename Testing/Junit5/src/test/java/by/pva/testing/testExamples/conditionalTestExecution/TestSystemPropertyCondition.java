package by.pva.testing.testExamples.conditionalTestExecution;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

public class TestSystemPropertyCondition {
	
	@Test
	@EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
	void onlyOn64BitArchitectures() {
	    // ...
	}

	@Test
	@DisabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
	void notOn64BitArchitecturesr() {
	    // ...
	}
}


// System.getProperties(); - System properties.