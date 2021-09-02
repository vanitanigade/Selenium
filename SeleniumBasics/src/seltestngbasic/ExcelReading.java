package seltestngbasic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelReading {

	public static void main(String[] args) throws BiffException, IOException {

		FileInputStream fis = new FileInputStream("Employee.xls");
		Workbook workbook = Workbook.getWorkbook(fis);
		Sheet sheet = workbook.getSheet("Sheet1");
		Cell cell = sheet.getCell("C6");
		String cellValue = cell.getContents();
		System.out.println("C6 cell value: " + cellValue);

		int rows = sheet.getRows();
		int columns = sheet.getColumns();
		System.out.println("no of rows: " + rows);
		System.out.println("no of columns " + columns);

		System.out.println("==============================");	

		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				Cell cel = sheet.getCell(j, i);
				String celValue = cel.getContents();
				System.out.print(celValue + "     ");
			}
			System.out.println();
		}  


		
	/* Workbook workbook = Workbook.getWorkbook(new File("Employee.xls"));
		  Sheet sheet = workbook.getSheet(0); 
		  for(int i=0; i<sheet.getRows();i++)
		  {
		   for(int j=0; j<sheet.getColumns();j++)
		   {
		    Cell cell = sheet.getCell(j,i);
		    System.out.print(cell.getContents());
		    //get their types
		    CellType type = sheet.getCell(j,i).getType();
		    System.out.print("("+type+")" + " ");
		   }
		   System.out.println(" ");
		  } 

		  CellType type = sheet.getCell(1,1).getType();
		  if (type == CellType.LABEL)
		  {
		   System.out.println("cell(1,1) data is a label");
	  } 
*/
	}

}
