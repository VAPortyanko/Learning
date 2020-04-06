package by.pva.functionalProgramming.streams.operations.collect;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collectors_methods {
	public static void main(String[] args) {

		// Whenever a terminal operation is called on a Stream object, the instance gets consumed and closed. Therefore:
		Supplier<Stream<String>> streamSupplier = () -> Stream.<String>builder().add("b").add("a").add("c").build();
				
		List<String> list = streamSupplier.get()
				                          .collect(Collectors.toList());
		
		Set<String> set = streamSupplier.get()
				                        .collect(Collectors.toSet());
		
		Map<String, String> map = streamSupplier.get()
				                                .collect(Collectors.<String, String, String>toMap((key)->key,String::toUpperCase));
		
		TreeSet<String> treeSet = streamSupplier.get()
                .collect(Collectors.toCollection(TreeSet::new));
                
		System.out.println("List: " + list);
		System.out.println("Set: " + set);
		System.out.println("Map: " + map);
		System.out.println("TreeSet: " + treeSet);
		
		Long count = streamSupplier.get()
                                   .collect(Collectors.counting());
		System.out.println("Stream size: " + count);
		
		List<String> unmodifiableList = streamSupplier.get()
                                                      .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
		
		System.out.println("unmodifiableList: " + unmodifiableList);
		
		String concatenatedString = streamSupplier.get()
                                                  .collect(Collectors.joining(";","{","}"));
		
		System.out.println("concatenatedString: " + concatenatedString);
 		
		Optional<String> maxString = streamSupplier.get()
                                                   .collect(Collectors.maxBy(Comparator.naturalOrder()));
		System.out.println("max string: " + maxString.orElse("None"));

	}
}
