package com.qa.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
//	private By locators:
	
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By forgotPwdLink=By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	private By header=By.cssSelector("div#logo h1 a");
	private By registerLink=By.linkText("Register");
	
//	constructor:
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
	
//	page actions/page methods/functionality/behavior of the page/no assertion:
	
	@Step("Getting Login Page Title...")
	public String getLoginPageTitle() {
		return elementUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, 5);
	}
	
	@Step("Getting Login Page Header Text...")
	public String getPageHeaderText() {
		return elementUtil.doGetText(header);
	}
	
	@Step("Checking forgot password link displayed or not...")
	public boolean isForgotPwdLinkExist() {
		return elementUtil.doIsDisplayed(forgotPwdLink);
	}
	
	@Step("Logging to application with username {0} and password {1}...")
	public AccountsPage doLogin(String un,String pwd) {
		elementUtil.doSendKeys(emailId, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	@Step("Navigating to registration Page...")
	public RegistrationsPage navigateToRegister() {
		elementUtil.doClick(registerLink);
		return new RegistrationsPage(driver);
	}
	
	

	
}
