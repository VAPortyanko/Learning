package test;

import java.io.*;
import com.linuxense.javadbf.*;

public class JavaDBFReaderTest {

  public static void main(String args[]) {

    try {

      // create a DBFReader object
      //
      InputStream inputStream  = new FileInputStream(args[0]); // take dbf file as program argument
      DBFReader reader = new DBFReader( inputStream); 

      // get the field count if you want for some reasons like the following
      //
      int numberOfFields = reader.getFieldCount();

      // use this count to fetch all field information
      // if required
      //
      for( int i=0; i<numberOfFields; i++) {

        DBFField field = reader.getField( i);

        // do something with it if you want
        // refer the JavaDoc API reference for more details
        //
        System.out.print(field.getName() + " ");
      }
      System.out.println();
      // Now, lets us start reading the rows
      //
      Object []rowObjects;

      while( (rowObjects = reader.nextRecord()) != null) {

        for( int i=0; i<rowObjects.length; i++) {

          System.out.print(rowObjects[i] + " ");
        }
        
        System.out.println();
      }

      // By now, we have itereated through all of the rows
      inputStream.close();
    }
    catch( DBFException e) {

      System.out.println( e.getMessage());
    }
    catch( IOException e) {

      System.out.println( e.getMessage());
    }
  }  
}  