package class_Files_methods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileTypeDetector;

public class OtherFilesMethods_ex {
	public static void main(String[] args) {
		
		// Determinating type of files.
		System.out.println("Determinating type of files.");
		Path p1 = Paths.get("Files/FileTypes/1.txt");
		Path p2 = Paths.get("Files/FileTypes/1.html");
		Path p3 = Paths.get("Files/FileTypes/WatchDir.java");
		Path p4 = Paths.get("Files/FileTypes/WatchDir.class");
		
		try {
		    String type1 = Files.probeContentType(p1);
		    String type2 = Files.probeContentType(p2);
		    String type3 = Files.probeContentType(p3);
		    String type4 = Files.probeContentType(p4);
		    
		    System.out.println("   File [" + p1 + "] has a type \"" + (type1 == null?"unknown":type1) + "\"");
		    System.out.println("   File [" + p2 + "] has a type \"" + (type2 == null?"unknown":type2) + "\"");
		    System.out.println("   File [" + p3 + "] has a type \"" + (type3 == null?"unknown":type3) + "\"");
		    System.out.println("   File [" + p4 + "] has a type \"" + (type4 == null?"unknown":type4) + "\"");
		    
		} catch (IOException x) {
		    System.err.println(x);
		}
		
		// Determining the type of files using my own FileTypeDetector.
		System.out.println("\nDetermining the type of files using my own FileTypeDetector.");
		FileTypeDetector myFTD = new FileTypeDetector() {
			@Override
			public String probeContentType(Path path) throws IOException {
				if (path.toString().endsWith(".java")){
					return "text/java";
				} else {
					return "Unknown";
				}
			}
		}; 
		
		try {
			String type3_u = myFTD.probeContentType(p3);
			System.out.println("   File [" + p3 + "] has a type \"" + (type3_u == null?"unknown":type3_u) + "\"");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
