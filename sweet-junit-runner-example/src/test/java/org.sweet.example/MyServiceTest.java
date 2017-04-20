package org.sweet.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.sweet.core.runner.IntegrationTest;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the static inner ContextConfiguration class
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@IntegrationTest
public class MyServiceTest {

    @Configuration
    static class ContextConfiguration {

        @Bean
        public MyService myService() {
            return new MyService();
        }
    }

    @Autowired
    MyService myService;

    @Test
    public void testMe() {
        assertEquals(3, myService.onePlusOne());
        System.out.println("Done 1");
    }
}
