package by.pva.testing.calculatorTests.displayName;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A special test case")
public class TestDisplayName {
	@Test
	@DisplayName("Custom test name containing spaces")
	void testWithDisplayNameContainingSpaces() {
	}

	@Test
	@DisplayName("╯°□°）╯")
	void testWithDisplayNameContainingSpecialCharacters() {
	}

	// https://stackoverflow.com/questions/50201853/junit-string-compare-fails-for-emoji
//	@Test
//	@DisplayName("😱") // Here must be an emoji image.
//	void testWithDisplayNameContainingEmoji() {
//	}
}
