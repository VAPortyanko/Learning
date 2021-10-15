package by.pva.springio.beans.dependencies.injection.property;

import java.util.Properties;

public class MainBean {

    private Dependency1 dependency1;
    private Dependency2 dependency2;
    private int i;
    private Properties properties;
    
	public Dependency1 getDependency1() {
		return dependency1;
	}
	public void setDependency1(Dependency1 dependency1) {
		this.dependency1 = dependency1;
	}
	public Dependency2 getDependency2() {
		return dependency2;
	}
	public void setDependency2(Dependency2 dependency2) {
		this.dependency2 = dependency2;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properies) {
		this.properties = properies;
	}
}
