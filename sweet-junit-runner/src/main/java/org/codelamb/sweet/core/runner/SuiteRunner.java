
package org.codelamb.sweet.core.runner;

import java.util.Set;

import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

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
