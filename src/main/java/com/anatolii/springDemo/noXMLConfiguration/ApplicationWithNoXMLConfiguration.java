package com.anatolii.springDemo.noXMLConfiguration;


import com.anatolii.springDemo.Coach;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationWithNoXMLConfiguration {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaCodeConfiguration.class);
        Coach theCoach = context.getBean("swimCoach", Coach.class);
        System.out.println(theCoach.getDailyWorkout());
        System.out.println(theCoach.getDailyFortune());
        context.close();
    }
}
