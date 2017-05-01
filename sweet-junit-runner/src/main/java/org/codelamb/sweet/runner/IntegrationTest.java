
package org.codelamb.sweet.runner;

import java.lang.annotation.*;

/**
 * Indicates that an annotated class is an IntegrationTest that can be loaded into a Suite.
 * <br><br>
 * Test can be excluded from the Suite when the enabledInSuite property is set to 'false'.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Inherited
public @interface IntegrationTest {
    boolean enabledInSuite() default true;
}
