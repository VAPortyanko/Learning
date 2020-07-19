// https://docs.oracle.com/javase/tutorial/essential/io/check.html
package class_Files_methods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;

public class Files_ex {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException, InterruptedException {
		
		Path p1 = Paths.get("D:/temp");
		Path p2 = Paths.get("D:/Test/../temp");
		Path p3 = Paths.get("D:/Temp/E54Z/Archives/E54Z_2018.09.14_14h40m.zip");
				
		System.out.println("Path p1 = " + p1);
		System.out.println("Path p2 = " + p2);
		System.out.println("Path p3 = " + p3);
		
		// Checking files existence.
		System.out.println("   Files.exists(p1): " + Files.exists(p1));
		System.out.println("   Files.notExists(p1): " + Files.notExists(p1));
		
		// File's properties.
		System.out.println("   Files.isSameFile(p1, p2): " + Files.isSameFile(p1, p2));
		
		System.out.println("   Files.isRegularFile(p1): " + Files.isRegularFile(p1));
		System.out.println("   Files.isRegularFile(p3): " + Files.isRegularFile(p3));
		System.out.println("   Files.isExecutable(p3): " + Files.isExecutable(p3));
		System.out.println("   Files.isHidden(p3): "     + Files.isHidden(p3));
		System.out.println("   Files.isReadable(p3): "   + Files.isReadable(p3));
		System.out.println("   Files.isWritable(p3): "   + Files.isWritable(p3));
		
        // Creating a file in a temporary directory.
		Path dir = Files.createTempDirectory("my-dir");
        Path fileToCreatePath = dir.resolve("test-file.txt");
        System.out.println("Create a file in a temp path: " + fileToCreatePath);
        Path newFilePath = Files.createFile(fileToCreatePath);
        System.out.println("New file created: " + newFilePath);
        System.out.println("New File exits: " + Files.exists(newFilePath));
        
		// Deleting a file or a directory.
        System.out.println("\nDeleting file /Files/Delete/Test.txt");
        Path p4 = Paths.get("Files/Delete/Test.txt");
        Path filePath1 = Files.createFile(p4);
        Files.delete(filePath1);  // Method deletes the file or throws an exception if the deletion fails.
        Files.deleteIfExists(p4); // Method also deletes the file, but if the file does not exist, no exception is thrown. 
                                  // Failing silently is useful when you have multiple threads deleting files 
                                  // and you don't want to throw an exception just because one thread did so first.

        // Coping files.
        System.out.println("\nCoping file from Files/Copy/From/test1.txt into Files/Copy/To/test1copy.txt");
        Path p5 = Paths.get("Files/Copy/From/test1.txt");
        Path p6 = Paths.get("Files/Copy/To/test1copy.txt");
        Files.deleteIfExists(p6);
        Files.copy(p5, p6); // The copy fails if the target file exists, unless the REPLACE_EXISTING option is specified.
        
        // Moving files.
		Path p7 = Paths.get("Files/Copy/");
		Path p8 = Paths.get("Files/movedCopy/");
		//Files.move(p7, p8);
		
		
		// Managing metadata (File and file store attributes)
		Path p9 = Paths.get("Files/Metadata/metadata.txt");
		System.out.println("\nMetadata of the_Files/Metadata/metadata.txt file");
		System.out.println("File size is " + Files.size(p9) + " bytes");
		System.out.println("IsDerectory: " + Files.isDirectory(p9));
		System.out.println("IsRegularFile: " + Files.isRegularFile(p9));
		System.out.println("IsSymbolicLink: " + Files.isSymbolicLink(p9));
		System.out.println("IsHidden: " + Files.isHidden(p9));
		
		// Getting and setting last modified time of a file.
		System.out.println("GetLastModifiedTime: " + Files.getLastModifiedTime(p9));
		Files.setLastModifiedTime(p9, FileTime.fromMillis(Files.getLastModifiedTime(p9).toMillis()+1000*60));
		System.out.println("SetLastModifiedTime: " + Files.getLastModifiedTime(p9) + " (Old FileTime + 1 minute)");
		
		// Getting and setting an owner of a file.
		System.out.println("GetOwner: " + Files.getOwner(p9));
		UserPrincipal newOwner = p9.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("�����");
        System.out.println("SetOwner (\"�����\"): ");
        Files.setOwner(p9, newOwner);
        System.out.println("GetOwner: " + Files.getOwner(p9));

        /* The following method was not tested because it threw an UnsupportedOperationException exception.(Windows doesn't support Posix standards)*/
         // Set<PosixFilePermission> set = Files.getPosixFilePermissions(p9);
         // for(PosixFilePermission p:set){
         //	   System.out.println(p.toString());
         // }
        
        // Reading attributes using "getAttribute" method.
        System.out.println("\nReading attributes using \"getAttribute\" method");
        System.out.println("Files.getAttribute(p9, \"basic:size\"): " + Files.getAttribute(p9, "basic:size")); // etAttribute(path, "group:attribute") 
        // ...

        // Reading a file's attributes as a bulk operation.
        System.out.println("\nReading a file's attributes as a bulk operation.");
        BasicFileAttributes attr = Files.readAttributes(p9, BasicFileAttributes.class);
        System.out.println("creationTime: " + attr.creationTime());
        System.out.println("lastAccessTime: " + attr.lastAccessTime());
        System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
        System.out.println("isDirectory: " + attr.isDirectory());
        System.out.println("isOther: " + attr.isOther());
        System.out.println("isRegularFile: " + attr.isRegularFile());
        System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
        System.out.println("size: " + attr.size());
        
		
	}
}

