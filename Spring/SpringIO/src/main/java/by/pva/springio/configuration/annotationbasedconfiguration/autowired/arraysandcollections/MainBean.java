package by.pva.springio.configuration.annotationbasedconfiguration.autowired.arraysandcollections;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

public class MainBean {
	
	@Autowired
	private Dependency[] dependencies;
	
	@Autowired
	Set<Dependency> dependencies2;
	
	@Autowired
	List<Dependency> dependencies3;
	
	@Autowired
	Map<String, Dependency> dependencies4;
	
	public Dependency[] getDependencies() {
		return dependencies;
	}

	public void setDependencies(Dependency[] dependencies) {
		this.dependencies = dependencies;
	}

	public Set<Dependency> getDependencies2() {
		return dependencies2;
	}

	public void setDependencies2(Set<Dependency> dependencies2) {
		this.dependencies2 = dependencies2;
	}

	public List<Dependency> getDependencies3() {
		return dependencies3;
	}

	public void setDependencies3(List<Dependency> dependencies3) {
		this.dependencies3 = dependencies3;
	}

	public Map<String, Dependency> getDependencies4() {
		return dependencies4;
	}

	public void setDependencies4(Map<String, Dependency> dependencies4) {
		this.dependencies4 = dependencies4;
	}
	
}
