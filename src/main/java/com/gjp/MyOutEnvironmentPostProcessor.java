package com.gjp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class MyOutEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        environment.getPropertySources().addLast(getMyProperties());
    }

    private PropertySource<?> getMyProperties() {
        PropertiesPropertySource propertiesPropertySource = null;
        try{
            FileInputStream fileInputStream = new FileInputStream("D:\\config.properties");
            Properties properties = new Properties();
            properties.load(fileInputStream);

            propertiesPropertySource = new PropertiesPropertySource("my", properties);
        }catch (Exception e){
            e.printStackTrace();
        }

        return propertiesPropertySource;
    }
}
