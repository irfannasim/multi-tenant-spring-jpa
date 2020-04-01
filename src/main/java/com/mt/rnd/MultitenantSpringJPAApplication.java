
package com.mt.rnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MultitenantSpringJPAApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultitenantSpringJPAApplication.class, args);
    }
}