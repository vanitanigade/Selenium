package seltestngbasic;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignupTestCases {

	WebDriver driver = null;


	/*	@BeforeClass
	public void browserSetup() {			//before creating openBrowser and openUrl method
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///C:/Downloaded%20Programs/Selenium/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
		driver.manage().window().maximize();
		String actloginPageTitle = driver.getTitle();
		String exploginPageTitle = "JavaByKiran | Log in";
		Assert.assertEquals(actloginPageTitle, exploginPageTitle);
	}
	 */	

	@BeforeClass
	public void openBrowsernUrl() throws IOException {
		driver = SelCommonFunctions.openBrowser(SelCommonFunctions.getConfigFileParamValue("browserName"));
		driver.manage().window().maximize();
		SelCommonFunctions.openUrl( SelCommonFunctions.getConfigFileParamValue("url"));
		String actloginPageTitle = driver.getTitle();
		String exploginPageTitle = "JavaByKiran | Log in";
		Assert.assertEquals(actloginPageTitle, exploginPageTitle);
	}		

	@AfterClass		//ctrl + shift + o
	void closeDriver() {
		driver.close();
	}

	public void clearTextBoxes() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
	}

	@Test(priority = 1)
	public void verifyHeadingOfLoginPage() throws IOException {
		// new SeleniumCommonFunctions(driver);
		String headingXpathValue = SelCommonFunctions.getXpathFileParamValue("HeadingOfLoginPagexpath");
		String actHeading = SelCommonFunctions.getText("xpath", headingXpathValue);
		Assert.assertEquals(actHeading, "Java By Kiran");
	}

	@Test(priority = 2)
	public void verifyRegistrationLinkText() {
		String RegistrationLinkText = driver.findElement(By.className("text-center")).getText();
		Assert.assertEquals(RegistrationLinkText, "Register a new membership");
	}

	@Test(priority = 3)
	public void clickRegistrationLink() {
		SelCommonFunctions.clickLink("linkText", "Register a new membership");
		String registrationTitle = driver.getTitle();
		Assert.assertEquals(registrationTitle, "JavaByKiran | Registration Page");
	}

	@Test(priority = 4)
	public void verifyRegistration() {
		clearTextBoxes();
		SelCommonFunctions.enterText("id", "name", "abc");
		SelCommonFunctions.enterText("id", "mobile", "1234567890");
		SelCommonFunctions.enterText("id", "email", "abc@gmail.com");
		SelCommonFunctions.enterText("id", "password", "abc");
		SelCommonFunctions.clickButton("tagName", "button");
		String userRegisteredMsg = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Assert.assertEquals(userRegisteredMsg, "User registered successfully.");
	}

	@Test(priority = 5)
	public void clickAlreadyMembershipLink() {
		SelCommonFunctions.clickLink("linkText", "I already have a membership");
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "JavaByKiran | Log in");
	}

}