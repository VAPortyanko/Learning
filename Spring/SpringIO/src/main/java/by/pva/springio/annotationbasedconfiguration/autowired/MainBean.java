package by.pva.springio.annotationbasedconfiguration.autowired;

import org.springframework.beans.factory.annotation.Autowired;

public class MainBean {
	
	private final DependencyBean dependency1;
	
	// As of Spring Framework 4.3, an @Autowired annotation on such a constructor is no longer necessary
	// if the target bean defines only one constructor to begin with. However,
	// if several constructors are available and there is no primary/default constructor,
	// at least one of the constructors must be annotated with @Autowired in order to
	// instruct the container which one to use.
	@Autowired
	public MainBean(DependencyBean dependency1) {
		this.dependency1 = dependency1;
	}
	
	public DependencyBean getDependency1() {
		return dependency1;
	}

}
