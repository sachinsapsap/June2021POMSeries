package com.qa.democart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ExcelUtil;

public class RegistrationsPageTest extends BaseTest {

	@BeforeClass
	public void regSetUp() {
		regPage=loginPage.navigateToRegister();
	}
	
	public String getRandomEmail() {
		Random random =new Random();
		String email="testautomation1"+random.nextInt(5000)+"@gmail.com";
		return email;
	}
	
	@DataProvider
	public Object[][] getRegData(){
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider = "getRegData")
	public void registrationTest(String firstName, String lastName, String telephone, String password, String subscribe ) {
		Assert.assertTrue(regPage.accountRegistration(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
	}
	
	
}
