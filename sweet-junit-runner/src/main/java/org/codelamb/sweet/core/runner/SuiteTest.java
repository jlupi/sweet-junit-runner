/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2017.
 */
package org.codelamb.sweet.core.runner;

import java.lang.annotation.*;

/**
 * Indicates that an annotated class is an SuiteTest that can be loaded into a Suite.
 * <br><br>
 * Test can be excluded from the Suite when the enabledInSuite property is set to 'false'.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Inherited
public @interface SuiteTest {
    boolean enabledInSuite() default true;
}
