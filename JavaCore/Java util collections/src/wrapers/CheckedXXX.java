package wrapers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckedXXX {
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] argv) throws Exception 
    { 
        try { 
  
            // creating object of List<String> 
            List arlst = new ArrayList(); 
  
            // Adding element to arlst 
            arlst.add("A"); 
            arlst.add("B"); 
            arlst.add("C"); 
            arlst.add("TajMahal"); 
  
            // printing the arrlist 
            System.out.println("List: " + arlst); 
  
            // create typesafe view of the specified list 
            List tslst = Collections.checkedList(arlst, String.class); 
  
            // printing the arrlist after operation 
            System.out.println("Typesafe view of List: " + tslst);
            
            arlst.add(1);
            System.out.println(arlst);
            tslst.add(1);
        } 
          catch (IllegalArgumentException e) { 
            System.out.println("Exception thrown : " + e); 
        } 
    } 
}
