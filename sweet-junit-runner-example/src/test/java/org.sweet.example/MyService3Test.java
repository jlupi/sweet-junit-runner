/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class MyService3Test {

  @Configuration
  static class ContextConfiguration {

    @Bean
    public MyService3 myService3() {
      return new MyService3();
    }
  }

  @Autowired
  MyService3 myService3;

  @Test
  public void testMe() {
    assertEquals(7, myService3.onePlusThree());
    System.out.println("Done 3");
  }
}
