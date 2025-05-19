package runner;
import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="CucumberFeatureFiles",glue= {"StepDefinitionFiles/StepDef.java"},
monochrome=true, plugin={"pretty","junit:target/JunitReports/report3.xml",
"json:target/JSONReports/report1.json",
"html:target/HTMLReports"},
tags="@OpenToWork" 	
		
		)
public class CucumberRunner {

}



