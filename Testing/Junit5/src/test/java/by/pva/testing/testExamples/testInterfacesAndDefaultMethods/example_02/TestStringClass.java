package by.pva.testing.testExamples.testInterfacesAndDefaultMethods.example_02;

import by.pva.testing.testExamples.testInterfacesAndDefaultMethods.example_02.interfaces.ComparableContract;
import by.pva.testing.testExamples.testInterfacesAndDefaultMethods.example_02.interfaces.EqualsContract;

class TestStringClass implements ComparableContract<String>, EqualsContract<String> {

	@Override
	public String createValue() {
		return "banana";
	}

	@Override
	public String createSmallerValue() {
		return "apple"; // 'a' < 'b' in "banana"
	}

	@Override
	public String createNotEqualValue() {
		return "cherry";
	}

}
