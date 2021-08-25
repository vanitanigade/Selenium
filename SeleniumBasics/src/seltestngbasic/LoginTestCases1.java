package seltestngbasic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTestCases1 {

	WebDriver driver = null;

	@BeforeClass
	public void openBrowsernUrl() {
		driver = SeleniumCommonFunctions1.openBrowser("chrome");
		driver.manage().window().maximize();
		SeleniumCommonFunctions1.openUrl(driver,
				"file:///C:/Downloaded%20Programs/Selenium/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
		String actloginPageTitle = driver.getTitle();
		String exploginPageTitle = "JavaByKiran | Log in";
		Assert.assertEquals(actloginPageTitle, exploginPageTitle);
	}

	@AfterClass // ctrl + shift + o
	void closeDriver() {
		driver.close();
	}

	public void clearTextBoxes() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
	}

	@Test(priority = 1)
	public void verifyHeadingOfLoginPage() {
		String actHeading = SeleniumCommonFunctions1.getText(driver, "xpath", "/html/body/div/div[1]/a/b");
		Assert.assertEquals(actHeading, "Java By Kiran");
	}

	@Test(priority = 2)
	public void verifySubHeadingOfLoginPage() {
		String actSubHeading = SeleniumCommonFunctions1.getText(driver, "xpath", "/html/body/div/div[1]/a/h4");
		Assert.assertEquals(actSubHeading, "JAVA | SELENIUM | PYTHON");
	}

	@Test(priority = 3)
	public void verifyRegistrationLinkText() {
		String RegistrationLinkText = SeleniumCommonFunctions1.getText(driver, "className", "text-center");
		Assert.assertEquals(RegistrationLinkText, "Register a new membership");
	}

	@Test(priority = 4)
	public void clickRegistrationLink() {
		SeleniumCommonFunctions1.clickLink(driver, "linkText", "Register a new membership");
		String registrationTitle = driver.getTitle();
		Assert.assertEquals(registrationTitle, "JavaByKiran | Registration Page");
	}

	@Test(priority = 5)
	public void verifyRegistration() {
		clearTextBoxes();
		SeleniumCommonFunctions1.enterText(driver, "id", "name", "abc");
		SeleniumCommonFunctions1.enterText(driver, "id", "mobile", "1234567890");
		SeleniumCommonFunctions1.enterText(driver, "id", "email", "abc@gmail.com");
		SeleniumCommonFunctions1.enterText(driver, "id", "password", "abc");
		SeleniumCommonFunctions1.clickButton(driver, "//*[@id=\"form\"]/div[5]/div/button");
		String userRegisteredMsg = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Assert.assertEquals(userRegisteredMsg, "User registered successfully.");
	}

	@Test(priority = 6)
	public void clickAlreadyMembershipLink() {
		SeleniumCommonFunctions1.clickLink(driver, "linkText", "I already have a membership");
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "JavaByKiran | Log in");
	}

	@Test(priority = 7)
	public void verifyErrorMsgForBlankLoginEmail() {
		SeleniumCommonFunctions1.enterText(driver, "id", "email", " ");
		SeleniumCommonFunctions1.enterText(driver, "id", "password", "123456");
		SeleniumCommonFunctions1.clickButton(driver, "//*[@id=\\\"form\\\"]/div[3]/div/button");
		String blankEmailErrorMsg = SeleniumCommonFunctions1.getText(driver, "id", "email_error");
		Assert.assertEquals(blankEmailErrorMsg, "Please enter email as kiran@gmail.com");
	}

	@Test(priority = 8)
	public void verifyErrorMsgForBlankLoginPassword() {
		SeleniumCommonFunctions1.enterText(driver, "id", "email", "kiran@gmail.com");
		SeleniumCommonFunctions1.enterText(driver, "id", "password", " ");
		SeleniumCommonFunctions1.clickButton(driver, "//*[@id=\"form\"]/div[3]/div/button");
		String blankPasswordErrorMsg = SeleniumCommonFunctions1.getText(driver, "id", "password_error");
		Assert.assertEquals(blankPasswordErrorMsg, "Please enter password 123456");
	}

	@Test(priority = 9)
	public void verifyErrorMsgForInvalidLoginEmail() {
		SeleniumCommonFunctions1.enterText(driver, "id", "email", "jbk@gmail.com");
		SeleniumCommonFunctions1.enterText(driver, "id", "password", "123456");
		SeleniumCommonFunctions1.clickButton(driver, "//*[@id=\"form\"]/div[3]/div/button");
		String invalidEmailErrorMsg = SeleniumCommonFunctions1.getText(driver, "id", "email_error");
	}

	@Test(priority = 10)
	public void verifyErrorMsgForInvalidLoginPassword() {
		SeleniumCommonFunctions1.enterText(driver, "id", "email", "kiran@gmail.com");
		SeleniumCommonFunctions1.enterText(driver, "id", "password", "546987");
		SeleniumCommonFunctions1.clickButton(driver, "//*[@id=\"form\"]/div[3]/div/button");
		String invalidPasswordErrorMsg = SeleniumCommonFunctions1.getText(driver, "id", "password_error");
		Assert.assertEquals(invalidPasswordErrorMsg, "Please enter password 123456");
	}

	@Test(priority = 11)
	public void verifyLogin() {
		clearTextBoxes();
		SeleniumCommonFunctions1.enterText(driver, "id", "email", "kiran@gmail.com");
		SeleniumCommonFunctions1.enterText(driver, "id", "password", "123456");
		SeleniumCommonFunctions1.clickButton(driver, "//*[@id=\"form\"]/div[3]/div/button");

		
		
		String dashboardPageTitle = driver.getTitle();
		System.out.println(dashboardPageTitle);
		Assert.assertEquals(dashboardPageTitle, "JavaByKiran | Dashboard");
	}

	@Test(priority = 12, dependsOnMethods = "verifyLogin")
	public void addUser() {
		driver.findElement(By.xpath("/html/body/div[1]/aside/section/ul/li[3]/a/span")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div/div[1]/a/button")).click();
		SeleniumCommonFunctions1.enterText(driver, "id", "username", "kirti");
		SeleniumCommonFunctions1.enterText(driver, "id", "mobile", "1234567890");
		SeleniumCommonFunctions1.enterText(driver, "id", "email", "kirti2@gmail.com");
		SeleniumCommonFunctions1.enterText(driver, "id", "course", "Selenium");
		driver.findElement(By.id("Female")).click();
		driver.findElement(By.xpath("/html/body/div/div[1]/section[2]/div/div/div/form/div[1]/div[6]/div/select"))
				.click();
		Select select = new Select(driver
				.findElement(By.xpath("/html/body/div/div[1]/section[2]/div/div/div/form/div[1]/div[6]/div/select")));
		select.selectByVisibleText("Maharashtra");
		SeleniumCommonFunctions1.enterText(driver, "id", "password", "kirti@123");
		driver.findElement(By.xpath("/html/body/div/div[1]/section[2]/div/div/div/form/div[3]/div/input"))
				.sendKeys("1234567890");
		driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
		String userAddedMsg = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Assert.assertEquals(userAddedMsg, "User Added Successfully. You can not see added user.");
	}

	@Test(priority = 13)
	public void checkOperatorsLinkText() {
		String OperatorsLinkText = driver.findElement(By.xpath("/html/body/div/aside[1]/section/ul/li[4]/a/span"))
				.getText();
		System.out.println(OperatorsLinkText);
		Assert.assertEquals(OperatorsLinkText, "Operators");
	}

	@Test(priority = 14)
	public void checkUsefulLinksText() {
		String UsefulLinksText = driver.findElement(By.xpath("/html/body/div/aside[1]/section/ul/li[5]/a/span"))
				.getText();
		System.out.println(UsefulLinksText);
		Assert.assertEquals(UsefulLinksText, "Useful Links");
	}

	@Test(priority = 15)
	public void checkUsersLinkText() {
		String UsersLinkText = driver.findElement(By.xpath("/html/body/div[1]/aside/section/ul/li[3]/a/span"))
				.getText();
		System.out.println(UsersLinkText);
		Assert.assertEquals(UsersLinkText, "Users");
	}

}
