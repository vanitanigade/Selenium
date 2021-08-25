package seltestngbasic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SeleniumCommonFunctions1 {

	/*
	 * public static WebDriver openBrowser(String brName) {
	 * System.setProperty("webdriver.chrome.driver", "chromedriver.exe"); 
	 * WebDriver driver = new ChromeDriver(); return driver; }               //OR
	 */
	public static WebDriver openBrowser(String brName) {
		WebDriver driver = null;

		if ("chrome".equals(brName)) { // brName.equals("chrome"); not allowed in industry as nullPointerException can
										// occur
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		} else if ("firefox".equals(brName) || "mozilla".equals(brName)) {
			System.setProperty("webdriver.firefox.driver", "firefoxdriver.exe");
			driver = new FirefoxDriver();
		} else if ("html".equals(brName)) {
			System.setProperty("webdriver.html.driver", "htmlunitdriver.exe");
			driver = new HtmlUnitDriver();
		} else {
			System.setProperty("webdriver.internetexplorer.driver", "internetexplorer.exe");
			driver = new InternetExplorerDriver();
		}
		return driver;
	}

	public static void openUrl(WebDriver driver, String urlToOpen) {
		driver.get(urlToOpen);
	}

	public static void enterText(WebDriver driver, String locName, String locValue, String dataToEnter) {

		if ("id".equals(locName)) {
			driver.findElement(By.id(locValue)).sendKeys(dataToEnter);
		}
		if ("xpath".equals(locName)) {
			driver.findElement(By.xpath(locValue)).sendKeys(dataToEnter);
		}
		if ("className".equals(locName)) {
			driver.findElement(By.className(locValue)).sendKeys(dataToEnter);
		}
		if ("tagName".equals(locName)) {
			driver.findElement(By.tagName(locValue)).sendKeys(dataToEnter);
		}
	}

	public static void clickButton(WebDriver driver, String locValue) {
		driver.findElement(By.xpath(locValue)).click();
	}

	public static void clickLink(WebDriver driver, String locName, String locValue) {

		if ("linkText".equals(locName)) {
			driver.findElement(By.linkText(locValue)).click();
		}
		if ("partialLinkText".equals(locName)) {
			driver.findElement(By.partialLinkText(locValue)).click();
		}
	}

	public static String getText(WebDriver driver, String locName, String locValue) {
		String text = "";
		if ("id".equals(locName)) {
			driver.findElement(By.id(locValue)).getText();
		}
		if ("xpath".equals(locName)) {
			driver.findElement(By.xpath(locValue)).getText();
		}
		if ("className".equals(locName)) {
			driver.findElement(By.className(locValue)).getText();
		}
		return text;
	}

	public static String getAttributeValue(WebDriver driver, String locName, String locValue, String attributeName) {
		String attributeValue = "";

		if ("id".equals(locName)) {
			attributeValue = driver.findElement(By.id(locValue)).getAttribute(attributeName);
		}
		if ("xpath".equals(locName)) {
			attributeValue = driver.findElement(By.xpath(locValue)).getAttribute(attributeName);
		}
		return attributeValue;
	}

	public static void pause(int timeToWait) {
		try {
			Thread.sleep(timeToWait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
