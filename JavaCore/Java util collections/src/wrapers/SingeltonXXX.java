package wrapers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SingeltonXXX {
	public static void main(String[] args) {
		Map<String, String> job = new HashMap<String, String>();
		// put...
		job.values().removeAll(Collections.singleton("LAWYER"));
	}
}
