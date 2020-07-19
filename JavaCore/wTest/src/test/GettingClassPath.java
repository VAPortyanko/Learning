package test;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class GettingClassPath{

   public static void main (String args[]) {
	    /*http://www.mkyong.com/java/how-to-print-out-the-current-project-classpath/*/
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
        	System.out.println(url.getFile());
        }
        
        
        /* http://stackoverflow.com/questions/6572415/finding-the-classpath-of-a-web-app */
        String cp = System.getProperty("java.class.path");
        String pathSep = File.pathSeparator;
        String[] jarOrDirectories = cp.split(pathSep);
        for (String fileName : jarOrDirectories) {
        File file = new File(fileName);
        if (file.isFile()) {
            System.out.println("From jar "+file);
        } else {
        	System.out.println("From folder "+file);
        }
        }
   }
}

