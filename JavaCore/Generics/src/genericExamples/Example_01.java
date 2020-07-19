package genericExamples;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

// Write a generic method to count the number of elements in a collection
// that have a specific property (for example, odd integers, prime numbers, palindromes).

@SuppressWarnings("unused")
public final class Example_01 {
    public static <T> int countIf(Collection<T> c, UnaryPredicate<T> p) {

        int count = 0;
        for (T elem : c)
            if (p.test(elem))
                ++count;
        return count;
        // return c.stream().filter(p::test).collect(Collectors.counting()).intValue();
        // return c.stream().filter(p::test).map(e->1L).reduce((acc, el) -> acc + el).orElse(new Long(0L)).intValue();
    }
    
    public static void main(String[] args) {
		System.out.println(countIf(Collections.<String>emptyList(), e->e.endsWith(".")));
		System.out.println(countIf(Arrays.asList("Stop.", "Start","End."), e->e.endsWith(".")));
	}
    
    interface UnaryPredicate<T> {
        public boolean test(T obj);
    }
}


