// The disjoint(Collection<?>, Collection<?>) method returns 'true' if the two specified collections
// have no elements in common.
package collectionsClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisJoint_method {
   public static void main(String args[]) {

      List<String> srclst = new ArrayList<String>(5);
      List<String> destlst = new ArrayList<String>(10);

      srclst.add("Java");
      srclst.add("is");
      srclst.add("best");

      destlst.add("C++");
      destlst.add("is not");
      destlst.add("older");      

      // check elements in both collections
      boolean iscommon = Collections.disjoint(srclst, destlst);

      System.out.println("No commom elements: " + iscommon);    
   }    
}
