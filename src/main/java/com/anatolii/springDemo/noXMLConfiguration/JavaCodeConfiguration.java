package com.anatolii.springDemo.noXMLConfiguration;

import com.anatolii.springDemo.Coach;
import com.anatolii.springDemo.FortuneService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:someURL.properties")
public class JavaCodeConfiguration {

    @Bean
    public FortuneService theDBFortuneService(){
        return new DBFortuneService();
    }
    @Bean
    public Coach swimCoach(){
        SwimCoach mySwimCoach = new SwimCoach(theDBFortuneService());
        return mySwimCoach;
    }

}
