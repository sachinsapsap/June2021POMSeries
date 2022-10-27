package com.qa.democart.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By accSections=By.xpath("//div[@id='content']/h2");
	private By header=By.cssSelector("div#logo h1 a");
	private By logoutLink=By.linkText("Logout");
	private By searchField=By.name("search");
	private By searchBtn=By.cssSelector("div#search button");
	

	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
	
	public String getAccPageTitle() {
		return elementUtil.waitForTitleIs(Constants.ACCOUNT_PAGE_TITLE, 5);
	}
	
	public String getAccPageUrl() {
		return elementUtil.waitForUrlContains(Constants.ACCOUNT_PAGE_URL_FRACTION, 5);
	}
	
	public String getAccPageHeader() {
		return elementUtil.doGetText(header);
	}
	
	public List<String> getAccountSectionsList() {
		List<WebElement> accSecList= elementUtil.waitForElementsVisible(accSections, 10);
		List<String> accSecValueList=new ArrayList<String>();
		for(WebElement e: accSecList) {
			accSecValueList.add(e.getText());
		}
		return accSecValueList;
	}
	
	public boolean isLogOutLinkExist() {
		return elementUtil.doIsDisplayed(logoutLink);
	}
	
	public ResultsPage doSearch(String productName) {
		System.out.println("Searching the product "+productName);
		elementUtil.doSendKeys(searchField, productName);
		elementUtil.doClick(searchBtn);
		return new ResultsPage(driver);
	}
	
}
