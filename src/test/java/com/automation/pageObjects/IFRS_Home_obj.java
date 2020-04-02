package com.automation.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.test.SetUp;

public class IFRS_Home_obj {

	WebDriver driver = SetUp.driver;
	
	
	@FindBy(xpath="//span[@class='title']")
	public WebElement IFRS_dashboard_title;
	
	@FindBy(xpath = "//h5[contains(text(),'Workpaper Status')]")
	public List<WebElement> IFRS_Dashboard_Text;
	
	@FindBy(xpath = "//*[normalize-space()='Workpaper Status']/../following-sibling::li//span[@class='filter-label']")
	public List<WebElement> Workpaper_status_label;
	
	public WebElement Workpaper_status_label_count(String status_name){
	WebElement label = driver.findElement(By.xpath("//span[contains(text(),'"+status_name+"')]/following-sibling::span[1]"));
	return label;}
	
	public WebElement Workstatus_label(String status_name){
		WebElement label = driver.findElement(By.xpath("//span[@class='filter-label' and normalize-space()='"+status_name+"']/.."));
		return label;}
	
	
	@FindBy(xpath = "//table[@class='workpaper-table']//tbody//tr")
	public List<WebElement> Workpaper_rows;
	
	@FindBy(xpath = "//table[@class='workpaper-table']//thead//th[not(contains(@class,'non-sortable'))]")
	public List<WebElement> Workpaper_table_col_name;
	
	public List<WebElement> Workstatus_column_date(String Col_name){
		List<WebElement> label = driver.findElements(By.xpath("//table[@class='workpaper-table']//tbody//tr//td[count(//table[@class='workpaper-table']//thead//th[normalize-space()='"+Col_name+"']/preceding-sibling::th)+1]"));
		return label;}
	
	public WebElement Workpaper_table_col_arrow(String col_name){
		WebElement label = driver.findElement(By.xpath("//table[@class='workpaper-table']//thead//th[normalize-space()='"+col_name+"']/span"));
		return label;}
	
	@FindBy(xpath = "//tbody//tr")
	public List<WebElement> WorkpaperRows;
	
	@FindBy(xpath = "//th")
	public List<WebElement> WorkpaperCols;
	
	
	
	public String ColumnContent(String col_number){
		return driver.findElement(By.xpath("tbody//tr/td["+col_number+"]")).getText();
		}
	
	public WebElement Column_cell(int row_num, int col_num){
		return driver.findElement(By.xpath("//tbody/tr["+row_num+"]/td["+col_num+"]"));	
	}
	
	public WebElement Button(String button_name){
		return driver.findElement(By.xpath("//button[normalize-space()='"+button_name+"']"));	
	}
	@FindBy(id="engagementNumber")
	public WebElement Engagement_search;

	@FindBy(xpath = "//div[@class='typeahead-row']")
	public List<WebElement> Search_Eng_row;
	
	@FindBy(xpath = "//div[@class='typeahead-header']")
	public WebElement Search_Eng_header;
	
	
	@FindBy(xpath = "//div[@class='typeahead-row']//span[1]")
	public WebElement Searched_result_Eng_number;
	
	@FindBy(xpath = "//div[@class='typeahead-row']//span[2]")
	public WebElement Searched_result_Eng_name;
	
	@FindBy(xpath="//div[@class='loading-indicator']")
	public List<WebElement> LoadingIndicator;
	
	@FindBy(xpath="//div[@class='company-info']//span[@class='engagement-info-value']")
	public WebElement Eng_name;
	
	@FindBy(xpath="//li[@class='home-link']//i")
	public WebElement Home_button;
	
	@FindBy(xpath="//div[@class='modal-body']//p[contains(normalize-space(),'Do you want to remove Active AuditPartner from Workpaper:')]")
	public WebElement RawText_WorkID;
	
	
}
