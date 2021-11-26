package by.pva.springio.configuration.javabasedconfiguration.annotations.bean;

import java.util.stream.Stream;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WithoutCGLIB {
	
  private int counter;
  
  @Bean
  public String something(){
      System.out.println("method invoked");
      return String.valueOf(++counter);
  }

      public static void main(String... strings) {
          AnnotationConfigApplicationContext context =
                  new AnnotationConfigApplicationContext(WithoutCGLIB.class);
          
          Stream.of(context.getBeanDefinitionNames()).forEach(System.out::println);
          
          String beans = (String) context.getBean("something");
          System.out.println(beans);
          
          WithoutCGLIB bean = context.getBean(WithoutCGLIB.class);
          System.out.println(bean.something());
          System.out.println(bean.something());
      }
}
