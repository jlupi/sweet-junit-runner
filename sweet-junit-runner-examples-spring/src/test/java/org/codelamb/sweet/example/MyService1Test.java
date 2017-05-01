package org.codelamb.sweet.example;

import static org.junit.Assert.assertEquals;

import org.codelamb.sweet.runner.IntegrationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyService1.class, MyService2.class, MyService3.class}, loader = AnnotationConfigContextLoader.class)
@IntegrationTest
public class MyService1Test {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MyService1 myService1;

    @Test
    public void testMe() {
        assertEquals(2, myService1.onePlusOne());
        System.out.println(this.getClass().getSimpleName() + " -> " + applicationContext.getId());
        assertEquals(3, applicationContext.getBeansWithAnnotation(Service.class).size());
    }
}
