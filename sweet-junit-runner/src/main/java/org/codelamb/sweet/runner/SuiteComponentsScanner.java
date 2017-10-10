package org.codelamb.sweet.runner;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

class SuiteComponentsScanner {

  private final String packageToScan;
  private String containsFilter;

  public SuiteComponentsScanner(String packageName, String containsFilter) {
    if (packageName == null || packageName.isEmpty()) {
      throw new IllegalArgumentException("packageName must be provided");
    }

    this.packageToScan = packageName;
    this.containsFilter = containsFilter;
  }

  public Set<Class<?>> loadClasses() {
    Reflections reflections = //new Reflections(  packageToScan);
            new Reflections(
                    new ConfigurationBuilder()
                            .setUrls(ClasspathHelper.forPackage(packageToScan))
                            .setScanners(new SubTypesScanner(false), new TypeAnnotationsScanner())
                            .filterInputsBy(new FilterBuilder().includePackage(packageToScan)));

    Set<Class<?>> result = new LinkedHashSet<>();
    result.addAll(getClasses(reflections));
    return result;
  }

  private Set<Class<?>> getClasses(Reflections reflections) {
    Set<Class<?>> annotatedClasses = reflections.getSubTypesOf(Object.class);
    for (Iterator<Class<?>> iterator = annotatedClasses.iterator(); iterator.hasNext(); ) {
      Class<?> annotatedClass = iterator.next();

      if (Modifier.isAbstract(annotatedClass.getModifiers())) {
        iterator.remove();
      } else if (!matchesPatternOrIsEnabledSuite(annotatedClass)) {
        iterator.remove();
      }
    }
    return annotatedClasses;
  }

  private boolean matchesPatternOrIsEnabledSuite(Class annotatedClass) {
    return isAnnotatedAndEnabledInSuite(annotatedClass) || matchesPattern(annotatedClass);
  }

  private boolean matchesPattern(Class annotatedClass) {
    if (containsFilter.trim().equals("")) {
      return false;
    }
    if (annotatedClass.getCanonicalName().toLowerCase().contains(containsFilter)) {
      return true;
    }
    return false;
  }

  private boolean isAnnotatedAndEnabledInSuite(Class klass) {
    for (Annotation annotation : klass.getAnnotations()) {
      if (annotation instanceof IntegrationTest) {
        return ((IntegrationTest) annotation).enabledInSuite();
      }
      if (annotation instanceof SuiteTest) {
        return ((SuiteTest) annotation).enabledInSuite();
      }
    }
    return false;
  }

}