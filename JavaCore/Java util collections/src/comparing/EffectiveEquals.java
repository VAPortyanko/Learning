package comparing;

public class EffectiveEquals {
	private int valueA;
	private int valueB;
	// ...

	public boolean equals( Object o ) {
	        if(this == o) {  // Step 1: Perform an == test
	            return true;
	        }
	        if(!(o instanceof EffectiveEquals)) {  // Step 2: Instance of check
	            return false;
	        }
	        EffectiveEquals ee = (EffectiveEquals) o; // Step 3: Cast argument
	        // Step 4: For each important field, check to see if they are equal
	        // For primitives use ==
	        // For objects use equals() but be sure to also
	        // handle the null case first
	        return ee.valueA == valueA && ee.valueB == valueB;
	    }
	// ...
}

// https://medium.com/coding-corpus/java-important-methods-equals-hashcode-and-compareto-6adcdf2814c3