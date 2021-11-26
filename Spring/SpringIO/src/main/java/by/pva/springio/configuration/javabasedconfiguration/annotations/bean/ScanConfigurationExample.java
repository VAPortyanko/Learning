package by.pva.springio.configuration.javabasedconfiguration.annotations.bean;

import java.util.stream.Stream;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(basePackageClasses = ScanConfigurationExample.class)
public class ScanConfigurationExample {

    public static void main (String[] args) {
    	
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScanConfigurationExample.class);
        
        Stream.of(context.getBeanDefinitionNames()).forEach(System.out::println);
        
        MyBean bean = context.getBean(MyBean.class);
        System.out.println(bean);
        //accessing multiple times
        bean = context.getBean(MyBean.class);
        System.out.println(bean);

        /*this will return same instance because @Configuration classes even in scan path will be
        * CGLIB proxied*/
        TestBean1 testBean = context.getBean(TestBean1.class);
        System.out.println(testBean);
        System.out.println(testBean);
        System.out.println(testBean);
        System.out.println(testBean.myBean());
        
        ScanConfigurationExample scanbean = context.getBean(ScanConfigurationExample.class);
        System.out.println(scanbean);
    }

    @Configuration
    static class TestBean1 {
        @Bean
        public MyBean myBean () {
            return new MyBean();
        }
    }
    
    @Component
    static class TestBean2 {
        @Bean
        public MyBean myBean () {
            return new MyBean();
        }
    }
    
}

// https://www.logicbig.com/tutorials/spring-framework/spring-core/bean-definition-in-components.html
// https://www.logicbig.com/tutorials/spring-framework/spring-core/java-config.html#cgilib-config
