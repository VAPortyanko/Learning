package by.pva.springio.annotationbasedconfiguration.autowired;

import org.springframework.beans.factory.annotation.Autowired;

public class MainBean {
	
	private final DependencyBean dependency1;
	
	@Autowired
	public MainBean(DependencyBean dependency1) {
		this.dependency1 = dependency1;
	}
	
	public DependencyBean getDependency1() {
		return dependency1;
	}

}
