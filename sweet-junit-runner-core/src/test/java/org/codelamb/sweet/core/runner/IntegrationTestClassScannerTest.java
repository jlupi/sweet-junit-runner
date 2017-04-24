
package org.codelamb.sweet.core.runner;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class IntegrationTestClassScannerTest {
    private static final String PACKAGE_NAME = "org.codelamb.sweet.core";

    @Test
    public void loadAnnotatedClasses() {
        // given
        IntegrationTestClassScanner sut = new IntegrationTestClassScanner(PACKAGE_NAME);

        // when
        Set<Class<?>> allClasses = sut.loadClasses();

        // then
        assertTrue(allClasses.contains(ClassToLoadTest.class));
        assertFalse(allClasses.contains(ClassToIgnoreTest.class));

        for (Class<?> allClass : allClasses) {
            IntegrationTest isAnnotated = allClass.getAnnotation(IntegrationTest.class);
            assertNotNull(isAnnotated);
        }
    }


    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenModuleNameNotProvided() {
        new IntegrationTestClassScanner(null);
    }
}