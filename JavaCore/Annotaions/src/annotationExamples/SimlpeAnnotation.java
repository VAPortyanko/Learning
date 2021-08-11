package annotationExamples;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SimpleAnno(myFirstAnnotation = "AnnotatedClass", numAnno = 1)
public class SimlpeAnnotation {

	@SimpleAnno(myFirstAnnotation = "AnnotatedMethod", numAnno = 2)
	@SimpleAnno2(isTrue = true)
	@SimpleAnno3
	@SimpleAnno4
	@SimpleAnno5(100)
	@SimpleAnno6(100)
	public void say(){ // The method must be public in order to be visible through getMethod method.
		System.out.println("Method 'Say'");
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {

		SimlpeAnnotation a = new SimlpeAnnotation();

		SimpleAnno ann1 = a.getClass().getAnnotation(SimpleAnno.class); // Receive an instance of SimpleAnno annotation for the class.
		System.out.println(ann1);
		
		SimpleAnno ann2 = a.getClass().getMethod("say").getAnnotation(SimpleAnno.class); // Receive an instance of SimpleAnno annotation for the method.
		System.out.println(ann2);
		
		// Get a list of all annotations of the "say" method.
		System.out.println();
		System.out.println("List of method's annotations:");
		Annotation[] arrayOfAnn2 = a.getClass().getMethod("say").getAnnotations();
		
		for(Annotation arr2: arrayOfAnn2)
			System.out.println(arr2);
		
	}
}

@Retention(RetentionPolicy.RUNTIME) // Annotation "Retention" make the SimpleAnno annotation available in runtime.
@interface SimpleAnno{
	String myFirstAnnotation();
	int numAnno();
}

@Retention(RetentionPolicy.RUNTIME)
@interface SimpleAnno2{
	boolean isTrue();
}

// Annotation with value by default.
@Retention(RetentionPolicy.RUNTIME)
@interface SimpleAnno3{
	int count() default -3;
}

// Annotation-marker
@Retention(RetentionPolicy.RUNTIME) 
@interface SimpleAnno4{
}

// Annotation-monomial (odnochlen).
@Retention(RetentionPolicy.RUNTIME) 
@interface SimpleAnno5{
	int value(); // It must be the name "value". In this case, you can not specify "value = 100", but simply write 100 in the annotation declaration.
}

//Annotation-monomial (odnochlen).
@Retention(RetentionPolicy.RUNTIME)
@interface SimpleAnno6{
	int value(); 
	String str() default "Hello"; // With monomial we can specify another value by default, but can't change it in declaration. Else we must have declaration as (val1=val2, val2=val2)
}