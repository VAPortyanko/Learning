package by.pva.springio.configuration.annotationbasedconfiguration.autowired.arraysandcollections;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ArrayAndListAutowiredExample {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("ArraysAndCollectionsAutowiredExample.xml");
		MainBean bean = (MainBean) context.getBean("mainBean");
		
		System.out.println("Array: \n" + Arrays.toString(bean.getDependencies()));
		System.out.println("Set: ");
		bean.getDependencies2().forEach(System.out::println);
		System.out.println("List (ordered!): ");
		bean.getDependencies3().forEach(System.out::println);
		System.out.println("Map: ");
		bean.getDependencies4().forEach((key, value) -> System.out.println(key + "(key) " + value + "(value)"));		
		
		((ClassPathXmlApplicationContext) context).close();
	}
	
}
