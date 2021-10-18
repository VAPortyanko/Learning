package by.pva.springio.beans.instantiating.laziness;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLazinessExample {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("BeanLazinessExample.xml");
		
		System.out.println("The application context has been initialized!");
		
		// The singletonScopedBean object already exists (It has been created during the context initialization).
		SingletonScopedBean singletonScopedBean = (SingletonScopedBean) context.getBean("singletonScopedBean");

		// The prototypeScopedBean will be created for the first time. 
		PrototypeScopedBean prototypeScopedBean = (PrototypeScopedBean) context.getBean("prototypeScopedBean");
		
		// A singleton scoped bean is created during context initialization, 
		// and a prototype scoped bean is created on first use 
		// (injection to another bean or getting it from the context).
		
		// We can change laziness of a singleton scoped bean using the "lazy-init" attribute.
		
		((ClassPathXmlApplicationContext) context).close();
	}
}

/* 

A bean of scope singleton is a bean that is created once per application context. 
A bean of scope prototype is a bean that is instantiated every time.

In other words if your have two classes that autowire a singleton scoped bean, 
all instances of those two classes will reference the same single instance of the bean. 
Doing the same with autowiring a prototype scoped bean will create a new instance for each instance that is autowired.

The property for lazy-init defines when the bean is instantiated: 
As a prototype scoped bean is instantiated each time there is no difference if the property is set to true or false, 
because the bean is instanciated when it is used (either by being autowired or by programmatic retrieval from the context). 

For a singleton scoped bean however it does make a difference:

    If lazy-init is set to false (which is the default), the bean is instantiated on startup.
    If the property is set to true, the bean is instantiated on the first use (through autowiring or programmatic retrieval from the context).

*/
