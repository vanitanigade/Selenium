package seltestngbasic;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTestCases {
	
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
	
	@AfterClass // ctrl + shift + o
	void closeDriver() {
		driver.close();
	}

	@Test(dataProvider = "loginData")
	public void login(String tcId, String tcTitle, String uname, String password, String expectedResult) throws InterruptedException {
		SelCommonFunctions.clearTextBoxes("id", "email");
		SelCommonFunctions.clearTextBoxes("id", "password");
		driver.findElement(By.id("email")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.tagName("button")).click();
		String actResult = "";
			
		if("blank username".equals(tcTitle)) 
			actResult = SelCommonFunctions.getText("id", "email_error");
		else if("blank password".equals(tcTitle)) 
			actResult = SelCommonFunctions.getText("id", "password_error");
		else if("invalid username".equals(tcTitle)) 
			actResult = SelCommonFunctions.getText("id", "email_error");
		else if("invalid password".equals(tcTitle)) 
			actResult = SelCommonFunctions.getText("id", "password_error");
		else 
			actResult = driver.getTitle();
		
		Assert.assertEquals(actResult, expectedResult);
		
	}
	
	@DataProvider
	public Object[][] loginData() {
		return SelCommonFunctions.getExcelData("loginData.xls","Sheet1");
	}
	
/*	@DataProvider(name="loginData")
	public Object[][] loginData() {
		Object[][] arrayObject = SelCommonFunctions.getExcelData("loginData.xls","Sheet1");
		return arrayObject;
	}
*/
}
