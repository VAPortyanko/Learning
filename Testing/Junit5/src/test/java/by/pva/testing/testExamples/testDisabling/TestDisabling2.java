package by.pva.testing.testExamples.testDisabling;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestDisabling2 {

	@Disabled("Disabled until bug #42 has been resolved")
	@Test
	void testWillBeSkipped() {
	}

	@Test
	void testWillBeExecuted() {
	}

}
