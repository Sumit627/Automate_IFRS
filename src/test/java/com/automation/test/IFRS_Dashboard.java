package com.automation.test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.automation.pageObjects.IFRS_Home_obj;
import com.automation.utilities.ActionMethods;
import com.automation.utilities.DbConnection;
import com.automation.utilities.PropertiesFile;
import com.automation.utilities.UpdateProperties;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class IFRS_Dashboard {

	WebDriver driver = SetUp.driver;
	IFRS_Home_obj IFRS_Home_obj = SetUp.IFRS_Home_obj;
	ActionMethods user = new ActionMethods();
	static Logger Log = Logger.getLogger(IFRS_Dashboard.class);
	public static Map<String,List<String>> map=new HashMap<String,List<String>>();
	public static List<String> list = new ArrayList<String>();
	public ResultSet rs;
	public Properties data = SetUp.data;
	DbConnection Dbcon = new DbConnection();
	public ResultSetMetaData rsmetadata = null;
	public String EngNumber="", EngName="";
	public int WorkpaperCount=0;
	Scenario ScenarioN = SetUp.Sc;
	public String WorkPaperID;
	
	
	@Then("^Verify that Four status label is displayed in dashbaord$")
	public void verify_that_Four_status_label_is_displayed_in_dashbaord(DataTable arg1) throws Throwable {
	    List<List<String>> data = arg1.raw();
	    for(WebElement Status: IFRS_Home_obj.Workpaper_status_label){
	    	user.highlightElement(driver, Status);
	    	Assert.assertTrue("Label value is not correct", Status.getText().equalsIgnoreCase(data.get(1).get(0)) || Status.getText().equalsIgnoreCase(data.get(2).get(0)) || Status.getText().equalsIgnoreCase(data.get(3).get(0)) || Status.getText().equalsIgnoreCase(data.get(4).get(0)));
	    }
	    user.takeScreenshot(driver);
	}

	@Then("^Verify status based filter\"([^\"]*)\" of workpaper is displayed correctly$")
	public void verify_status_based_filter_of_workpaper_is_displayed_correctly(String arg1) throws Throwable {
	    user.highlightElement(driver, IFRS_Home_obj.Workstatus_label(arg1));
	    user.click(IFRS_Home_obj.Workstatus_label(arg1));
	    user.pageLaodWait(driver);
	    Assert.assertTrue("Row count is accurate as shown in status header", IFRS_Home_obj.Workpaper_rows.size()==Integer.parseInt(IFRS_Home_obj.Workpaper_status_label_count(arg1).getText()));
	    user.takeScreenshot(driver);
	}
	
	@Then("^Verify that following column is displayed Workpaper table$")
	public void verify_that_following_column_is_displayed_Workpaper_table(DataTable arg1) throws Throwable {
	    List<List<String>> data = arg1.raw();
	    List<String> Col_name= new ArrayList<String>();
	    for(int i=1;i<data.size();i++){
	    	Col_name.add(data.get(i).get(0));
	    	}
	    for(WebElement Table_col : IFRS_Home_obj.Workpaper_table_col_name){
	    	user.highlightElement(driver, Table_col);
	    	Assert.assertTrue("Column name is not correct/ present", Col_name.contains(Table_col.getText()));
	    	Log.info("Column name ("+Table_col.getText()+") is accurately chceked in application");
	    }
	    user.takeScreenshot(driver);
	}
	
	@Then("^Verify that Sorting functionality is working fine for \"([^\"]*)\" column$")
	public void verify_that_Sorting_functionality_is_working_fine_for_column(String arg1) throws Throwable {
		int flag1=0,flag=0;
		ArrayList<String> List1 = new ArrayList<String>();
		driver.navigate().refresh();
		user.pageLaodWait(driver);
		Thread.sleep(2000);
		for(WebElement Col : IFRS_Home_obj.Workpaper_table_col_name){
			if(Col.getText().equalsIgnoreCase(arg1)){
				user.click(Col);
				flag1=flag1+1;
				break;
				}
			}
		Assert.assertTrue("Desired column is not found", flag1==1);
		user.pageLaodWait(driver);
		for(WebElement M : IFRS_Home_obj.Workstatus_column_date(arg1)){
			List1.add(M.getText().replaceAll("%", "").replaceAll("[\\(\\)\\[\\]\\{\\}]",""));
		}
			Log.info(List1);
			Collections.sort(List1, String.CASE_INSENSITIVE_ORDER);
			if(IFRS_Home_obj.Workpaper_table_col_arrow(arg1).getAttribute("ng-reflect-ng-class").contains("sort sort-desc")){
				for(int k=0;k<IFRS_Home_obj.Workstatus_column_date(arg1).size();k++){
					Assert.assertEquals("Ascending sort is not done properly for column : "+arg1, List1.get(k), IFRS_Home_obj.Workstatus_column_date(arg1).get(k).getText().replaceAll("%", "").replaceAll("[\\(\\)\\[\\]\\{\\}]",""));
					flag=flag+1;
					if(flag==30){break;}
				}
			}
			if(IFRS_Home_obj.Workpaper_table_col_arrow(arg1).getAttribute("ng-reflect-ng-class").equalsIgnoreCase("sort sort-asc")){
				Collections.reverse(List1);
				for(int k=0;k<IFRS_Home_obj.Workstatus_column_date(arg1).size();k++){
					Assert.assertEquals("Descending sort is not done properly for column : "+arg1, List1.get(k), IFRS_Home_obj.Workstatus_column_date(arg1).get(k).getText().replaceAll("%", "").replaceAll("[\\(\\)\\[\\]\\{\\}]",""));
					flag=flag+1;
					if(flag==30){break;}
				}
			}
			if(flag==0){Assert.fail("Column is not sorted");}
			user.takeScreenshot(driver);
		}
	
	@Then("^Capture Application workpaper table$")
	public void capture_Application_workpaper_table() throws Throwable {
	for(int col=1;col<=7;col++)
	{
		List<String> colvalues = new ArrayList<String>();
		colvalues.clear();
	   for(int i=1;i<=IFRS_Home_obj.WorkpaperRows.size();i++)
	   {
		   if(col==1)
		   
			   colvalues.add(IFRS_Home_obj.Column_cell(i, col).getAttribute("title"));
		   
		   else
			   colvalues.add(IFRS_Home_obj.Column_cell(i, col).getText().replaceAll("\u2009", "").trim());
	   }
	   
	   map.put(IFRS_Home_obj.WorkpaperCols.get(col-1).getText(), colvalues);
	   System.out.println(map); 
	}
		System.out.println(map);
	    user.takeScreenshot(driver);
	}
	
	@Then("^Capture Database query result \"([^\"]*)\"$")
	public void capture_Database_query_result(String arg1) throws Throwable {
		data =PropertiesFile.readPropertiesFile();
		System.out.println(data.getProperty(arg1));
		rs = Dbcon.extractDataFromDatabase(data.getProperty(arg1));
		rs.next();
		System.out.println(rs.getString(1));
	}
	
	@Then("^Validate the result for column \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void validate_the_result_for_column(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) throws Throwable {
		Map<Integer,String> map1=new HashMap<Integer,String>();
        int count=0;
        rsmetadata = rs.getMetaData();
        System.out.println("Query result set column size : "+rsmetadata.getColumnCount());
	    while (rs.next()) {
	     	String str="";
	     	Boolean found=false;
	     	for(int c =0;c<IFRS_Dashboard.map.get(arg2).size();c++)
	     	{
	     		if(rs.getString(arg2).equals(IFRS_Dashboard.map.get(arg2).get(c)))
	     		{
	     			if(rs.getString(arg1).equals(IFRS_Dashboard.map.get(arg1).get(c)) && rs.getString(arg3).equals(IFRS_Dashboard.map.get(arg3).get(c)) && rs.getString(arg4).equals(IFRS_Dashboard.map.get(arg4).get(c)) && rs.getString(arg5).equals(IFRS_Dashboard.map.get(arg5).get(c)) && rs.getString(arg6).equals(IFRS_Dashboard.map.get(arg6).get(c)) )
	     			{
	     				found=true;
	     				break;
	     			}
	     		}
	     	}
	     	count++;
	     	if(found)
	     		str="true";
	     	else{
	     		str="false";
	     		list.add("row number "+count+" for "+arg2+" : "+rs.getString(arg2)+" is not found in application");}
	     	
	     	map1.put(count, rs.getString(arg2)+str);
	     }
	     System.out.println(map1);
	     Assert.assertFalse("mismatch found in rows "+list, list.size()>0);
		}
	
	public void validate_the_result_for_columns(String[] args) throws Throwable {
		Map<Integer,String> map1=new HashMap<Integer,String>();
        int count=0;
        rsmetadata = rs.getMetaData();
        System.out.println("Argument number :"+args.length);
        System.out.println("Query result set column size : "+rsmetadata.getColumnCount());
	    while (rs.next()) {
	     	String str="";
	     	Boolean found=false;
	     	for(int c =0;c<IFRS_Dashboard.map.get(args[0]).size();c++)
	     	{
	     		if(rs.getString(args[0]).equals(IFRS_Dashboard.map.get(args[0]).get(c)))
	     		{
	     			for(int i=1;i<args.length;i++){
	     			if(rs.getString(args[i]).equals(IFRS_Dashboard.map.get(args[i]).get(c)))
	     			{
	     				found=true;
	     				break;
	     			}}
	     		}
	     		if(found=true){break;}
	     	}
	     	count++;
	     	if(found)
	     		str="true";
	     	else{
	     		str="false";
	     		list.add("row number "+count+" for "+args[0]+" : "+rs.getString(args[0])+" is not found in application");}
	     	
	     	map1.put(count, rs.getString(args[0])+str);
	     }
	     System.out.println(map1);
	     Assert.assertFalse("mismatch found in rows "+list, list.size()>0);
		}
	
	@Then("^click on \"([^\"]*)\" button$")
	public void click_on_button(String arg1) throws Throwable {
		user.highlightElement(driver, IFRS_Home_obj.IFRS_dashboard_title);
		WorkpaperCount=Integer.parseInt(IFRS_Home_obj.IFRS_dashboard_title.getText().trim().replaceAll("[\\D]", ""));
		System.out.println("Initial WorkpaperCount : "+WorkpaperCount);
	    try{
		user.highlightElement(driver, IFRS_Home_obj.Button(arg1));
	    user.click(IFRS_Home_obj.Button(arg1));}
	    catch(Exception e){Assert.fail("desired button not found");}
	}

	@Then("^Type engagement number \"([^\"]*)\"$")
	public void type_engagement_number(String arg1) throws Throwable {
	    user.highlightElement(driver, IFRS_Home_obj.Engagement_search);
	    user.type(IFRS_Home_obj.Engagement_search, arg1);
	    EngNumber=arg1;
	}

	@Then("^verify the search result$")
	public void verify_the_search_result() throws Throwable {
	    user.pageLaodWait(driver);
	    user.sync1(driver, IFRS_Home_obj.Search_Eng_header);
	    user.sync(driver, IFRS_Home_obj.Search_Eng_header);
	    user.takeScreenshot(driver);
	    Assert.assertTrue("Desired search row not found", IFRS_Home_obj.Search_Eng_row.size()>0 && IFRS_Home_obj.Searched_result_Eng_number.getText().equalsIgnoreCase(EngNumber));
	    EngName=IFRS_Home_obj.Searched_result_Eng_name.getText().trim();
	}

	@Then("^click on the search engagement$")
	public void click_on_the_search_engagement() throws Throwable {
	    user.click(IFRS_Home_obj.Search_Eng_row.get(0));
	}

	@Then("^capture the status and the data$")
	public void capture_the_status_and_the_data() throws Throwable {
//	    user.pageLaodWait(driver);
	    user.sync_element_not_visible(driver, IFRS_Home_obj.LoadingIndicator);
	    user.highlightElement(driver, IFRS_Home_obj.Eng_name);
	    user.takeScreenshot(driver);
	    Assert.assertTrue("Desired workpaper detail page is not open", EngName.equalsIgnoreCase(IFRS_Home_obj.Eng_name.getText().trim()));
	}

	@Then("^Navigate to the dashboard$")
	public void navigate_to_the_dashboard() throws Throwable {
	    user.highlightElement(driver, IFRS_Home_obj.Home_button);
	    user.click(IFRS_Home_obj.Home_button);
	}

	@Then("^verify that Workpaper count is increased by one$")
	public void verify_that_Workpaper_count_is_increased_by_one() throws Throwable {
	    user.pageLaodWait(driver);
	    user.sync_element_not_visible(driver, IFRS_Home_obj.LoadingIndicator);
	    user.highlightElement(driver, IFRS_Home_obj.IFRS_dashboard_title);
	    Assert.assertSame("Workpaper count is not increased by 1", Integer.parseInt(IFRS_Home_obj.IFRS_dashboard_title.getText().trim().replaceAll("[\\D]", "")), WorkpaperCount+1);
	}
	
	@Then("^Navigate to \"([^\"]*)\" \"([^\"]*)\"$")
	public void navigate_to(String arg1, String arg2) throws Throwable {
	    user.pageLaodWait(driver);
	    int flag=0;
	    try{
	    	for(WebElement EngNum : IFRS_Home_obj.Workstatus_column_date(arg2)){
	    		if(EngNum.getText().equalsIgnoreCase(arg1)){
	    			user.highlightElement(driver, EngNum);
	    			user.click(EngNum);
	    			flag=flag+1;
	    			user.pageLaodWait(driver);
	    			break;
	    		}
	    	}
	    	Assert.assertTrue("Desired Engagement number is not found : "+arg1, flag==1);
	    	user.takeScreenshot(driver);
	    }
	    catch(Exception e){
	    	Log.error(e.getMessage());
	    	throw e;
	    }
	    catch(AssertionError e){
	    	ScenarioN.write("Desired Engagement number is not found : "+arg1);
	    }
	}

	@Then("^capture the Workpaper ID from pagesource$")
	public void capture_the_Workpaper_ID_from_pagesource() throws Throwable {
		user.pageLaodWait(driver);
		user.sync_element_not_visible(driver, IFRS_Home_obj.LoadingIndicator);
	    try{
	    	user.highlightElement(driver, IFRS_Home_obj.Home_button);
	    	String pagesource=driver.getPageSource();
	    	System.out.println("The current page source is "+ pagesource);
	    	WorkPaperID = pagesource.split("Do you want to remove")[1].split("#")[1].split("-")[0].trim();
//	    	System.out.println("URL: "+UR);
//	    	String[] Arry = UR.split("/");
//	    	System.out.println("UR "+UR);
//	    	System.out.println(Arry.length);
//	    	WorkPaperID=Integer.parseInt(Arry[Arry.length-1].trim());
	    	System.out.println(WorkPaperID);
	    	user.takeScreenshot(driver);
	    	UpdateProperties.updatePropertiesFile("WorkPaperID", WorkPaperID);
	    }
	    catch(Exception e){
	    	Log.error(e.getMessage());
	    	throw e;
	    }
	}
	
	
	@Then("^Update the query\"([^\"]*)\" based on captured \"([^\"]*)\" and \"([^\"]*)\"$")
	public void update_the_query_based_on_captured_and(String arg1, String arg2, String arg3) throws Throwable {
		data=PropertiesFile.readQuery();
	    String Query = data.getProperty(arg1);
	    data=PropertiesFile.readPropertiesFile();
	    Query=Query.replaceAll("XXXX", data.getProperty("Role_Select_Row")).replaceAll("YYYY", data.getProperty("WorkPaperID"));
	    UpdateProperties.updatePropertiesFile("Updated_Query", Query);
	    System.out.println(Query);
	}
	
	@Then("^Update the query\"([^\"]*)\" based on captured \"([^\"]*)\"$")
	public void update_the_query_based_on_captured(String arg1, String arg2) throws Throwable {
		data=PropertiesFile.readQuery();
	    String Query = data.getProperty(arg1);
	    data=PropertiesFile.readPropertiesFile();
	    Query=Query.replaceAll("XXXX", data.getProperty("Role_Select_Row"));
	    UpdateProperties.updatePropertiesFile("Updated_Query", Query);
	    System.out.println(Query);
	}

	@Then("^Validate the result for column \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void validate_the_result_for_column(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		String[] args = {arg1,arg2,arg3,arg4};
		validate_the_result_for_columns(args);
	}

}
