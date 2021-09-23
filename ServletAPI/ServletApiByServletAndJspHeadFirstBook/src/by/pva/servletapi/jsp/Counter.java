package by.pva.servletapi.jsp;

public class Counter {
	
	private static int count;

	public static synchronized int getCount() {
		count++;
		return count;
	}
}
