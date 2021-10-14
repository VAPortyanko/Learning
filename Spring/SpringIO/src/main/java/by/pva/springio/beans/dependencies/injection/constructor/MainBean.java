package by.pva.springio.beans.dependencies.injection.constructor;

public class MainBean {

    private final Dependency1 dependency1;
    private final Dependency2 dependency2;
    
    public MainBean(Dependency1 dependency1, Dependency2 dependency2) {
        this.dependency1 = dependency1;
        this.dependency2 = dependency2;
    }

	public Dependency1 getDependency1() {
		return dependency1;
	}

	public Dependency2 getDependency2() {
		return dependency2;
	}
	
}
