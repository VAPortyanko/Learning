package file;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Calendar;

public class File_methods {
	public static void main(String[] args) {
		
// *************************************************************************************************** //		
//                                        Define six files.                                            //
// *************************************************************************************************** //		
		File f1 = new File("Files/TestFile.txt");
		File f2 = new File("Files/NewTestFile.txt");
		File f3 = new File("Files/NewTestFile2.txt");
		File f4 = new File("Files/");
		File f5 = new File("Files/catalogA");
		File f6 = new File("Files/catalogB/catalogC");

		System.out.println("Define 6 files:");
		
// *************************************************************************************************** //		
//                              Get path, absolute and canonical paths.                                //
// *************************************************************************************************** //		
		// path
		System.out.println("     f1: " + f1.getPath());
		System.out.println("     f2: " + f2.getPath());
		System.out.println("     f3: " + f3.getPath());
		System.out.println("     f4: " + f4.getPath());
		System.out.println("     f5: " + f5.getPath());
		System.out.println("     f6: " + f6.getPath());
		
		// Absolute path.
		System.out.println("\nF1 file absolute path: " + f1.getAbsolutePath());
		
		// Canonical path. 
		try {
			System.out.println("F1 file canonical path: " + f1.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

// *************************************************************************************************** //		
//                                          Check if a file can be ...                                 //
// *************************************************************************************************** //			
		System.out.println("\nFile F1 can be ...");
		System.out.println("     execute: " + f1.canExecute());
		System.out.println("     read   : " + f1.canRead());
		System.out.println("     write  : " + f1.canWrite());

// *************************************************************************************************** //		
//                                            Create new files                                         //
// *************************************************************************************************** //			
		try {
			System.out.println("\nCreate two new files:");
			System.out.println("Create new file - F2: " + f2.getCanonicalPath()+ " - " + f2.createNewFile());
			System.out.println("Create new file - F3: " + f3.getCanonicalPath()+ " - " + f3.createNewFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
// *************************************************************************************************** //		
//                                            Delete file                                              //
// *************************************************************************************************** //		
		
		System.out.println("\nDelete file F2: " + f2 + " : " + f2.delete());
		
// *************************************************************************************************** //		
//                                         check if exist                                              //
// *************************************************************************************************** //
		System.out.println("\nCheck if the file exist:");
		System.out.println("File " + f1 + " exist ? : " + f1.exists());
		System.out.println("File " + f2 + " exist ? : " + f2.exists());
		
// *************************************************************************************************** //		
//                                            Memory space                                             //
// *************************************************************************************************** //
		System.out.println("\nTotal space of the current drive: " + f1.getTotalSpace());
		System.out.println("Free space: " + f1.getFreeSpace());
		System.out.println("UsableSpace: " + f1.getUsableSpace());
		System.out.println("Size of the F1 file: " + f1.length());
		
// *************************************************************************************************** //		
//                                         File properties                                             //
// *************************************************************************************************** //
		System.out.println("\nF1 file properties:");
		System.out.println("     File name: " + f1.getName());
		System.out.println("     Parent folder: " + f1.getParent());
		System.out.println("     Is file: " + f1.isFile());
		System.out.println("     Is folder: " + f1.isDirectory());
		System.out.println("     The path is absolute: " + f1.isAbsolute());
		System.out.println("     Is hidden: " + f1.isHidden());
		Calendar cln = Calendar.getInstance();
		cln.setTimeInMillis(f1.lastModified());
		System.out.println("     Last modification time of the F1 file: " + cln.getTime());
		
// *************************************************************************************************** //		
//                                              File list                                              //
// *************************************************************************************************** //
		System.out.println("\nList of files and folders (for F4):");
		
		// Get list of files (Strings).
		String[] list = f4.list();
		for (String a:list)
			System.out.println("     " + a);
		// Get list of files (Files).
		//File[] list2 = f4.listFiles();
		//for (File b:list2)
		//	System.out.println(b);

// *************************************************************************************************** //		
//                                           FileNameFiler                                             //
// *************************************************************************************************** //		
		System.out.println("\nOnly *.txt files: ");
		// лист с фильтром только для текстовых фалйов.
		String[] list3 = f4.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				
				  if(name.lastIndexOf('.')>0)
	               {
	                  // get last index for '.' char
	                  int lastIndex = name.lastIndexOf('.');
	                  
	                  // get extension
	                  String str = name.substring(lastIndex);
	                  
	                  // match path name extension
	                  if(str.equals(".txt"))
	                  {
	                     return true;
	                  }
	               }
	               return false;
			}
		});
		for (String a:list3)
			System.out.println(a);
		
// *************************************************************************************************** //		
//                        Create folders and subfolders + Rename(replace)                              //
// *************************************************************************************************** //	
		System.out.println("\nCreate folder (for F5) (if all folders in the path exist)");
		f5.mkdir();
		System.out.println("Create folder (for F6) with all necessary parent folders");
		f6.mkdirs();
		System.out.println("Sleep 5 seconds ...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("... and rename (replace) f5 folder");
		f5.renameTo(new File("Files/catalogB/CatalogC/CatalogA"));
		
// *************************************************************************************************** //		
//                                          Delete file on exit                                        //
// *************************************************************************************************** //
		System.out.println("\nWhen the jvm will terminated delete F3 file");
		f3.deleteOnExit();
		System.out.println("We are waiting for five seconds during which the file will exist");
		try {
			Thread.sleep(5000); // 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

