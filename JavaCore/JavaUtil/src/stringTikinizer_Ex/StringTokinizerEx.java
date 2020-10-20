package stringTikinizer_Ex;
import java.util.StringTokenizer;

public class StringTokinizerEx {
	public static void main(String[] args) {
		// Space and coma are delimeters.
		StringTokenizer strToken = new StringTokenizer("Hello,  my name is Vitaly", ", ", true);
		while(strToken.hasMoreElements()){
			System.out.println("\"" + strToken.nextToken() + "\"");
		}
		
		System.out.println();
		
		strToken = new StringTokenizer("Hello,  my name is Vitali", ", ", false);
		while(strToken.hasMoreElements()){
			System.out.println("\"" + strToken.nextToken() + "\"");
		}
	}
}
