
package org.codelamb.sweet.core.runner;

import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import java.util.Set;

/**
 * Allows to dynamically load classes into JUnit Suite. <br><br>
 * To use it annotate the Suite with <code>@RunWith(SuiteRunner.class)</code> <br><br>
 * <code>@SuiteConfiguration</code> must be defined to tell the runner where to seach for the test classes.
 */
public class SuiteRunner extends Suite {

    public SuiteRunner(Class<?> klass, RunnerBuilder builder) throws InitializationError {
        super(builder, klass, loadClasses(klass));
    }

    private static Class<?>[] loadClasses(Class<?> klass) throws InitializationError {
        SuiteConfiguration annotation = klass.getAnnotation(SuiteConfiguration.class);
        if (annotation == null) {
            throw new InitializationError(SuiteRunner.class.getName() + " requires "
                    + SuiteConfiguration.class.getName() + " annotation.");
        }

        SuiteComponentsScanner classLoader = new SuiteComponentsScanner(annotation.value());

        Set<Class<?>> classes = classLoader.loadClasses();
        return classes.toArray(new Class[classes.size()]);
    }
}
