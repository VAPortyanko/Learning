package by.pva.functionalProgramming.streams.operations.collect;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PartitioningBy_ex {

	public static void main(String[] args) {
		
		Map<Boolean, List<Integer>> map = Stream.of(1, 2, 3 ,4 ,5 , 6)
				                                .collect(Collectors.partitioningBy((element)->element % 2 == 0));
		
		List<Integer> list01 = map.get(true);
		List<Integer> list02 = map.get(false);
		
	
		System.out.println(list01);
		System.out.println(list02);

	}
}
