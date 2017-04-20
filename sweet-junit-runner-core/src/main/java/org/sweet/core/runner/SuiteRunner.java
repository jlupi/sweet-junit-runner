
package org.sweet.core.runner;

import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import java.util.Set;

public class SuiteRunner extends Suite {

    public SuiteRunner(Class<?> klass, RunnerBuilder builder) throws InitializationError {
        super(builder, klass, loadClasses(klass));
    }

    private static Class<?>[] loadClasses(Class<?> klass) throws InitializationError {
        SuiteConfiguration annotation = klass.getAnnotation(SuiteConfiguration.class);
        if (annotation == null) {
            throw new InitializationError(SuiteRunner.class.getName() + " requires "
                    + SuiteConfiguration.class.getName() + " configuration annotation.");
        }

        IntegrationTestClassScanner classLoader = new IntegrationTestClassScanner(annotation.value());

        Set<Class<?>> classes = classLoader.loadClasses();
        return classes.toArray(new Class[classes.size()]);
    }
}
