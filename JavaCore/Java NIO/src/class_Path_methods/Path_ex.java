// https://docs.oracle.com/javase/tutorial/essential/io/pathOps.html
package class_Path_methods;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Path_ex {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws URISyntaxException {

		/* Creating "Path" instances */
		System.out.println("Creating \"Path\" instances:");
		System.out.println("   p1 = d:/Temp/E53Z/Archives/");
		System.out.println("   p2 = /tmp/foo");
		System.out.println("   p3 = file:///Users/joe/FileTest.java");
		Path p1 = Paths.get("d:/Temp/E53Z/Archives/");
		Path p2 = Paths.get("/tmp/foo");
		Path p3 = Paths.get(URI.create("file:///Users/joe/FileTest.java"));

		/* Retrieving Information about a Path */
		System.out.println("\nRetrieving information about a Path");
		System.out.format("   p1.toString:     %s%n", p1.toString()    );
		System.out.format("   p1.getFileName:  %s%n", p1.getFileName() );
		System.out.format("   p1.getName(0):   %s%n", p1.getName(0)    );
		System.out.format("   p1.getNameCount: %d%n", p1.getNameCount());
		System.out.format("   p1.subpath(0,2): %s%n", p1.subpath(0,2)  );
		System.out.format("   p1.getParent:    %s%n", p1.getParent()   );
		System.out.format("   p1.getRoot:      %s%n", p1.getRoot()     );
		
		/* Removing Redundancies From a Path */
		System.out.println("\nRemoving redundancies From a Path");
		Path p4 = Paths.get("/home/sally/../joe/foo");
		System.out.println("   Ful path:        " + p4);
		System.out.println("   Normalized path: " + p4.normalize());

		/* Converting a Path */
		System.out.println("\nConverting a Path");
		Path p5 = Paths.get("/Home/logFile/");
		System.out.println("   p5.toUri: " + p5.toUri());
		System.out.println("   p5.toAbsolutePath: " + p5.toAbsolutePath());
		try {
			System.out.println("   p5.toRealPath: " + p5.toRealPath(LinkOption.NOFOLLOW_LINKS));
		} catch (NoSuchFileException x) {
		    System.out.format("   Exception: p5 = %s: no such" + " file or directory%n", p5);
		    // Logic for case when file doesn't exist.
		} catch (IOException x) {
		    System.out.format("   Exception: %s%n", x);
		    // Logic for other sort of file error.
		}
		
		/* Joining two paths */
		System.out.println("\nJoining two paths");
		Path p6 = Paths.get("/Home/logFile/");
		Path p7 = Paths.get("log.txt");
		System.out.println("   p6 = " + p6);
		System.out.println("   p7 = " + p7);
		System.out.println("   p6.resolve(p7) = " + p6.resolve(p7));
		
		/* Creating a path between two paths */
		System.out.println("\nCreating a path between two paths");
		Path p8 = Paths.get("D:/Home/logFileS/");
		Path p9 = Paths.get("D:/Home/TextFiles/");
		System.out.println("   p8 = " + p8);
		System.out.println("   p9 = " + p9);
		System.out.println("   p8.relativize(p9) = " + p8.relativize(p9));
		System.out.println("   !!! Paths.get(\"\").toAbsolutePath().toString(); = " + Paths.get("").toAbsolutePath().toString());
		System.out.println("   !!! p8.relativize(p9).toAbsolutePath() = " + p8.relativize(p9).toAbsolutePath());
		
		/* Comparing Two Paths */
		System.out.println("\nComparing two paths");
		Path p10 = Paths.get("D:/Home/logFiles");
		Path p11 = Paths.get("D:/Home");
		Path p12 = Paths.get("logFiles");
		Path p13 = Paths.get("D:/Home/logFiles");
		Path p14 = Paths.get("D:/Home/TextFiles/../logFiles");
		System.out.println("   p10 = " + p10);
		System.out.println("   p11 = " + p11);
		System.out.println("   p12 = " + p12);
		System.out.println("   p13 = " + p13);
		System.out.println("   p14 = " + p14);
		System.out.println("   p10.equils(p13) = " + p10.equals(p13));
		System.out.println("   p10.startsWith(p11) = " + p10.startsWith(p11));
		System.out.println("   p10.endsWith(p12) = " + p10.endsWith(p12));
		System.out.println("   p10.equils(p14) = " + p10.equals(p14));
		try {
			System.out.println("   Files.isSameFile(p10, p14) = " + Files.isSameFile(p10, p14)); // It is true if both files(folders) exist.
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/* Iterating over Path "p10") */
		System.out.print("\nIterating over Path \"p10\"\n   ");
		for(Path name: p10){
			System.out.print("[" + name + "]");
		}
		
		
	}
}