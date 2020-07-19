package exercises.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// https://docs.oracle.com/javase/tutorial/collections/interfaces/QandE/questions.html
// Write a method that takes a List<String> and applies String.trim to each element. 

public class Task_003 {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("  R   ", "G   ","   B");
		
		System.out.println(list);
		System.out.println(trimString(list));
	}
	
	static List<String> trimString(Collection<String> coll){
		return coll.stream().map(String::trim).collect(Collectors.toList());
	}
}

// Correct answer:
//import java.util.*;
//
//public class ListTrim {
//    static void listTrim(List<String> strings) {
//        for (ListIterator<String> lit = strings.listIterator(); lit.hasNext(); ) {
//            lit.set(lit.next().trim());
//        }
//    }
//
//    public static void main(String[] args) {
//        List<String> l = Arrays.asList(" red ", " white ", " blue ");
//        listTrim(l);
//        for (String s : l) {
//            System.out.format("\"%s\"%n", s);
//        }
//    }
//}
