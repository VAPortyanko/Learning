package lists;

import java.util.Arrays;
import java.util.List;

public class FixedSizeList {

	public static void main(String[] args) {

		int size = 10;
		List<String> list = Arrays.asList(new String[size]);
		list.set(0, "asd");
		// list.add(""); Exception in thread "main" java.lang.UnsupportedOperationException
	}
}
