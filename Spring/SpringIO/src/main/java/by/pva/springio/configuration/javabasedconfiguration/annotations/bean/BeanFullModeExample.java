package by.pva.springio.configuration.javabasedconfiguration.annotations.bean;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackageClasses = BeanFullModeExample.class, useDefaultFilters = false,
                    includeFilters = {@ComponentScan.Filter(
                                        type = FilterType.ASSIGNABLE_TYPE,
                                        value = BeanFullModeExample.TestBean.class)})
public class BeanFullModeExample {

    public static void main (String[] args) {
    	
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanFullModeExample.class);
        
        MyBean bean = context.getBean(MyBean.class);
        System.out.println(bean);

        // Accessing multiple times
        bean = context.getBean(MyBean.class);
        System.out.println(bean);

        /* This will return same instance because @Configuration classes even in scan path will be CGLIB proxied */
        TestBean testBean = context.getBean(TestBean.class);
        System.out.println(testBean.myBean());
        
        System.out.println(context.getBean(TestBean.class)); // Compare with BeanLiteModeExample.
        
        context.close();
    }

    @Configuration
    static class TestBean {
        @Bean
        public MyBean myBean () {
            return new MyBean();
        }
    }
}

// https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-java-basic-concepts
// https://www.logicbig.com/tutorials/spring-framework/spring-core/bean-definition-in-components.html
// https://www.logicbig.com/tutorials/spring-framework/spring-core/java-config.html#cgilib-config