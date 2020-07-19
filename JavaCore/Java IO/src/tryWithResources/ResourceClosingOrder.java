package tryWithResources;

public class ResourceClosingOrder {
	public static void main(String[] args) throws Exception {
		// Resources that were defined/acquired first will be closed last:
		try (AutoCloseableResourcesFirst  af = new AutoCloseableResourcesFirst(); 
			 AutoCloseableResourcesSecond as = new AutoCloseableResourcesSecond()) {

			af.doSomething();
			as.doSomething();
		}
	}
}

class AutoCloseableResourcesFirst implements AutoCloseable {

	public AutoCloseableResourcesFirst() {
		System.out.println("Constructor -> AutoCloseableResources_First");
	}

	public void doSomething() {
		System.out.println("Something -> AutoCloseableResources_First");
	}

	@Override
	public void close() throws Exception {
		System.out.println("Closed AutoCloseableResources_First");
	}
}

class AutoCloseableResourcesSecond implements AutoCloseable {

	public AutoCloseableResourcesSecond() {
		System.out.println("Constructor -> AutoCloseableResources_Second");
	}

	public void doSomething() {
		System.out.println("Something -> AutoCloseableResources_Second");
	}

	@Override
	public void close() throws Exception {
		System.out.println("Closed AutoCloseableResources_Second");
	}
}