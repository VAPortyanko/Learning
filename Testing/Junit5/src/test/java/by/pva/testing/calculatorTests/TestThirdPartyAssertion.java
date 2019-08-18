package by.pva.testing.calculatorTests;

import org.junit.jupiter.api.Test;

import by.pva.testing.classesForTesting.Calculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestThirdPartyAssertion {
	private final Calculator calculator = new Calculator();

	@Test
	void assertWithHamcrestMatcher() {
		assertThat(calculator.subtract(4, 1), is(equalTo(3)));
	}
}
