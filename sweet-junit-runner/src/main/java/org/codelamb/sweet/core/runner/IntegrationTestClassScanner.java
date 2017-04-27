
package org.codelamb.sweet.core.runner;

import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Set;

import org.reflections.Reflections;

public class IntegrationTestClassScanner {

    private final String packageToScan;

    public IntegrationTestClassScanner(String packageName) {
        if (packageName == null || packageName.isEmpty()) {
            throw new IllegalArgumentException("packageName must be provided");
        }

        this.packageToScan = packageName;
    }

    public Set<Class<?>> loadClasses() {
        Reflections reflections = new Reflections(packageToScan);
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(IntegrationTest.class);
        for (Iterator<Class<?>> iterator = annotatedClasses.iterator(); iterator.hasNext();) {
            Class<?> annotatedClass = iterator.next();
            IntegrationTest annotation = annotatedClass.getAnnotation(IntegrationTest.class);
            if (Modifier.isAbstract(annotatedClass.getModifiers())) {
                iterator.remove();
            } else if (!annotation.enabledInSuite()) {
                iterator.remove();
            }
        }
        return annotatedClasses;
    }

}