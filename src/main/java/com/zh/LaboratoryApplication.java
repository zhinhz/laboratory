package com.zh;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class LaboratoryApplication {
    private static Logger logger = Logger.getLogger(LaboratoryApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(LaboratoryApplication.class, args);
    }

    /**
     * 如果要发布到自己的Tomcat中的时候，需要继承SpringBootServletInitializer类，并且增加如下的configure方法。
     * 如果不发布到自己的Tomcat中的时候，就无需上述的步骤
     */
//    protected SpringApplicationBuilder configure(
//            SpringApplicationBuilder application) {
//        return application.sources(LaboratoryApplication.class);
//    }


}
