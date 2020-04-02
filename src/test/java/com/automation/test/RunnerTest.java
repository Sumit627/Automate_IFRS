package com.automation.test;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		
		features= {"src/test/resources/2_IFRS_Dashboard.feature"},
				 plugin = { 
						    "html:target/cucumber-html-report",
						    "json:target/cucumber.json"
						        },
				 glue ={"com/automation/test"},strict = true,
				   dryRun= false,monochrome = true, snippets= SnippetType.CAMELCASE)
		
		  
		
public class RunnerTest {
		

		
	 

}
