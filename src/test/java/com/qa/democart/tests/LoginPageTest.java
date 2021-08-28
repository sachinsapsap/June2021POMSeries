package com.qa.democart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.democart.utils.Constants;
import com.qa.democart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC 100: Design Login Page")
@Story("US 101:Login Page with different features")
public class LoginPageTest extends BaseTest {

	@Description("This is my Login Page Title Test....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void loginPageTitleTest() {
		String title=loginPage.getLoginPageTitle();
		System.out.println("Login Page Title is "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, Errors.TITLE_ERROR_MSG);
	}
	
	@Description("This is my Login Page Header Test....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void loginPageHeaderTest() {
		String header=loginPage.getPageHeaderText();
		System.out.println("Login Page Title is "+header);
		Assert.assertEquals(header, Constants.PAGE_HEADER, Errors.HEADER_ERROR_MSG);
	}
	
	@Description("This is forgot password Link Test....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("This is my Login Page Test....")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=4)
	public void loginTest() {
		accPage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogOutLinkExist());
	}
	
	
}
