package com.gjp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.FileInputStream;

//@EnableScheduling
@ConfigurationPropertiesScan(basePackages = {"com.gjp.*"})
@SpringBootApplication(scanBasePackages = {"com.gjp.*"})
public class TestApplication {

    public static void main(String[] args) {
        try{
//            FileInputStream fileInputStream = new FileInputStream("D:\\config.properties");
//            System.getProperties().load(fileInputStream);
            SpringApplication springApplication = new SpringApplication(TestApplication.class);
            springApplication.run(args);
        }catch (Exception e){
            e.printStackTrace();

        }

    }
}
