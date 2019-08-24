package by.pva.testing.testExamples.testInterfacesAndDefaultMethods.example_01;

import org.junit.jupiter.api.Test;

import by.pva.testing.testExamples.testInterfacesAndDefaultMethods.example_01.interfaces.IDynamicTest;
import by.pva.testing.testExamples.testInterfacesAndDefaultMethods.example_01.interfaces.ILifecycleLogger;
import by.pva.testing.testExamples.testInterfacesAndDefaultMethods.example_01.interfaces.ITimeExecutionLoger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestInterface implements ILifecycleLogger, ITimeExecutionLoger, IDynamicTest {

	@Test
	void isEqualValue() {
		assertEquals(1, "a".length(), "is always equal");
	}

}