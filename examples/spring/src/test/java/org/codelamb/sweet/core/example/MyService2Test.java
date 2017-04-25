package org.codelamb.sweet.core.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.codelamb.sweet.core.runner.IntegrationTest;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the static inner ContextConfiguration class
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@IntegrationTest
public class MyService2Test {

    @Configuration
    static class ContextConfiguration {

        @Bean
        public MyService2 myService2() {
            return new MyService2();
        }
    }

    @Autowired
    MyService2 myService2;

    @Test
    public void testMe() {
        assertEquals(5, myService2.onePlusTwo());
        System.out.println("Done 2");
    }
}
