package by.pva.springio.beans.dependencies.injection.autowiring;

public class MainBean {
	
	private String name;
	private Bean1 bean1;
	private Bean2 bean2;
	
	public MainBean(String name, Bean1 bean1, Bean2 bean2) {
		this.name = name;
		this.bean1 = bean1;
		this.bean2 = bean2;
	}

	public String getName() {
		return name;
	}

	public Bean1 getBean1() {
		return bean1;
	}

	public Bean2 getBean2() {
		return bean2;
	}
	
}
