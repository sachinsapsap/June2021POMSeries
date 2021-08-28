package com.qa.democart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.democart.listeners.TestAllureListener;
import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC 101: Design Accounts page for open cart application....")
@Story("US 102: Accounts page with differnet features.....")
@Listeners(TestAllureListener.class)
public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetUp() {
		accPage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Description("accPageTitleTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void accPageTitleTest() {
		String title=accPage.getAccPageTitle();
		System.out.println(title);
		Assert.assertEquals(title,Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Description("accPageHeaderTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void accPageHeaderTest() {
		String header=accPage.getAccPageHeader();
		System.out.println(header);
		Assert.assertEquals(header,Constants.PAGE_HEADER);
	}
	
	@Description("accSectionsListTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3)
	public void accSectionsListTest() {
		List<String> accAccSecList=accPage.getAccountSectionsList();
		System.out.println("Actual Account Sections List "+accAccSecList);
		Assert.assertEquals(accAccSecList, Constants.EXPECTED_ACC_SEC_LIST);
	}
	
	@Description("logoutLinkExistTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	public void logoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogOutLinkExist());
	}
	
	@DataProvider
	public Object[][] getSearchData() {
//		return new Object[][] {{"Macbook Pro"},{"Macbook Air"},{"Apple"}};
		return ExcelUtil.getTestData(Constants.SEARCH_SHEET_NAME);	
	}
	
	@Description("searchTest with {0}")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5, dataProvider = "getSearchData")
	public void searchTest(String productName){
		resultsPage=accPage.doSearch(productName);
		String resultsHeader=resultsPage.getSearchPageHeader();
		System.out.println(resultsHeader);
		Assert.assertTrue(resultsHeader.contains(productName));
	}
	
	@DataProvider
	public Object[][] getProductSelectData(){
//		return new Object[][] {{"Macbook","MacBook Air"},{"Macbook","MacBook Pro"},{"Apple","Apple Cinema 30\""}};
		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
	}
	
	@Description("selectProductTest with product name: {0} and mainProductName: {1}")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 6,dataProvider ="getProductSelectData")
	public void selectProductTest(String productName,String mainProductName){
		resultsPage=accPage.doSearch(productName);
		productInfoPage=resultsPage.selectProduct(mainProductName);
		String header=productInfoPage.getProductHeaderText();
		System.out.println("The product header is "+header);
		Assert.assertEquals(header, mainProductName);
		
	}
	
	

}
