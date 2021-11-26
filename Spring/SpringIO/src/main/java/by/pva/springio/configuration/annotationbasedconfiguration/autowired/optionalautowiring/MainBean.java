package by.pva.springio.configuration.annotationbasedconfiguration.autowired.optionalautowiring;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class MainBean {
	
	/* 
	*  By default, autowiring fails when no matching candidate beans are available for a given injection point.
	*  You can change this behavior:
	*/
	
	// 1) Set the "required" attribute to "false" ("true" is the default value).
	@Autowired(required = false) 
	Dependency1 dependency1;
	
	// 2) Alternatively, you can express the non-required nature of a particular dependency through Java 8’s java.util.Optiona
	@Autowired
	Optional<Dependency2> dependency2; 
	
	// 3) As of Spring Framework 5.0, you can also use a @Nullable annotation 
    //    (of any kind in any package — for example, javax.annotation.Nullable from JSR-305).
	@Autowired
	@Nullable  
	Dependency3 dependency3;
	
	public Dependency1 getDependency1() {
		return dependency1;
	}
	public void setDependency1(Dependency1 dependency1) {
		this.dependency1 = dependency1;
	}
	public Optional<Dependency2> getDependency2() {
		return dependency2;
	}
	public void setDependency2(Optional<Dependency2> dependency2) {
		this.dependency2 = dependency2;
	}
	public Dependency3 getDependency3() {
		return dependency3;
	}
	public void setDependency3(Dependency3 dependency3) {
		this.dependency3 = dependency3;
	}
	
}
