package com.automation.test;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.pageObjects.IFRS_Home_obj;
import com.automation.pageObjects.IFRS_Role_obj;
import com.automation.utilities.ActionMethods;
import com.automation.utilities.UpdateProperties;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class IFRS_Role_Screen {

	WebDriver driver = SetUp.driver;
	ActionMethods user = new ActionMethods();
	Properties data = SetUp.data;
	static Logger Log = Logger.getLogger(IFRS_Role_Screen.class);
	IFRS_Role_obj IFRS_Role_Page_obj = SetUp.IFRS_Role_obj;
	IFRS_Home_obj IFRS_Home_Page_obj = SetUp.IFRS_Home_obj;
	public String Role_no,userRole ;
	
	
	
	@Given("^User is navigate to \"([^\"]*)\" screen of IFRS$")
	public void user_is_navigate_to_screen_of_IFRS(String arg1) throws Throwable {
		String Url = data.getProperty(arg1);
	    System.out.println(Url);
	    driver.navigate().to(Url);
	    user.pageLaodWait(driver);
	    Thread.sleep(4000);
	    if(user.isAlertPresent(driver)){
	    	driver.switchTo().alert().accept();
	    }
	    if(IFRS_Role_Page_obj.IFRS_roleHeader_Text.size()>0 || IFRS_Home_Page_obj.IFRS_Dashboard_Text.size()>0){
	    	Log.info("Screen is displayed successfully");
	    	user.takeScreenshot(driver);
	    }
	    else{
	    	Assert.fail("Screen is not displayed or loaded properly");
	    }
	}

	@Then("^Assign \"([^\"]*)\" role for unassigned user$")
	public void assign_role_for_unassigned_user(String arg1) throws Throwable {
		user.pageLaodWait(driver);
//		String arg2="";
//		if(arg1.equalsIgnoreCase("Partner")){arg2="Buppp";}else{arg2=arg1;}
	    if(IFRS_Role_Page_obj.Assigned_row_list.size()>0){
	    	if(IFRS_Role_Page_obj.Assigned_role.getText().contains(arg1)){
	    		Log.info("User is already assigned to "+arg1+" role");
	    		user.scrollToElement(driver, IFRS_Role_Page_obj.Assigned_role);
	    		user.highlightElement(driver, IFRS_Role_Page_obj.Assigned_role);
	    	}
	    	else{
	    		user.scrollToElement(driver, IFRS_Role_Page_obj.Unselect_role);
	    		user.highlightElement(driver, IFRS_Role_Page_obj.Assigned_role);
	    		user.highlightElement(driver, IFRS_Role_Page_obj.Unselect_role);
	    		user.click(IFRS_Role_Page_obj.Unselect_role);
//	    		user.pageLaodWait(driver);
	    		Thread.sleep(1000);
	    		if(IFRS_Role_Page_obj.AvailableUser_SelectHyperlink("Login", arg1.toLowerCase()).size()>0){
		    		for(WebElement X : IFRS_Role_Page_obj.AvailableUser_SelectHyperlink("Login", arg1.toLowerCase())){
		    			user.scrollToElement(driver, X);
		    			user.highlightElement(driver, X);
		    			user.click(X);break;
		    		}
	    		}
	    		else{
	    			for(WebElement X : IFRS_Role_Page_obj.AvailableUser_SelectHyperlink2("Name", arg1)){
		    			user.scrollToElement(driver, X);
		    			user.highlightElement(driver, X);
		    			user.click(X);break;
		    		}
	    		}
		    	driver.navigate().refresh();
		    	user.pageLaodWait(driver);
		    	user.sync(driver, IFRS_Role_Page_obj.Assigned_row);
		    	if(IFRS_Role_Page_obj.Assigned_role.getText().contains(arg1)){
		    		Log.info("User is already assigned to "+arg1+" role");
		    		user.scrollToElement(driver, IFRS_Role_Page_obj.Assigned_role);
		    		user.highlightElement(driver, IFRS_Role_Page_obj.Assigned_role);
		    	}
		    	else{
		    		Assert.fail("Desired role is not assigned");
		    	}
	    	}
	    	
	    }
	    else{
	    	if(IFRS_Role_Page_obj.AvailableUser_SelectHyperlink("Login", arg1.toLowerCase()).size()>0){
	    		for(WebElement X : IFRS_Role_Page_obj.AvailableUser_SelectHyperlink("Login", arg1.toLowerCase())){
	    			user.scrollToElement(driver, X);
	    			user.highlightElement(driver, X);
	    			user.click(X);break;
	    		}
    		}
    		else{
    			for(WebElement X : IFRS_Role_Page_obj.AvailableUser_SelectHyperlink2("Name", arg1)){
	    			user.scrollToElement(driver, X);
	    			user.highlightElement(driver, X);
	    			user.click(X);break;
	    		}
    		}
	    	driver.navigate().refresh();
	    	user.sync(driver, IFRS_Role_Page_obj.Assigned_row);
	    	if(IFRS_Role_Page_obj.Assigned_role.getText().contains(arg1)){
	    		Log.info("User is already assigned to "+arg1+" role");
	    		user.scrollToElement(driver, IFRS_Role_Page_obj.Assigned_role);
	    		user.highlightElement(driver, IFRS_Role_Page_obj.Assigned_role);
	    	}
	    	else{
	    		Assert.fail("Desired role is not assigned");
	    	}
	    }
	    user.takeScreenshot(driver);
	}
	
	@Then("^Assign \"([^\"]*)\" role for assigned user$")
	public void assign_role_for_assigned_user(String arg1) throws Throwable {

			
			user.pageLaodWait(driver);
			Thread.sleep(1000);
			userRole=arg1;
			try{
		    if(IFRS_Role_Page_obj.Assigned_row_list.size()>0){		//Assigned Row available or not
		    	if(IFRS_Role_Page_obj.Assigned_role.getText().toLowerCase().contains(arg1.toLowerCase())){ //Same role is already assgned
		    		Log.info("User is already assigned to "+arg1+" role");
		    		user.scrollToElement(driver, IFRS_Role_Page_obj.Assigned_role);
		    		user.highlightElement(driver, IFRS_Role_Page_obj.Assigned_role);
		    	}
		    	else{																//Unselcting current role  and then Selecting desired role
		    		
		    	
		    	//	user.scrollToElement(driver, IFRS_Role_Page_obj.Unselect_role);
//		    		user.highlightElement(driver, IFRS_Role_Page_obj.Assigned_role);
		    		/*user.highlightElement(driver, IFRS_Role_Page_obj.Unselect_role);
		    		user.click(IFRS_Role_Page_obj.Unselect_role);*/
//		    		driver.navigate().refresh();
//		    		Thread.sleep(500);
//		    		user.pageLaodWait(driver);
//		    		Thread.sleep(1000);
		    		for(WebElement X : IFRS_Role_Page_obj.AvailableUser_SelectHyperlink2("Name", arg1)){
		    			user.scrollToElement(driver, X);
		    			user.highlightElement(driver, X);
		    			user.waiting(1000);
		    			user.pageLaodWait(driver);
		    			System.out.println("Role is clicked : "+X.getText());
		    			user.click(X);break;
		    		}
		    		System.out.println("Unselcting current role  and then Selecting desired role"+arg1);
			    	driver.get(data.getProperty("Role_Select"));
			    	Thread.sleep(500);
			    	user.pageLaodWait(driver);
			    	System.out.println("Role is refreshed");
			    	user.sync(driver, IFRS_Role_Page_obj.Assigned_row);
			    	user.waiting(500);	
			    	if(IFRS_Role_Page_obj.Assigned_role.getText().toLowerCase().contains(arg1.toLowerCase())){
				    		Log.info("User is already assigned to "+arg1+" role");
				    		user.scrollToElement(driver, IFRS_Role_Page_obj.Assigned_role);
				    		user.highlightElement(driver, IFRS_Role_Page_obj.Assigned_role);
				    	}
				    	else{
				    		Assert.fail("Desired role is not assigned");
				    	}
						
			    	
		    	}
		    	
		    }
		    else{
		    	for(WebElement X : IFRS_Role_Page_obj.AvailableUser_SelectHyperlink2("Name", arg1)){
	    			user.scrollToElement(driver, X);
	    			user.highlightElement(driver, X);
	    			user.waiting(1000);
	    			user.pageLaodWait(driver);
	    			user.click(X);break;
	    		}
		    	
		    	driver.get(data.getProperty("Role_Select"));
		    	Thread.sleep(500);
		    	user.pageLaodWait(driver);
		    	user.sync(driver, IFRS_Role_Page_obj.Assigned_row);
		    	user.waiting(1000);
		    	if(IFRS_Role_Page_obj.Assigned_role.getText().toLowerCase().contains(arg1.toLowerCase())){
		    		Log.info("User is already assigned to "+arg1+" role");
		    		user.scrollToElement(driver, IFRS_Role_Page_obj.Assigned_role);
		    		user.highlightElement(driver, IFRS_Role_Page_obj.Assigned_role);
		    	}
		    	else{
		    		Assert.fail("Desired role is not assigned");
		    	}
		    }
		    user.takeScreenshot(driver);
		
		
		}catch (Exception e){
			Log.error(e.getMessage());
			throw e;
		}
		
		catch(AssertionError e){
			driver.get(data.getProperty("Role_Select"));
			user.waiting(1000);
			user.pageLaodWait(driver);
			
			if(!IFRS_Role_Page_obj.Assigned_role.getText().toLowerCase().contains(arg1.toLowerCase()))
			{
				for(WebElement X : IFRS_Role_Page_obj.AvailableUser_SelectHyperlink2("Name", arg1)){
	    			user.scrollToElement(driver, X);
	    			user.highlightElement(driver, X);
	    			user.waiting(1000);
	    			user.pageLaodWait(driver);
	    			
	    			System.out.println(" In Catch Role is clicked : "+X.getText());
	    			user.jsClick(driver,X);break;
	    		
	    		}
				user.waiting(500);
				user.pageLaodWait(driver);
			}
			
			if(!IFRS_Role_Page_obj.Assigned_role.getText().toLowerCase().contains(arg1.toLowerCase()))
			{
				Assert.fail("Desired role is not assigned");
			}	
		}

	    Role_no=String.valueOf(IFRS_Role_Page_obj.Role_no.size()+1);
	    UpdateProperties.updatePropertiesFile("Role_Select_Row", Role_no);
	    System.out.println("Assigned role row number "+Role_no);
	    user.takeScreenshot(driver);
	}

}
