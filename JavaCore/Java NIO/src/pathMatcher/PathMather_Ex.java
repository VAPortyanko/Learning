// https://www.concretepage.com/java/jdk7/example-pathmatcher-java-nio
package pathMatcher;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;

public class PathMather_Ex {
	public static void main(String[] args) {
		
		PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**.{java,class,txt}");

		Path filename = Paths.get("Files/PathMatcher/Test.txt");
		
		if (matcher.matches(filename)) {
		    System.out.println("Matches!");
		} else {
			System.out.println("Not Matches!");
		}
	}
}
