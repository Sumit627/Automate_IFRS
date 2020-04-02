package com.automation.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.test.SetUp;

public class IFRS_Role_obj {

	WebDriver driver = SetUp.driver;
	
	@FindBy(xpath = "//p[contains(text(),'Select an employee to impersonate')]")
	public List<WebElement> IFRS_roleHeader_Text;
	
	@FindBy(xpath = "//p[contains(text(),'Select an employee to impersonate')]/following-sibling::div[1]/table//tr[contains(@style,'lightgreen')]")
	public WebElement Assigned_row;
	
	@FindBy(xpath = "//p[contains(text(),'Select an employee to impersonate')]/following-sibling::div[1]/table//tr[contains(@style,'lightgreen')]")
	public List<WebElement> Assigned_row_list;
	
	@FindBy(xpath = "//p[contains(text(),'Select an employee to impersonate')]/following-sibling::div[1]/table//tr[contains(@style,'lightgreen')]//td[1]")
	public WebElement Assigned_role;
	
	@FindBy(xpath = "//tr[contains(@style,'lightgreen')]//td[6]/a")
	public WebElement Unselect_role;
	
	public List<WebElement> AvailableUser_SelectHyperlink(String Col_name, String Role_Name){
	return driver.findElements(By.xpath("//p[contains(text(),'Select an employee to impersonate')]/following-sibling::div[1]/table//td[count(//th[normalize-space()='"+Col_name+"']/preceding-sibling::th)+1][not(contains(text(),'Inactive')) and contains(text(),'"+Role_Name+"')]/following-sibling::td[1]/a"));}
	
	public List<WebElement> AvailableUser_SelectHyperlink2(String Col_name, String Role_Name){
	return driver.findElements(By.xpath("//p[contains(text(),'Select an employee to impersonate')]/following-sibling::div[1]/table//td[count(//th[normalize-space()='"+Col_name+"']/preceding-sibling::th)+1][contains(text(),'"+Role_Name+"')]/following-sibling::td[count(//table//tr//th)-count(//table//tr//th[contains(text(),'"+Col_name+"')]/preceding-sibling::th)-1]/a"));}
	
	@FindBy(xpath = "//tbody//tr[contains(@style,'lightgreen')]/preceding-sibling::tr[contains(@style,'white')]")
	public List<WebElement> Role_no;
	
}
