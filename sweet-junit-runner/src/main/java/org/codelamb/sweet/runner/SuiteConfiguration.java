package org.codelamb.sweet.runner;

import java.lang.annotation.*;

/**
 * Specifies which classes should be loaded to the Suite.
 * <br>
 * The <code>value</code> is the root package where the test classes are loaded from. It cannot be empty or null.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface SuiteConfiguration {
  /**
   * Package from which classes should be loaded.
   */
  String value() default "";

  /**
   * loads classes which cannocial-name contains containsFilter ignored if class has Annotation IntegrationTest or SuitTest
   */
  String containsFilter() default "";
}