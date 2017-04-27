
package org.codelamb.sweet.core.runner;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

class SuiteComponentsScanner {

    private final String packageToScan;

    public SuiteComponentsScanner(String packageName) {
        if (packageName == null || packageName.isEmpty()) {
            throw new IllegalArgumentException("packageName must be provided");
        }

        this.packageToScan = packageName;
    }

    public Set<Class<?>> loadClasses() {
        Reflections reflections = new Reflections(packageToScan);
        Set<Class<?>> result = new LinkedHashSet<>();
        result.addAll(getClasses(reflections, IntegrationTest.class));
        result.addAll(getClasses(reflections, SuiteTest.class));
        return result;
    }

    private Set<Class<?>> getClasses(Reflections reflections, Class klass) {
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(klass);
        for (Iterator<Class<?>> iterator = annotatedClasses.iterator(); iterator.hasNext();) {
            Class<?> annotatedClass = iterator.next();
            Annotation annotation =  annotatedClass.getAnnotation(klass);
            if (Modifier.isAbstract(annotatedClass.getModifiers())) {
                iterator.remove();
            } else if (!isEnabledInSuite(annotation)) {
                iterator.remove();
            }

        }
        return annotatedClasses;
    }

    private boolean isEnabledInSuite(Annotation annotation) {
        if  (annotation instanceof IntegrationTest) {
            return ((IntegrationTest) annotation).enabledInSuite();
        }
        if  (annotation instanceof SuiteTest) {
            return ((SuiteTest) annotation).enabledInSuite();
        }
        return false;
    }

}