package parametrizedTypes;

public class ParametrizedInterface {
	
	// public static class FooService<T> implements Service<T> {
	public static class FooService implements Service<String> {	
		
	    private final String input1;
	    private final int input2;

	    public FooService(String input1, int input2) {
	       this.input1 = input1;
	       this.input2 = input2;
	    }

	    @Override
	    public String execute() {
	        return String.format("'%s%d'", input1, input2);
	    }
	    
	    public static void main(String[] args) {
			FooService fs = new FooService("10", 33);
			System.out.println(fs.execute());
		} 
	    
	}
	
	public interface Service<T> {
		   T execute();
		}
}
