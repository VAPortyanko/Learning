package by.pva.testing.calculatorTests.testDisabling;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Disabled until bug #99 has been fixed")
public class TestDisabling {

	@Test
	void testWillBeSkipped() {
	}

}
