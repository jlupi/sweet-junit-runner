package org.codelamb.sweet.runner;

import org.codelamb.sweet.runner.testclasses.*;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Set;

import static org.junit.Assert.*;

public class SuiteComponentsScannerTest {

  private static final String PACKAGE_NAME = "org.codelamb.sweet";

  @Test
  public void loadAnnotatedClasses() {
    // given
    SuiteComponentsScanner sut = new SuiteComponentsScanner(PACKAGE_NAME, "");

    // when
    Set<Class<?>> allClasses = sut.loadClasses();

    // then
    assertTrue(allClasses.contains(LoadedIntegrationTest.class));
    assertTrue(allClasses.contains(LoadedSuiteTest.class));
    assertFalse(allClasses.contains(IgnoredNotAnnotatedTest.class));
    assertFalse(allClasses.contains(IgnoredDisabledTest.class));
    assertFalse(allClasses.contains(ClassWithoutAnnotationTest.class));
    assertFalse(allClasses.contains(IgnoredAbstractTest.class));

    for (Class<?> allClass : allClasses) {
      Annotation annotation = allClass.getAnnotation(IntegrationTest.class);
      if (annotation == null) {
        annotation = allClass.getAnnotation(SuiteTest.class);
      }
      assertNotNull("Didn't find required annotation on class " + allClass.getSimpleName(), annotation);
    }
  }

  @Test
  public void loadAnnotatedClassesWithContainsFilter() {
    // given
    SuiteComponentsScanner sut = new SuiteComponentsScanner(PACKAGE_NAME, "without");

    // when
    Set<Class<?>> allClasses = sut.loadClasses();

    // then
    assertTrue(allClasses.contains(LoadedIntegrationTest.class));
    assertTrue(allClasses.contains(LoadedSuiteTest.class));
    assertTrue(allClasses.contains(ClassWithoutAnnotationTest.class));
    assertFalse(allClasses.contains(IgnoredNotAnnotatedTest.class));
    assertFalse(allClasses.contains(IgnoredDisabledTest.class));
    assertFalse(allClasses.contains(IgnoredAbstractTest.class));

  }

  @Test
  public void loadAnnotatedClassesWithContainsFilter2() {
    // given
    SuiteComponentsScanner sut = new SuiteComponentsScanner(PACKAGE_NAME, "hkjdahjkdashjkhjkdas");

    // when
    Set<Class<?>> allClasses = sut.loadClasses();

    // then
    assertTrue(allClasses.contains(LoadedIntegrationTest.class));
    assertTrue(allClasses.contains(LoadedSuiteTest.class));
    assertFalse(allClasses.contains(IgnoredNotAnnotatedTest.class));
    assertFalse(allClasses.contains(IgnoredDisabledTest.class));
    assertFalse(allClasses.contains(ClassWithoutAnnotationTest.class));
    assertFalse(allClasses.contains(IgnoredAbstractTest.class));

    for (Class<?> allClass : allClasses) {
      Annotation annotation = allClass.getAnnotation(IntegrationTest.class);
      if (annotation == null) {
        annotation = allClass.getAnnotation(SuiteTest.class);
      }
      assertNotNull("Didn't find required annotation on class " + allClass.getSimpleName(), annotation);
    }
  }


  @Test(expected = IllegalArgumentException.class)
  public void throwsExceptionWhenModuleNameNotProvided() {
    new SuiteComponentsScanner(null, null);
  }
}