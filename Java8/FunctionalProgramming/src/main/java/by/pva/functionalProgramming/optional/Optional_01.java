package by.pva.functionalProgramming.optional;

import java.util.Optional;

public class Optional_01 {

	public static void main(String[] args) throws MyOptionalException {
		Optional<String> o1 = Optional.of("1");
		// Optional<String> o1 = Optional.of(null); // NulPointerException;
		Optional<String> o2 = Optional.ofNullable(null);
		// Optional<String> o2 = Optional.empty(); // This is a better way;
		
		System.out.println(o1.get());
		// System.out.println(o2.get()); // NoSuchElementException
		
		System.out.println(o2.equals(Optional.empty()));              // true
		System.out.println(o2.orElse("defaultValue"));                // defaultValue
		System.out.println(o2.orElseGet(() -> "derivedValue"));       // derivedValue
		System.out.println(o2.orElseThrow(MyOptionalException::new)); // throws MyOptionalException
	}
}

@SuppressWarnings("serial")
class MyOptionalException extends Exception{
	public MyOptionalException() {
		super("No value is present!");
	}
} 

/*
 * See https://dzone.com/articles/using-optional-correctly-is-not-optional
 * 
 * 1). Never Assign Null to an Optional Variable.
 * 
 * Avoid: Optional<String> emptyCart1 = null; Prefer: Optional<String>
 * emptyCart2 = Optional.empty();
 * 
 * 2). ...
 * 
 * 
 */
