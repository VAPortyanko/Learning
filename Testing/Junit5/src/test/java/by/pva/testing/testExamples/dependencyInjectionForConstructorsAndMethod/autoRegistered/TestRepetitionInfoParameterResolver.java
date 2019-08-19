package by.pva.testing.testExamples.dependencyInjectionForConstructorsAndMethod.autoRegistered;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

public class TestRepetitionInfoParameterResolver {
	
	@RepeatedTest(5)
	void test(RepetitionInfo repitionInfo) {
		System.out.println("<---- TestRepetitionInfoParameterResolver.test.repition " + repitionInfo.getCurrentRepetition() + "/" + repitionInfo.getTotalRepetitions() + " ---->");
	}
}
