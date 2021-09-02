package seltestngbasic;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Info {

	//SelCommonFunctions Info:

	//	static WebDriver driver = null;	 //OR create @BeforeClass setUpEnvironment() method in class LoginTestCases & create object of this class
	// inside it & call all methods of this class by its object directly. i.e. seleniumCommonFunctions.enterText

	/*	SelCommonFunctions(WebDriver driver){
		this.driver = driver;
	}
	 */
	/*	 public static WebDriver openBrowser(String brName) {			//this method if user wants to write only for one browser
	 System.setProperty("webdriver.chrome.driver", "chromedriver.exe"); 
	 WebDriver driver = new ChromeDriver(); // brName.equals("chrome"); not allowed in industry as nullPointerException can occur
	 return driver; } //OR
	 */	 

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

	public static void main(String[] args) throws BiffException, WriteException, IOException {
		writeExstingExcel("C:\\Users\\61435\\Documents\\Selenium\\Employee.xls", "C:\\Users\\61435\\Documents\\Selenium\\new.xls", 3, 0, "demo");
	}
}
