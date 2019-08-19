package by.pva.testing.testExamples.conditionalTestExecution;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;

public class TestJavaRuntimeEnvironmentCondition {
	
	@Test
	@EnabledOnJre(JRE.JAVA_8)
	void onlyOnJava8() {
		// ...
	}

	@Test
	@DisabledOnJre(JRE.JAVA_8)
	void notOnJava8() {
		// ...
	}

	@Test
	@EnabledOnJre({ JRE.JAVA_9, JRE.JAVA_10 })
	void onJava9Or10() {
		// ...
	}

}