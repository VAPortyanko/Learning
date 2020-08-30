package maps;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Map_ContainAll_RemoveAll {
	public static void main(String[] args) {

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("Admin",     "Danila");
		map.put("Manager",   "Sergey");
		map.put("Developer", "Aleksandr");
		
		Set<String> requiredRoles = new TreeSet<String>();
		Set<String> permitedRoles = new TreeSet<String>();
		
		requiredRoles.add("Admin");
		requiredRoles.add("Developer");
		requiredRoles.add("Director");
		
		permitedRoles.add("Admin");
		permitedRoles.add("Developer");
		permitedRoles.add("HR Manager");		
		
		System.out.println("Is valid: " + validate(map, requiredRoles, permitedRoles));
	}
	
	static <K, V> boolean validate(Map<K, V> attrMap, Set<K> requiredAttrs, Set<K>permittedAttrs) {
	    boolean valid = true;
	    Set<K> attrs = attrMap.keySet();

	    if (! attrs.containsAll(requiredAttrs)) {
	        Set<K> missing = new HashSet<K>(requiredAttrs);
	        missing.removeAll(attrs);
	        System.out.println("Missing attributes: " + missing);
	        valid = false;
	    }
	    if (! permittedAttrs.containsAll(attrs)) {
	        Set<K> illegal = new HashSet<K>(attrs);
	        illegal.removeAll(permittedAttrs);
	        System.out.println("Illegal attributes: " + illegal);
	        valid = false;
	    }
	    return valid;
	}
}

