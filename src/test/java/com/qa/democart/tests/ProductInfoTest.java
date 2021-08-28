package com.qa.democart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void productInfoPageSetUp() {
		accPage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test
	public void productImagesTest() {
		resultsPage=accPage.doSearch("iMac");
		productInfoPage=resultsPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImagesCount(), 3);
	}
	
	@Test
	public void produdctInfoTest() {
		resultsPage=accPage.doSearch("Macbook");
		productInfoPage=resultsPage.selectProduct("MacBook Pro");
		Map<String,String> actProductInfoMap=productInfoPage.getProductInfo();
		softAssert.assertEquals(actProductInfoMap.get("name"),"MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Brand"),"Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"),"Product 18");
		softAssert.assertEquals(actProductInfoMap.get("Price"),"$2,000.00");
		softAssert.assertAll();
	}
}
