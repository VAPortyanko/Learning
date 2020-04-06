package by.pva.functionalProgramming.streams.primitiveStreams;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class IntStream_Ex {
	
	public static final String TAB = StringUtils.repeat(" ", 3);
	
	public static void main(String[] args) {
		
		List<String> s2 = Arrays.asList("1", "2", "7");
		
		 IntSummaryStatistics stats = s2.stream()
				                        .mapToInt(Integer::valueOf)
				                        .summaryStatistics();
		
		 System.out.println("Values: " + s2);
		 System.out.println(TAB + "Average value: " + stats.getAverage());
		 System.out.println(TAB + "Count of elements: " + stats.getCount());
		 System.out.println(TAB + "Max value: " + stats.getMax());
		 System.out.println(TAB + "Min value: " + stats.getMin());
		 System.out.println(TAB + "Summa: " + stats.getSum());
 	}
}
