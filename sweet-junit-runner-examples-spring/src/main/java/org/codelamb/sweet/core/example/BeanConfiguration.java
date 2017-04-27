/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2017.
 */
package org.codelamb.sweet.core.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public MyService1 myService() {
        return new MyService1();
    }

    @Bean
    public MyService2 myService2() {
        return new MyService2();
    }

    @Bean
    public MyService3 myService3() {
        return new MyService3();
    }
}
