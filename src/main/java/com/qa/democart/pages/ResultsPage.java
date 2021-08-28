package com.qa.democart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.democart.utils.ElementUtil;
import com.qa.democart.utils.JavaScriptUtil;

public class ResultsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	private JavaScriptUtil jsUtil;

	private By searchHeader = By.cssSelector("div#content h1");
	private By productResults=By.cssSelector("div.caption a");

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil=new JavaScriptUtil(driver);
	}
	
	public String getSearchPageHeader() {
		return elementUtil.doGetText(searchHeader);
	}
	
	public int getSearchProductListCount() {
		return elementUtil.getElements(productResults).size();
	}
	
	public ProductInfoPage selectProduct(String mainProductName) {
		jsUtil.scrollPageDown();
		List<WebElement> searchList=elementUtil.getElements(productResults);
		for(WebElement e: searchList) {
			if(e.getText().trim().equals(mainProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}
