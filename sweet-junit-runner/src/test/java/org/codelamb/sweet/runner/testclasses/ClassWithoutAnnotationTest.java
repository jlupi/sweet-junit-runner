
package org.codelamb.sweet.runner.testclasses;

import org.junit.Test;

public class ClassWithoutAnnotationTest {

    @Test
    public void shouldLoadIntegrationTest() {
        System.out.println("Class " + ClassWithoutAnnotationTest.class.getSimpleName() + " loaded");
    }

}