package com.restapi.automation.test.context;

import java.util.HashMap;
import java.util.Map;

import static java.lang.ThreadLocal.withInitial;

public class TestContext {
    private static final String SCENARIO_CONTEXT = "SCENARIO_CONTEXT";

    private static TestContext testContext;

    private final ThreadLocal<Map<String, Object>> threadLocal;

    private TestContext()
    {
        threadLocal = withInitial(HashMap::new);
        initTestContext();
    }

    private void initTestContext() {
        initScenarioContext();
    }

    private void initScenarioContext() {
        threadLocal.get().put(SCENARIO_CONTEXT, new ScenarioContext());
    }

    public static TestContext getTestContext() {
        if (testContext == null) {
            testContext = new TestContext();
        }
        return testContext;
    }

    public ScenarioContext getScenarioContext() {
        return (ScenarioContext) threadLocal.get().get(SCENARIO_CONTEXT);
    }

    public void reset() {
        threadLocal.get().clear();

        initTestContext();
    }

}