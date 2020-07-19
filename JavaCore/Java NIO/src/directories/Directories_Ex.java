// https://docs.oracle.com/javase/tutorial/essential/io/dirs.html
package directories;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Directories_Ex {

	public static void main(String[] args) {
		
		// 1). Listing a File System's Root Directories.
		System.out.print("1). Listing a File System's Root Directories:\n   ");
		
		Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
		for (Path name: dirs) {
		    System.out.print(name);
		}
		
		// 2). Creating a Directory.
		System.out.println("\n\n2). Creating a Directory:");
		
		Path directory = Paths.get("Files/Directories/");
		Path directories = Paths.get("Files/Directories/DIR1/DIR2/DIR3/");
		
		try {
			Files.createDirectory(directory);
			System.out.println("Directory [" + directory + "] was successfully created!");
		} catch (FileAlreadyExistsException e) {
			System.out.println("   FileAllreadyExistException: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Files.createDirectories(directories);
			System.out.println("   Directories [" + directories + "] were successfully created!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// 3). Creating a Temporary Directory.
		System.out.println("\n3). Creating a Temporary Directory and wait 5 seconds:");
		
		Path tempDirectory = Paths.get("Files/Temp");
		
		try {
			Path tmpDir = Files.createTempDirectory(tempDirectory, "temp");
			// Path tmpDir2 = Files.createTempDirectory("temp"); Windows temp folder/temp/
			tmpDir.toFile().deleteOnExit(); // If empty.
			System.out.println("   " + tmpDir);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 4). Listing a Directory's Contents.
		System.out.println("\n4). Listing a Directory's Contents:");
		
		Path listingDirectory = Paths.get("Files/Directories/Listing/");
		try(DirectoryStream<Path> dstream = Files.newDirectoryStream(listingDirectory)){
			for (Path p:dstream){
				System.out.println("   " + p + (Files.isDirectory(p)?"   {Directory}":"   {File}"));
			}
		}catch(DirectoryIteratorException e1){
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		// 5). Filtering a Directory Listing By Using Globbing.
		System.out.println("\n5). Filtering a Directory Listing By Using Globbing:");
		try (DirectoryStream<Path> dstream2 = Files.newDirectoryStream(listingDirectory, "*.{log}")) {
			for (Path p : dstream2) {
				System.out.println("   " + p  + (Files.isDirectory(p)?"   {Directory}":"   {File}"));
			}
		} catch (IOException x) {
			System.err.println(x);
		}
		
		// 6). Writing Your Own Directory Filter.
		System.out.println("\n6). Writing Your Own Directory Filter:");
		DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
			@Override
			public boolean accept(Path entry) throws IOException{
				return (Files.isDirectory(entry));
			}
		};
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(listingDirectory, filter)) {
			for (Path p : stream) {
				System.out.println("   " + p  + (Files.isDirectory(p)?"   {Directory}":"   {File}"));
			}
		} catch (IOException x) {
			System.err.println(x);
		} 
		
	}
}
