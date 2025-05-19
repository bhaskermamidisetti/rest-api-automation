package com.restapi.automation.test.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","json:target/cucumber-reports/reports.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-reports/reports2.html"},
        glue = {"com.restapi.automation.test.stepDefinitions"},
        features = {"src/test/resources/features"},
        tags = "@restapi",
        monochrome = true
)
public class TestRunner {
}