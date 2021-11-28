package by.pva.springio.configuration.javabasedconfiguration.annotations.bean;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(basePackageClasses = BeanLiteModeExample.class, useDefaultFilters = false,
                    includeFilters = {@ComponentScan.Filter(
                                        type = FilterType.ASSIGNABLE_TYPE,
                                        value = BeanLiteModeExample.TestBean.class)})
public class BeanLiteModeExample {

    public static void main (String[] args) {
    	
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanLiteModeExample.class);
        
        MyBean bean = context.getBean(MyBean.class);
        System.out.println(bean);

        // Accessing multiple times
        bean = context.getBean(MyBean.class);
        System.out.println(bean);

        /* this will return a new instance because @Component methods are not CGLIB proxied */
        TestBean testBean = context.getBean(TestBean.class);
        System.out.println(testBean.myBean());

        System.out.println(context.getBean(TestBean.class)); // Compare with BeanFullModeExample.
        
        context.close();
    }

    @Component
     static class TestBean {
        @Bean
        public MyBean myBean () {
            return new MyBean();
        }
    }
    
}

//https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-java-basic-concepts
//https://www.logicbig.com/tutorials/spring-framework/spring-core/bean-definition-in-components.html
//https://www.logicbig.com/tutorials/spring-framework/spring-core/java-config.html#cgilib-config