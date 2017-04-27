
package org.codelamb.sweet.core.runner.testclasses;

import org.codelamb.sweet.core.runner.IntegrationTest;
import org.junit.Test;

@IntegrationTest
public class LoadedIntegrationTest {

    @Test
    public void shouldLoadIntegrationTest() {
        System.out.println("Class " + LoadedIntegrationTest.class.getSimpleName() + " loaded");
    }

}