package seltestngbasic;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AllTestCases {

	WebDriver driver = null;

	/*
	 * @BeforeClass public void setUpEnvironment() { SeleniumCommonFunctions
	 * seleniumCommonFunctions = new SeleniumCommonFunctions(driver); } // OR new
	 * SeleniumCommonFunctions(driver);
	 */

	@BeforeClass
	public void openBrowsernUrl() throws IOException {
		driver = SelCommonFunctions.openBrowser(SelCommonFunctions.getConfigFileParamValue("browserName"));
		driver.manage().window().maximize();
		SelCommonFunctions.openUrl(SelCommonFunctions.getConfigFileParamValue("url"));
	}

	@AfterClass // control + shift + o
	void closeDriver() {
		driver.close();
	}

	@Test(priority = 1)
	public void verifyHeadingOfLoginPage() throws IOException {
		// new SeleniumCommonFunctions(driver);
		String headingXpathValue = SelCommonFunctions.getXpathFileParamValue("HeadingOfLoginPagexpath");
		String actHeading = SelCommonFunctions.getText("xpath", headingXpathValue);
		Assert.assertEquals(actHeading, "Java By Kiran");
	}

	@Test(priority = 2)
	public void verifySubHeadingOfLoginPage() throws IOException {
		String subHeadingXpathValue = SelCommonFunctions.getXpathFileParamValue("SubHeadingOfLoginPagexpath");
		String actSubHeading = SelCommonFunctions.getText("xpath", subHeadingXpathValue);
		Assert.assertEquals(actSubHeading, "JAVA | SELENIUM | PYTHON");
	}

	@Test(priority = 3)
	public void verifyRegistrationLinkText() {
		String RegistrationLinkText = driver.findElement(By.className("text-center")).getText();
		Assert.assertEquals(RegistrationLinkText, "Register a new membership");
	}

	@Test(priority = 4)
	public void clickRegistrationLink() {
		SelCommonFunctions.clickLink("linkText", "Register a new membership");
		String registrationTitle = driver.getTitle();
		Assert.assertEquals(registrationTitle, "JavaByKiran | Registration Page");
	}

	@Test(priority = 5)
	public void verifyRegistration() {
		SelCommonFunctions.enterText("id", "name", "abc");
		SelCommonFunctions.enterText("id", "mobile", "1234567890");
		SelCommonFunctions.enterText("id", "email", "abc@gmail.com");
		SelCommonFunctions.enterText("id", "password", "abc");
		SelCommonFunctions.clickButton("tagName", "button");
		String userRegisteredMsg = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Assert.assertEquals(userRegisteredMsg, "User registered successfully.");
	}

	@Test(priority = 6)
	public void clickAlreadyMembershipLink() {
		SelCommonFunctions.clickLink("linkText", "I already have a membership");
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "JavaByKiran | Log in");
	}

	@Test(priority = 7)
	public void verifyEmailPlaceholderValue() {
		String emailPlaceholderValue = SelCommonFunctions.getAttributeValue("id", "email", "placeholder");
		Assert.assertEquals(emailPlaceholderValue, "Email.");
	}

	@Test(priority = 8)
	public void verifyPasswordPlaceholderValue() {
		String passwordPlaceholderValue = SelCommonFunctions.getAttributeValue("id", "password", "placeholder");
		Assert.assertEquals(passwordPlaceholderValue, "Password");
	}

	@Test(priority = 9)
	public void verifyErrorMsgForBlankLoginEmail() {
		SelCommonFunctions.enterText("id", "email", " ");
		SelCommonFunctions.enterText("id", "password", "123456");
		SelCommonFunctions.clickButton("tagName", "button");
		SelCommonFunctions.getScreenshots();
		String blankEmailErrorMsg = SelCommonFunctions.getText("id", "email_error");
		Assert.assertEquals(blankEmailErrorMsg, "Please enter email as kiran@gmail.com");
	}

	@Test(priority = 10)
	public void verifyErrorMsgForBlankLoginPassword() {
		SelCommonFunctions.clearTextBoxes("id", "email");
		SelCommonFunctions.clearTextBoxes("id", "password");
		SelCommonFunctions.enterText("id", "email", "kiran@gmail.com");
		SelCommonFunctions.enterText("id", "password", " ");
		SelCommonFunctions.clickButton("tagName", "button");
		String blankPasswordErrorMsg = SelCommonFunctions.getText("id", "password_error");
		Assert.assertEquals(blankPasswordErrorMsg, "Please enter password 123456");
	}

	@Test(priority = 11)
	public void verifyErrorMsgForInvalidLoginEmail() {
		SelCommonFunctions.clearTextBoxes("id", "email");
		SelCommonFunctions.clearTextBoxes("id", "password");
		SelCommonFunctions.enterText("id", "email", "jbk@gmail.com");
		SelCommonFunctions.enterText("id", "password", "123456");
		SelCommonFunctions.clickButton("tagName", "button");
		String invalidEmailErrorMsg = SelCommonFunctions.getText("id", "email_error");
		Assert.assertEquals(invalidEmailErrorMsg, "Please enter email as kiran@gmail.com");

	}

	@Test(priority = 12)
	public void verifyErrorMsgForInvalidLoginPassword() {
		SelCommonFunctions.clearTextBoxes("id", "email");
		SelCommonFunctions.clearTextBoxes("id", "password");
		SelCommonFunctions.enterText("id", "email", "kiran@gmail.com");
		SelCommonFunctions.enterText("id", "password", "546987");
		SelCommonFunctions.clickButton("tagName", "button");
		String invalidPasswordErrorMsg = SelCommonFunctions.getText("id", "password_error");
		Assert.assertEquals(invalidPasswordErrorMsg, "Please enter password 123456");
	}

	@Test(priority = 13)
	public void verifyLogin() {
		SelCommonFunctions.clearTextBoxes("id", "email");
		SelCommonFunctions.clearTextBoxes("id", "password");
		SelCommonFunctions.enterText("id", "email", "kiran@gmail.com");
		SelCommonFunctions.enterText("id", "password", "123456");
		SelCommonFunctions.clickButton("tagName", "button");
		String dashboardPageTitle = driver.getTitle();
		System.out.println(dashboardPageTitle);
		Assert.assertEquals(dashboardPageTitle, "JavaByKiran | Dashboard");
	}
	
	@Test(priority = 14)
	public void verifyUsersLinkText() throws IOException {
		String UsersLinkText = SelCommonFunctions.getText("xpath",
				SelCommonFunctions.getXpathFileParamValue("userslinkxpath"));
		Assert.assertEquals(UsersLinkText, "Users");
	}

	@Test(priority = 15)
	public void addUser() throws IOException {
		SelCommonFunctions.clickButton("xpath", SelCommonFunctions.getXpathFileParamValue("adduserlinkxpath"));
		SelCommonFunctions.clickButton("tagName", "button");
		SelCommonFunctions.enterText("id", "username", "kirti");
		SelCommonFunctions.enterText("id", "mobile", "1234567890");
		SelCommonFunctions.enterText("id", "email", "kirti2@gmail.com");
		SelCommonFunctions.enterText("id", "course", "Selenium");
		SelCommonFunctions.clickButton("id", "Female");
		WebElement s = driver.findElement(By.tagName("select"));
		Select select = new Select(s);
		select.selectByVisibleText("Maharashtra");
		SelCommonFunctions.enterText("id", "password", "kirti@123");
		SelCommonFunctions.enterText("xpath", SelCommonFunctions.getXpathFileParamValue("friendmobilexpath"),
				"1234567890");
		SelCommonFunctions.clickButton("tagName", "button");
		String actUserAddedMsg = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		String expUserAddedMsg = SelCommonFunctions.getXpathFileParamValue("expUserAddedMsg");
		Assert.assertEquals(actUserAddedMsg, expUserAddedMsg);
	}

	@Test(priority = 16)
	public void verifyOperatorsLinkText() throws IOException {
		String OperatorsLinkText = SelCommonFunctions.getText("xpath",
				SelCommonFunctions.getXpathFileParamValue("operatorslinkxpath"));
		Assert.assertEquals(OperatorsLinkText, "Operators");
	}

	@Test(priority = 17)
	public void verifyUsefulLinksText() throws IOException {
		String UsefulLinksText = SelCommonFunctions.getText("xpath",
				SelCommonFunctions.getXpathFileParamValue("usefullinkxpath"));
		Assert.assertEquals(UsefulLinksText, "Useful Links");
	}

	@Test(priority = 18)
	public void verifyDownloadsLinksText() throws IOException {
		String UsefulLinksText = SelCommonFunctions.getText("xpath",
				SelCommonFunctions.getXpathFileParamValue("downloadslinkxpath"));
		Assert.assertEquals(UsefulLinksText, "Downloads");
	}
	
	@Test(priority = 19)
	public void clickDownloadsLinks() throws IOException {
		SelCommonFunctions.clickButton("xpath", SelCommonFunctions.getXpathFileParamValue("downloadslinkxpath"));
		String downloadsPageTitle = driver.getTitle();
		Assert.assertEquals(downloadsPageTitle, "JavaByKiran | Downloads");
	}
	
/*	@Test(priority = 20)
	public void clickFirstRowButton() throws IOException {
		SelCommonFunctions.clickButton("xpath", SelCommonFunctions.getXpathFileParamValue("1stjdkrowxpath"));
		String downloadsPageTitle = driver.getTitle();
		Assert.assertEquals(downloadsPageTitle, "Java SE Development Kit 8 - Downloads");
	}
*/	
	@Test(priority = 21)
	public void verifyLogoutLinksText() throws IOException {
		String LogoutLinksText = SelCommonFunctions.getText("xpath",
				SelCommonFunctions.getXpathFileParamValue("logoutlinkxpath"));
		Assert.assertEquals(LogoutLinksText, "Logout");
	}
	
	@Test(priority = 22)
	public void clickLogoutLinks() throws IOException {
		SelCommonFunctions.clickButton("xpath", SelCommonFunctions.getXpathFileParamValue("logoutlinkxpath"));
		String downloadsPageTitle = driver.getTitle();
		Assert.assertEquals(downloadsPageTitle, "JavaByKiran | Log in");
	}
}
