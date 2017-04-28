package org.codelamb.sweet.core.runner;

import org.codelamb.sweet.core.runner.testclasses.IgnoredNotAnnotatedTest;
import org.junit.Test;
import org.junit.internal.builders.JUnit4Builder;
import org.junit.runners.model.InitializationError;

import static org.junit.Assert.assertEquals;


public class SuiteRunnerTest {

    @Test(expected = InitializationError.class)
    public void shouldFailIfConfigurationIsMissing() throws Exception {
        new SuiteRunner(IgnoredNotAnnotatedTest.class, new JUnit4Builder());
    }

    @Test
    public void shouldProcessTestFiles() throws Exception {
        // when
        SuiteRunner suiteRunner = new SuiteRunner(IntegrationTestSuite.class, new JUnit4Builder());

        // then
        assertEquals(IntegrationTestSuite.class.getName(), suiteRunner.getTestClass().getName());
    }
}