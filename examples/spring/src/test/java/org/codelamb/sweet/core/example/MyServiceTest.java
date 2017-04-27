package org.codelamb.sweet.core.example;

import org.codelamb.sweet.core.runner.IntegrationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyService.class, MyService2.class, MyService3.class}, loader = AnnotationConfigContextLoader.class)
@IntegrationTest
public class MyServiceTest {

    @Autowired
    private MyService myService;

    @Test
    public void testMe() {
        assertEquals(2, myService.onePlusOne());
        System.out.println("Done 1");
    }
}
