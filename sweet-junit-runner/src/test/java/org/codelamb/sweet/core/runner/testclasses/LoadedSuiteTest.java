
package org.codelamb.sweet.core.runner.testclasses;

import org.codelamb.sweet.core.runner.SuiteTest;
import org.junit.Test;

@SuiteTest
public class LoadedSuiteTest {

    @Test
    public void shouldLoadSuiteTest() {
        System.out.println("Class " + LoadedSuiteTest.class.getSimpleName() + " loaded");
    }

}