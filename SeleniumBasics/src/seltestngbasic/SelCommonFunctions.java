package seltestngbasic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;

public class SelCommonFunctions {

	static WebDriver driver = null;	

	/*	SelCommonFunctions(WebDriver driver){
		this.driver = driver;
	}
	 */
	public static WebDriver openBrowser(String brName) {
		//	WebDriver driver = null;
		if ("chrome".equals(brName)) { 
			System.setProperty(Constants.CHROMEBROWSER, Constants.CHROMEFILEPATH);
			driver = new ChromeDriver();
		} else if ("firefox".equals(brName) || "mozilla".equals(brName)) {
			System.setProperty("webdriver.firefox.driver", "firefoxdriver.exe");
			driver = new FirefoxDriver();
		} else if ("html".equals(brName)) {
			System.setProperty("webdriver.html.driver", "htmlunitdriver.exe");
			driver = new HtmlUnitDriver();
		} else {
			System.setProperty(Constants.IEBROWSER, Constants.IEBRFILEPATH);
			driver = new InternetExplorerDriver();
		}
		return driver;
	}

	public static void openUrl(String urlToOpen) {	//as driver is static then removed driver from (WebDriver driver, String urlToOpen) & other common methods
		driver.get(urlToOpen);
	}

	public static String getConfigFileParamValue(String parameter) {
		FileInputStream fis;
		Properties properties = null;
		try {
			fis = new FileInputStream("config.properties");
			properties = new Properties();
			properties.load(fis);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(parameter);
	}

	public static String getXpathFileParamValue(String parameter) throws IOException {
		FileInputStream fis;
		Properties properties = null;
		try {
			fis = new FileInputStream("xpath.properties");
			properties = new Properties();
			properties.load(fis);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(parameter);
	}

	public static WebElement getElementAsPerLocator(String locName, String locValue) {
		WebElement we = null;
		switch(locName) {
		case "id":
			we = driver.findElement(By.id(locValue));
			break;
		case "xpath":
			we = driver.findElement(By.xpath(locValue));
			break;
		case "className":
			we = driver.findElement(By.className(locValue));
			break;
		case "tagName":
			we = driver.findElement(By.tagName(locValue));
			break;
		case "linkText":
			we = driver.findElement(By.linkText(locValue));
			break;
		case "partialLinkText":
			we = driver.findElement(By.partialLinkText(locValue));
		default:
			break;
		}
		return we;
	}

	public static void clearTextBoxes(String locName, String locValue) {
		getElementAsPerLocator(locName, locValue).clear();
	}

	public static void enterText(String locName, String locValue, String dataToEnter) {
		getElementAsPerLocator(locName, locValue).sendKeys(dataToEnter);
	}

	public static void clickButton(String locName, String locValue) {
		getElementAsPerLocator(locName, locValue).click();
	}

	public static void clickLink(String locName, String locValue) {
		getElementAsPerLocator(locName, locValue).click();
	}

	public static String getText(String locName, String locValue) {
		String text = getElementAsPerLocator(locName, locValue).getText();
		return text;
	}

	public static String getAttributeValue(String locName, String locValue, String attributeName) {
		String attributeValue = getElementAsPerLocator(locName, locValue).getAttribute(attributeName);
		return attributeValue;
	}

	/**
	 * @param timeToWait
	 * this method is used to wait for mentioned time after any code of line execution
	 */
	public static void pause(int timeToWait) {
		try {
			Thread.sleep(timeToWait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String[][] getExcelData(String fileName, String sheetName) {
		String[][] arrayExcelData = null;
		try {
			FileInputStream fis = new FileInputStream(fileName);
			Workbook wb = Workbook.getWorkbook(fis);
			Sheet sh = wb.getSheet(sheetName);

			int totalNoOfCols = sh.getColumns()-1;
			int totalNoOfRows = sh.getRows();

			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];

			for (int i= 1 ; i < totalNoOfRows; i++) {

				for (int j=0; j < totalNoOfCols; j++) {
					arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
				}
			}
		} catch (IOException | BiffException  e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}

	public static void writeExstingExcel(String existingFile, String newFile, int colNo, int rowNo, String cellValue) throws BiffException, IOException, WriteException{
		Workbook workbook = Workbook.getWorkbook(new File(existingFile));
		File nwfile = new File(newFile);
		WritableWorkbook copywk = Workbook.createWorkbook(nwfile, workbook);  //create a new excel and copy from existing
		WritableSheet sheet = copywk.getSheet(0);
		Label label = new Label(colNo, rowNo, cellValue);	//Label(colno, rowno, string)
		sheet.addCell(label);
		copywk.write();
		copywk.close();
	}

}
