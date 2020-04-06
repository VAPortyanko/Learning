package by.pva.testing.testExamples.testInterfacesAndDefaultMethods.example_01.interfaces;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import by.pva.testing.testExamples.utils.StringUtils;

public interface IDynamicTest {

    @TestFactory
    default Stream<DynamicTest> dynamicTestsForPalindromes() {
        return Stream.of("racecar", "radar", "mom", "dad").map(text -> dynamicTest(text, () -> assertTrue(StringUtils.isPalindrome(text))));
    }

}