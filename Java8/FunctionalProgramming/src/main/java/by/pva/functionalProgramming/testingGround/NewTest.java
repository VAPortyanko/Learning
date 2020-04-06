package by.pva.functionalProgramming.testingGround;

import java.util.Arrays;
import java.util.List;

public class NewTest {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("1", "2", "3");
		
	int result =  list.stream()
		.map(string-> Integer.valueOf(string))
		.reduce(0, (acc, el) -> acc + el)
		.intValue();

	System.out.println(result);
	}
}
