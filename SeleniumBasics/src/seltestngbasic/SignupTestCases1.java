package seltestngbasic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignupTestCases1 {

	WebDriver driver = null;


	/*	@BeforeClass
	public void browserSetup() {
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
	public void openBrowsernUrl() {
		driver = SeleniumCommonFunctions1.openBrowser("chrome");
		driver.manage().window().maximize();
		SeleniumCommonFunctions1.openUrl(driver, "file:///C:/Downloaded%20Programs/Selenium/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
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
	public void verifyHeadingOfLoginPage() {
		String actHeading = driver.findElement(By.xpath("/html/body/div/div[1]/a/b")).getText();
		Assert.assertEquals(actHeading, "Java By Kiran");
	}

	@Test(priority = 2)
	public void verifyRegistrationLinkText() {
		String RegistrationLinkText = driver.findElement(By.className("text-center")).getText();
		Assert.assertEquals(RegistrationLinkText, "Register a new membership");
	}

	@Test(priority = 3)
	public void clickRegistrationLink() {
		driver.findElement(By.linkText("Register a new membership")).click();
		String registrationTitle = driver.getTitle();
		Assert.assertEquals(registrationTitle, "JavaByKiran | Registration Page");
	}

	@Test(priority = 4)
	public void verifyRegistration() {
		clearTextBoxes();
		SeleniumCommonFunctions1.enterText(driver, "id", "name", "abc");
		SeleniumCommonFunctions1.enterText(driver, "id", "mobile", "1234567890");
		SeleniumCommonFunctions1.enterText(driver, "id", "email", "abc@gmail.com");
		SeleniumCommonFunctions1.enterText(driver, "id", "password", "abc");
		driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/button")).click();
		String userRegisteredMsg = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Assert.assertEquals(userRegisteredMsg, "User registered successfully.");
	}

	@Test(priority = 5)
	public void clickAlreadyMembershipLink() {
		driver.findElement(By.linkText("I already have a membership")).click();		
		String loginPageTitle = driver.getTitle();									
		Assert.assertEquals(loginPageTitle, "JavaByKiran | Log in");
	}

}