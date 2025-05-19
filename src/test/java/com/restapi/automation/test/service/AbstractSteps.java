package com.restapi.automation.test.service;

import com.restapi.automation.test.context.ScenarioContext;
import com.restapi.automation.test.context.TestContext;


public class AbstractSteps {

    private TestContext testContext;
    private ScenarioContext scenarioContext;

    public void beforeEachScenario() {
        testContext = TestContext.getTestContext();
        scenarioContext = testContext.getScenarioContext();
    }

    public void afterEachScenario(){
        testContext.reset();
    }

    public TestContext getTestContext() {
        return testContext;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}