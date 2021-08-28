package com.qa.democart.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.democart.Factory.DriverFactory;

public class ElementUtil {
	
	private WebDriver driver;// to make it specific to ElementUtil class.Encapsulation used.
	private JavaScriptUtil jsUtil;

	/**
	 * This constructor is used to initialize driver
	 * 
	 * @param driver Pass the driver
	 */
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil=new JavaScriptUtil(driver);
	}

	/**
	 * This method is used to get By locator based on locator Type and locator value provided.
	 * @param locatorType Pass the locator type to identify the element.
	 * @param locatorValue Pass the locator value to identify the element.
	 * @return This returns the By reference if the WebElement.
	 */
	public By getByLocator(String locatorType, String locatorValue) {
		By locator = null;
		switch (locatorType) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "className":
			locator = By.className(locatorValue);
			break;
		case "linkText":
			locator = By.linkText(locatorValue);
			break;
		case "partialLinkText":
			locator = By.partialLinkText(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "cssSelector":
			locator = By.cssSelector(locatorValue);
			break;
		case "tagName":
			locator = By.tagName(locatorValue);
			break;
		default:
			System.out.println("Enter correct Locator Type");
			break;
		}
		return locator;
	}
	
	/**
	 * This method returns first WebElement based on the locator provided.
	 * 
	 * @param locator Pass the locator to identify the WebElement
	 * @return This method returns the WebElement
	 */
	public WebElement getElement(By locator) {
		if(Boolean.parseBoolean(DriverFactory.highlight.trim())) {
			jsUtil.flash(driver.findElement(locator));
		}
		return driver.findElement(locator);
	}

    /**
     * This method returns first WebElement based on the locatorType and locatorValue provided.
     * @param locatorType Pass the locator type to identify the element.
     * @param locatorValue Pass the locator value to identify the element.
     * @return This method returns the WebElement
     */
	public  WebElement getElement(String locatorType, String locatorValue) {
		return driver.findElement(getByLocator(locatorType, locatorValue));
	}
	

	/**
	 * This method inputs value provided on the WebElement based on the locator after clearing any value in the element
	 * provided.
	 * 
	 * @param locator Pass the locator to identify the WebElement
	 * @param value   Pass the string to be input
	 */
	public void doSendKeys(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}

	/**
	 * This method is used to click the WebElement
	 * 
	 * @param locator Pass the locator to identify the WebElement
	 */
	public void doClick(By locator) {
		getElement(locator).click();
	}

	/**
	 * This method returns text of the WebElement.
	 * 
	 * @param locator Pass the locator to identify the WebElement
	 * @return This method returns the text of the WebElement.
	 */
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	/**
	 * This method is used to give the specific attribute value of a tag.
	 * 
	 * @param locator   Pass the locator to identify the WebElement
	 * @param attribute Pass the attribute whose value is to be fetched.
	 * @return This method returns the attribute value of the WebElement identified.
	 */
	public String doGetAttribute(By locator, String attribute) {
		return getElement(locator).getAttribute(attribute);
	}

	/**
	 * This method is used to find out if WebElement is displayed or not.
	 * 
	 * @param locator Pass the locator to identify the WebElement
	 * @return This method returns true if element is present and false if not.
	 */
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	/**
	 * This method is used to find out if WebElement is enabled or not.
	 * 
	 * @param locator Pass the locator to identify the WebElement
	 * @return This method returns true if element is enabled and false if not.
	 */
	public boolean doIsEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	/**
	 * This method is used to find out if WebElement is selected or not.
	 * 
	 * @param locator Pass the locator to identify the WebElement
	 * @return This method returns true if element is selected and false if not.
	 */
	public boolean doIsSelected(By locator) {
		return getElement(locator).isSelected();
	}

	/**
	 * This method returns all WebElements based on the locator provided.
	 * 
	 * @param locator Pass the locator to identify the WebElement
	 * @return This method returns the List of WebElements
	 */
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	/**
	 * This method is used to click WebElement having particular value.
	 * 
	 * @param locator Pass the locator to identify the WebElement
	 * @param value   Pass the value of the WebElement to be clicked.
	 */
	public void clickOnElement(By locator, String value) {
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			if (e.getText().equals(value)) {
				e.click();
			}
		}
	}

	/**
	 * This method is used to return Text of all the Link WebElements
	 * 
	 * @param locator Pass the locator to identify the WebElement
	 * @return This method returns List of String Generic containing text of all
	 *         WebElements
	 */
	public List<String> getLinksTextList(By locator) {
		List<String> eleTextList = new ArrayList<>();
		List<WebElement> eleList = getElements(locator);
		System.out.println("Element count: " + eleList.size());
		for (WebElement e : eleList) {
			String text = e.getText();
			if (!text.isEmpty()) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}

	/**
	 * This method is used to check if element is displayed on page or not/
	 * 
	 * @param locator Pass the locator to identify the WebElement
	 * @return This method returns boolean value based on element presence.
	 */
	public boolean isElementExist(By locator) {
		List<WebElement> listEle = getElements(locator);

		if (listEle.size() > 0) {
			System.out.println("Element is present");
			return true;
		}
		System.out.println("Element is not present");
		return false;
	}

	/*****************************************Select Drop Down Utilities************************************************************/

	/**
	 * This is used to select value from select tag DropDown using Visible Text
	 * 
	 * @param locator Pass the locator to identify the WebElement
	 * @param text    Pass the Visible Text to be selected.
	 */
	public void doSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	/**
	 * This is used to select value from select tag DropDown using index of the
	 * value.
	 * 
	 * @param locator Pass the locator to identify the WebElement
	 * @param index   Pass the index to be selected.
	 */
	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	/**
	 * This is used to select value from select tag DropDown using value attribute
	 * 
	 * @param locator Pass the locator to identify the WebElement
	 * @param value   Pass the value attribute value to be selected.
	 */
	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	/**
	 * This is used to get all values of a DropDown.
	 * @param locator Pass the locator to identify the DropDown WebElement
	 * @return This method returns values of all options of the DropDown.
	 */
	public List<String> getDropDownOptionsList(By locator) {
		List<String> optionsValueList = new ArrayList<>();
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();

		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsValueList.add(text);
		}

		return optionsValueList;

	}
	
	/**
	 * This method is used to select DropDown value without using selectByIndex,selectByValue or selectByVisibleText.Using getOptions method of Select class
	 * @param locator Pass the locator to identify the DropDown WebElement
	 * @param value Pass the DropDown value to be selected.
	 */
	public void selectDropDownValue(By locator,String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();

		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			if(text.equals(value)) {
				e.click();
				break;
			}
		}
	}
	
	/**
	 * This method is used to select drop down value without using Select class methods.Using FindElements method.
	 * @param locator Pass the locator to identify the DropDown WebElement
	 * @param value Pass the DropDown value to be selected.
	 */
	public void clickDropDownValue(By locator,String value) {
	    List<WebElement> optionsList=getElements(locator);
			
			System.out.println(optionsList.size());
			
			for (WebElement e : optionsList) {
				String text = e.getText();
				if(text.equals(value)) {
					e.click();
					break;
				}
			}
		}
	
	
	/*****************************************JQuery Drop Down Utilities************************************************************/
	
	
	/**
	 * This method is used for single,multiple and all values selection of DropDown.
	 * @param locator Pass the locator to identify the DropDown WebElements.
	 * @param value Pass the values to be selected. Pass "all" to select all values.
	 */
	public void selectChoice(By locator, String... value) {// ... is equivalent to array.String... represents
																	// String[]
		List<WebElement> choiceList = getElements(locator);

		if (!value[0].equalsIgnoreCase("all")) {

			for (WebElement e : choiceList) {
				String text = e.getText();
				for (int i = 0; i < value.length; i++) {
					if (text.equals(value[i])) {
						e.click();
						break;
					}
				}

			}
		}
		
		else {
			try {
			for(WebElement e:choiceList) {
				e.click();
			}
		}
			catch(Exception e) {
				
			}
	}

}
	
	
	
	/************************************User Actions Utilities**********************************************/
	
	/**
	 * This method is used to move to Element or hover over an Element.
	 * @param parentMenu Pass the parent locator to identify the WebElement
	 * @param childMenu Pass the child locator to identify the WebElement
	 * @throws InterruptedException
	 */
	public void twoLevelMenuHandle(By parentMenu,By childMenu) throws InterruptedException {
		
	Actions act=new Actions(driver);
	
	act.moveToElement(getElement(parentMenu)).perform();
	Thread.sleep(2000);
	getElement(childMenu).click();
	
	}
	
	/**
	 * This method is used to move to Element or hover over an Element.
	 * @param parentMenu1 Pass the parent level 1 locator to identify the WebElement
	 * @param parentMenu2 Pass the parent level 2 locator to identify the WebElement
	 * @param childMenu Pass the child locator to identify the WebElement
	 * @throws InterruptedException
	 */
	public void threeLevelMenuHandle(By parentMenu1,By parentMenu2,By childMenu) throws InterruptedException {
		
		Actions act=new Actions(driver);
		
		act.moveToElement(getElement(parentMenu1)).perform();
		Thread.sleep(3000);
		act.moveToElement(getElement(parentMenu2)).perform();
		Thread.sleep(3000);
		getElement(childMenu).click();
		
		}
	
	
	/**
	 * This method is used to right click a WebElement.
	 * @param locator Pass the locator to identify the WebElement to be right clicked.
	 */
	public void doRightClick(By locator) {
		Actions act = new Actions(driver);
		act.contextClick(getElement(locator)).perform();
	}
	
	/**
	 * This method is used to click on any option after doing right click on a WebElement.
	 * @param locator Pass the locator to identify all the elements of the right click.
	 * @param value Pass the option to be clicked after doing right click
	 */
	public void doRightClickOptionsClick(By locator, String value) {
		List<WebElement> rightClickOptionsList=getElements(locator);
		System.out.println(rightClickOptionsList.size());
		for(WebElement e: rightClickOptionsList) {
			String text=e.getText();
			if(text.equals(value)) {
				e.click();
				break;
			}
		}
	}
	
	/**
	 * This method inputs value provided on the WebElement based on the locator
	 * provided using Actions class.
	 * 
	 * @param locator Pass the locator to identify the WebElement
	 * @param value   Pass the string to be input
	 */
	public void doActionSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();

	}
	
	/**
	 * This method is used to click the WebElement using Actions class.
	 * 
	 * @param locator Pass the locator to identify the WebElement
	 */
	public void doActionClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();

	}
	
	/**********************************************File Upload for type=file scenarios************************************/
	
	/**
	 * This method is used to upload a file
	 * @param locator Pass the locator of the WebElement where to upload file
	 * @param path Pass the path of the file to be uploaded in your local system
	 */
	public void doFileUpload(By locator, String path) {
		doSendKeys(locator, path);
	}
	
	/**********************************************Wait Utilities***********************************************************/
	
	/**
	 * This method is used to wait till the WebElement is present inside DOM of a page and then return the WebElement.
	 * @param locator Pass the locator of the WebElement.
	 * @param timeout Pass the time duration to wait.
	 * @return This returns the WebElement.
	 */
	public WebElement waitForElementPresence(By locator,int timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	 * This method is used to wait till the WebElement is present inside DOM of a page and then return the WebElement.
	 * @param locator Pass the locator of the WebElement.
	 * @param timeout Pass the time duration to wait.
	 * @param intervalTime Pass the polling time period in milliseconds.
	 * @return This returns the WebElement.
	 */
	public WebElement waitForElementPresence(By locator,int timeout, int intervalTime)
	{
		WebDriverWait wait=new WebDriverWait(driver, timeout, intervalTime);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	 * This method is used to wait till the WebElement is present inside DOM of a page and visible and then return the WebElement.
	 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	 * @param locator Pass the locator of the WebElement.
	 * @param timeout Pass the time duration to wait.
	 * @return This returns the WebElement.
	 */
	public WebElement waitForElementVisible(By locator,int timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 * This method is used to wait till the List of WebElement is present inside DOM of a page and visible and then return the List of WebElement.
	 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	 * @param locator Pass the locator of the WebElements.
	 * @param timeout Pass the time duration to wait.
	 * @return This returns the List of WebElement.
	 */
	public List<WebElement> waitForElementsVisible(By locator,int timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	/**
	 * This method is used to return text of all the WebElements.
	 * @param locator Pass the locator of the WebElements.
	 * @param timeout Pass the time duration to wait.
	 * @return This returns the List of String.
	 */
	public List<String> getElementsTextList(By locator,int timeout)
	{
		List<WebElement> ele=waitForElementsVisible(locator, timeout);
		List<String> eleTextList=new ArrayList<String>();
		for(WebElement e: ele) {
			eleTextList.add(e.getText());
		}
		return eleTextList;
	}
	
	/**
	 * This method returns the WebElement once it is visible and enabled such that you can click it.
	 * @param locator Pass the locator of the WebElement.
	 * @param timeout Pass the time duration to wait.
	 * @return This returns the WebElement.
	 */
	public WebElement waitForElementToBeClickable(By locator, int timeout) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	/**
	 * This method clicks the WebElement once it is visible and enabled.
	 * @param locator Pass the locator of the WebElement.
	 * @param timeout Pass the time duration to wait.
	 */
	public void clickWhenReady(By locator, int timeout) {
		waitForElementPresence(locator, timeout).click();
	}
	
	/*********************************************Wait Alert Utilities*****************************************************/
	
	/**
	 * This method is used to switch to Alert once Alert present.
	 * @param timeout Pass the time duration to wait.
	 * @return This returns the ALert reference.
	 */
	public Alert waitForAlert(int timeout) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	/**
	 * This method is used to get Alert text once Alert present.
	 * @param timeout Pass the time duration to wait.
	 * @return This returns the Alert text.
	 */
	public String getAlertText(int timeout) {
		return waitForAlert(timeout).getText();
	}
	
	/**
	 * This method is used to accept Alert once Alert present.
	 * @param timeout Pass the time duration to wait.
	 */
	public void acceptAlert(int timeout) {
		waitForAlert(timeout).accept();
	}
	
	/**
	 * This method is used to dismiss Alert once Alert present.
	 * @param timeout Pass the time duration to wait.
	 */
	public void dismissAlert(int timeout) {
		waitForAlert(timeout).dismiss();
	}
	
	/**
	 * This method is used to input value once Alert present.
	 * @param timeout Pass the time duration to wait.
	 * @param value Pass the value to be input.
	 */
	public void sendKeysOnAler(int timeout, String value) {
		waitForAlert(timeout).sendKeys(value);
	}
	
	
	/************************************************Title using Wait****************************************************************/
	
	/**
	 * This method is used to return Title of the page once Title contains a value.
	 * @param titleValue Pass the value to be checked.
	 * @param timeout Pass the time duration to wait.
	 * @return This returns the Title of the page.
	 */
	public String waitForTitleContains(String titleValue,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		if(wait.until(ExpectedConditions.titleContains(titleValue))) {
			return driver.getTitle();
		}
		else {
			return null;
		}
	}
	
	/**
	 * This method is used to return Title of the page once Title value is present.
	 * @param fullTitleValue Pass the value to be checked.
	 * @param timeout Pass the time duration to wait.
	 * @return This returns the Title of the page.
	 */
	public String waitForTitleIs(String fullTitleValue,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		if(wait.until(ExpectedConditions.titleIs(fullTitleValue))) {
			return driver.getTitle();
		}
		else {
			return null;
		}
	}
	
	
	/******************************************************URL using Wait***************************************************************/
	
	/**
	 * This method is used to return URL of the page once URL contains a value.
	 * @param urlFraction Pass the URL fraction to be checked.
	 * @param timeout Pass the time duration to wait.
	 * @return This returns the URL of the page.
	 */
	public String waitForUrlContains(String urlFraction,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		if(wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		}
		else {
			return null;
		}
	}
	
	/**
	 * This method is used to return URL of the page once URL is present or loaded completely.
	 * @param url Pass the URL to be checked.
	 * @param timeout Pass the time duration to wait.
	 * @return This returns the URL of the page.
	 */
	public String waitForUrlToBe(String url,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		if(wait.until(ExpectedConditions.urlToBe(url))) {
			return driver.getCurrentUrl();
		}
		else {
			return null;
		}
	}
	
	
	/***********************************************************Frame using Wait**********************************************************/
	
	/**
	 * This method is used to switch driver control to the frame using frameName once frame is available.
	 * @param frameName Pass the frame name to which driver needs to be switched
	 * @param timeout Pass the time duration to wait for frame availability.
	 */
	public void waitForFrameAndSwitchToIt(String frameName,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}
	
	/**
	 * This method is used to switch driver control to the frame using By frame Locator once frame is available.
	 * @param frameLocator Pass the frame By locator to which driver needs to be switched.
	 * @param timeout Pass the time duration to wait for frame availability.
	 */
	public void waitForFrameAndSwitchToIt(By frameLocator,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	/**
	 * This method is used to switch driver control to the frame using frameIndex once frame is available.
	 * @param frameIndex Pass the frame index to which driver needs to be switched
	 * @param timeout Pass the time duration to wait for frame availability.
	 */
	public void waitForFrameAndSwitchToIt(int frameIndex,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}
	
	/**
	 * This method is used to switch driver control to the frame WebElement once frame is available.
	 * @param frameElement Pass the frame WebElement to which driver needs to be switched
	 * @param timeout Pass the time duration to wait for frame availability.
	 */
	public void waitForFrameAndSwitchToIt(WebElement frameElement,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	
	
	/************************************************Custom Wait function without explicit or implicit wait*************************/
	
	/**
	 * This method is used to return WebElement after waiting for certain time.
	 * @param locator Pass the locator to identify the WebElement.
	 * @param timeout Pass the time in seconds to wait.
	 * @param pollingTime Pass the wait interval time between 2 polls.
	 * @return This returns the WebElement if found within time range else null.
	 */
	public WebElement retryingElement(By locator,int timeout, int pollingTime) {
		WebElement element=null;
		int attempts=1;
		while(attempts<=timeout) {
			
			 try {
				element=driver.findElement(locator);
				break;
			} catch (Exception e) {
				try {
					Thread.sleep(pollingTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("No of attempts: "+attempts);
			}
			attempts++; 
		}
		return element;
		
	}
	
	
}


