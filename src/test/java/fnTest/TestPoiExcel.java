package fnTest;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import htmlTag.TestSeleniumRent591;
import htmlTagDao.Rent591ItemDao;
import htmlTagDao.Rent591ItemsDao;

public class TestPoiExcel {
	private static TestPoiExcel testPoiExcel=new TestPoiExcel();
	private static TestSeleniumRent591 testSeleniumRent591;
	private static final String pathFile = "D:\\JavaFramePrac\\eclipse-workspace\\test\\src\\test\\java\\fnTest\\";
	private static final String fileName = "test2.xlsx";
	private static XSSFWorkbook wb;
	private static XSSFSheet sheet0;
	private static XSSFCellStyle xssfCellStyleFormHead, xssfCellStyleFormContents;
	private static org.apache.poi.ss.usermodel.Font xssfFont;
	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";
	private static String PATH="D:\\JavaFramePrac\\eclipse-workspace\\test\\src\\test\\java\\htmlTag";
	private static String FILENAME="Rent591ItemsDao.txt";

	
	public TestPoiExcel() {
		wb = new XSSFWorkbook();
		iniStyle();
	}
	public TestPoiExcel(TestSeleniumRent591 testSeleniumRent591) {
		this.testSeleniumRent591=testSeleniumRent591;
	}
	public TestSeleniumRent591 getTestSeleniumRent591() {
		return this.testSeleniumRent591;
	}
	public static void main(String[] args) {
		try {
//			Workbook wb = getWorkbook(new File(pathFile+fileName));
			
//			Sheet sheet0 = wb.getSheetAt(0);
//			System.out.println("sheet0="+sheet0);
			
			testPoiExcel = new TestPoiExcel(new TestSeleniumRent591());
			
			
//			sheet0.setSheetPassword("123", null);
			// print width of column
//			for(int getColWidthNum=0; getColWidthNum<6; getColWidthNum++) {
//				System.out.println(sheet0.getColumnWidth(getColWidthNum));
//			}
			
//			// A1, B3, B4有值時
//			System.out.println("sheet0.getFirstRowNum()="+sheet0.getFirstRowNum());//0
//			System.out.println("sheet0.getLastRowNum()="+sheet0.getLastRowNum());//3
//			System.out.println("sheet0.getLeftCol()="+sheet0.getLeftCol()); //0-based, 0
//			System.out.println("sheet0.getPhysicalNumberOfRows()="+sheet0.getPhysicalNumberOfRows());//3
//			System.out.println("sheet0.getTopRow()="+sheet0.getTopRow());//0
//			
//			System.out.println("sheet0.getRow(0)="+sheet0.getRow(0));
//			System.out.println("sheet0.getRow(0).getLastCellNum(0)="+sheet0.getRow(0).getLastCellNum());//1-based, 1
//			System.out.println("sheet0.getRow(0).getCell(...)="+sheet0.getRow(0).getCell(sheet0.getRow(0).getLastCellNum()-1));//test1

			TestSeleniumRent591 testSeleniumRent591 = testPoiExcel.getTestSeleniumRent591();
			Object readObject = testSeleniumRent591.readInfosUnderObjectInputStream(new File(PATH), FILENAME);
			String[] formHead = new String[]{"ID", "標題", "坪數", "地址", "價錢"};
			if(readObject instanceof Rent591ItemsDao) {
				Rent591ItemsDao showIdentity = (Rent591ItemsDao) readObject;
				System.out.println("showIdentity.getItemListImgList().get(0)="+showIdentity.getItemListImgList().get(0));
				System.out.println("showIdentity.getDataBindIdList().size()="+showIdentity.getDataBindIdList().size());
				
				int rowBase=2;//第2列
				int colBase=2;//第2欄
//				// 寫入表頭
//				Row createdRowHead = sheet0.createRow(rowBase-1);//0-based
//				for(int loopObjHeadNum=0; loopObjHeadNum<formHead.length; loopObjHeadNum++) {
//					testPoiExcel.writeInfoStartAt(sheet0, createdRowHead, colBase+loopObjHeadNum, formHead[loopObjHeadNum], false, true);
//				}
//				// 寫入欄位值
//				Row createdRow3;
//				for(int loopObjContentNum=0; loopObjContentNum<showIdentity.getDataBindIdList().size(); loopObjContentNum++) {
//					// 產生寫入列
//					// 從rowBase(0-base)值的欄位開始寫入 e.g. rowBase=2 -> 從第3列開始
//					createdRow3 = sheet0.createRow(rowBase+loopObjContentNum);
//					testPoiExcel.writeInfoStartAt(sheet0, createdRow3, colBase, showIdentity.getDataBindIdList().get(loopObjContentNum), false, false);
//					testPoiExcel.writeInfoStartAt(sheet0, createdRow3, colBase+1, showIdentity.getItemSubjectList().get(loopObjContentNum), true, false);
//					testPoiExcel.writeInfoStartAt(sheet0, createdRow3, colBase+2, showIdentity.getItemSpaceList().get(loopObjContentNum), false, false);
//					testPoiExcel.writeInfoStartAt(sheet0, createdRow3, colBase+3, showIdentity.getItemAddressList().get(loopObjContentNum), true, false);
//					testPoiExcel.writeInfoStartAt(sheet0, createdRow3, colBase+4, showIdentity.getItemCostList().get(loopObjContentNum), false, false);
//				}
				testPoiExcel.writeRent591ItemsInfoToExcel(formHead, rowBase, colBase, readObject);
				
			} else if(readObject instanceof Rent591ItemDao) {
				Rent591ItemDao showIdentity = (Rent591ItemDao) readObject;
				System.out.println("showIdentity="+showIdentity);
			}

//			String writeVal = testPoiExcel.writeInfoStartAt(sheet0, 2, 2, "test3");
//			System.out.println("write val:"+writeVal+" success!");
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	private void iniStyle() {
		System.out.println("enter iniStyle()");
		xssfCellStyleFormContents = wb.createCellStyle();
		xssfCellStyleFormHead = wb.createCellStyle();
		xssfFont = wb.createFont();
		
		sheet0 = wb.createSheet("rent591Info");
		System.out.println("sheet0="+sheet0);
		sheet0.setColumnWidth(1, 3072);
		sheet0.setColumnWidth(2, 4096);
		sheet0.setColumnWidth(4, 4096);
	}
	private void outputStreamMethod() {
		System.out.println("enter outputStreamMethod()");
		try {
			FileOutputStream fos = new FileOutputStream(pathFile+fileName);
			wb.write(fos);
			
			wb.close();
			fos.flush();
			fos.close();
		} catch(Exception e) {
			System.out.println(e);
		}

	}
	public void writeRent591ItemsInfoToExcel(String[] formHead, Integer rowBase, Integer colBase, 
										Object readObject) {
		System.out.println("enter writeRent591ItemsInfoToExcel()");
		Rent591ItemsDao showIdentity=null;
		if(readObject instanceof Rent591ItemsDao) {
			showIdentity = (Rent591ItemsDao) readObject;
		}
		// 寫入表頭
		Row createdRowHead = sheet0.createRow(rowBase-1);//0-based
		for(int loopObjHeadNum=0; loopObjHeadNum<formHead.length; loopObjHeadNum++) {
			testPoiExcel.writeInfoStartAt(sheet0, createdRowHead, colBase+loopObjHeadNum, formHead[loopObjHeadNum], false, true);
		}
		// 寫入欄位值
		Row createdRow3;
		for(int loopObjContentNum=0; loopObjContentNum<showIdentity.getDataBindIdList().size(); loopObjContentNum++) {
			// 產生寫入列
			// 從rowBase(0-base)值的欄位開始寫入 e.g. rowBase=2 -> 從第3列開始
			createdRow3 = sheet0.createRow(rowBase+loopObjContentNum);
			testPoiExcel.writeInfoStartAt(sheet0, createdRow3, colBase, showIdentity.getDataBindIdList().get(loopObjContentNum), false, false);
			testPoiExcel.writeInfoStartAt(sheet0, createdRow3, colBase+1, showIdentity.getItemSubjectList().get(loopObjContentNum), true, false);
			testPoiExcel.writeInfoStartAt(sheet0, createdRow3, colBase+2, showIdentity.getItemSpaceList().get(loopObjContentNum), false, false);
			testPoiExcel.writeInfoStartAt(sheet0, createdRow3, colBase+3, showIdentity.getItemAddressList().get(loopObjContentNum), true, false);
			testPoiExcel.writeInfoStartAt(sheet0, createdRow3, colBase+4, showIdentity.getItemCostList().get(loopObjContentNum), false, false);
		}
		outputStreamMethod();
	}
	private String writeInfoStartAt(Sheet sheet, Row row, Integer colNum, String writeVal, 
									boolean autoBreakTextInOneCell, boolean formHead) {
//		System.out.println("enter writeInfoStartAt()");
		Cell createdCellB = row.createCell(colNum-1);
		createdCellB.setCellValue(writeVal);
		if(formHead) {
//			System.out.println("formHead == true, writeVal="+writeVal);
//			xssfCellStyleFormHead.setWrapText(false);
			xssfCellStyleFormHead.setAlignment(HorizontalAlignment.CENTER);
//			xssfCellStyleFormHead.setVerticalAlignment(VerticalAlignment.TOP);
			xssfFont.setFontName("Cambria");
			xssfCellStyleFormHead.setFont(xssfFont);
			createdCellB.setCellStyle(xssfCellStyleFormHead);
			return writeVal;
		} else if(autoBreakTextInOneCell) {
			xssfCellStyleFormContents.setWrapText(true);
			xssfCellStyleFormContents.setAlignment(HorizontalAlignment.LEFT);
			xssfCellStyleFormContents.setVerticalAlignment(VerticalAlignment.CENTER);
		}
		xssfFont.setFontName("Cambria");
		xssfCellStyleFormContents.setFont(xssfFont);
		createdCellB.setCellStyle(xssfCellStyleFormContents);

		return writeVal;
	}
	/**
	 * Judging the version of Excel
	 * 
	 * @param in
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWorkbook(File file) throws IOException {
		Workbook wb = null;
		FileInputStream in = new FileInputStream(file);
		if (file.getName().endsWith(EXCEL_XLS)) { // Excel 2003
			wb = new HSSFWorkbook(in);
			System.out.println("Excel file type:xls");
		} else if (file.getName().endsWith(EXCEL_XLSX)) { // Excel 2007/2010
			wb = new XSSFWorkbook(in);
			System.out.println("Excel file type:xlsx");
		}
		return wb;
	}

	public static void outputtoexcel(Workbook workbook) {  
    	OutputStream out = null;  
    	
    	int sheetCount = workbook.getNumberOfSheets(); //Total number of sheets 
    	System.out.println("sheetCount="+sheetCount);
        try {  
        	for(int s = 0; s < sheetCount; s++ ) {
        		System.out.println("————————————————————————Start processing sheet"+(s+1)+"————————————————————————");
	            String sheetname = workbook.getSheetName(s);
	            System.out.println("Name of sheet"+(s+1)+":"+sheetname);
	            //Set the subscript of the excel sheet: 0 start
	             org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(s);//The first sheet
	             System.out.println("sheet="+sheet);
	             // Set the count to skip the first line of the directory
	             int count = 0;  
	             //Total number of rows
	             int rowLength = sheet.getLastRowNum(); //0-based 
	             System.out.println("Total number of rows:"+ (rowLength+1));
	             //Get the first line  
	             Row rowzero = sheet.getRow(0); 
	             System.out.println("rowzero="+rowzero);
	             // rowzero=?---1欄1列時---
	             //<xml-fragment r="1" spans="1:1" x14ac:dyDescent="0.3" xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships" xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006" xmlns:x14ac="http://schemas.microsoft.com/office/spreadsheetml/2009/9/ac" xmlns:xr="http://schemas.microsoft.com/office/spreadsheetml/2014/revision" xmlns:xr2="http://schemas.microsoft.com/office/spreadsheetml/2015/revision2" xmlns:xr3="http://schemas.microsoft.com/office/spreadsheetml/2016/revision3" xmlns:main="http://schemas.openxmlformats.org/spreadsheetml/2006/main">
	             //		<main:c r="A1" t="s">
	             //			<main:v>0</main:v>
	             //		</main:c>
	             //</xml-fragment>
	             // rowzero=?---2欄2列時---
	             //<xml-fragment r="1" spans="1:2" x14ac:dyDescent="0.3" xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships" xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006" xmlns:x14ac="http://schemas.microsoft.com/office/spreadsheetml/2009/9/ac" xmlns:xr="http://schemas.microsoft.com/office/spreadsheetml/2014/revision" xmlns:xr2="http://schemas.microsoft.com/office/spreadsheetml/2015/revision2" xmlns:xr3="http://schemas.microsoft.com/office/spreadsheetml/2016/revision3" xmlns:main="http://schemas.openxmlformats.org/spreadsheetml/2006/main">
	             //		<main:c r="A1" t="s">
	             //			<main:v>0</main:v>
	             //		</main:c>
	             //		<main:c r="B1" t="s">
	             //			<main:v>1</main:v>
	             //		</main:c>
	             //</xml-fragment>
	             
	             //Total number of columns  
	             
	             int colLength = rowzero.getLastCellNum(); //1-based
	             System.out.println("Total number of columns:"+ colLength);
	             //Determine if the result column already exists, create it without
	             if("Result".equals(rowzero.getCell(colLength-1).getStringCellValue())) {
	            	 System.out.println("Result equals the one got");
	            	 colLength = colLength-1;
	             } else {
	            	 /** 
	            	  * Write a new column to the sheet
	            	  */
	            	 System.out.println("Result not equals the one got");
		             Cell newcell = rowzero.createCell(colLength);  
		             newcell.setCellValue("Result");  
	             }
           
            /* 
             for (int i = 0; i < rowLength;i ) {
                 Row row = sheet.getRow(i);
                 for (int j = 0; j < colLength; j ) {
                Cell cell = row.getCell(j);
                if(cell==null || “”.equals(cell.toString())){  
                    System.out.print(“null” ” “);
                     }else{
                    System.out.print(cell.toString() ” “);
                     }
                 }  
                 System.out.println();
             }  */
             int writeflag = 1;//Line number written
             for(int j = 0 ; j < (rowLength-1)/15; j++) {
            	 /** 
            	  * Write results back to sheet
            	  */
            	 System.out.println("j="+j+", (rowLength-1)/15="+(rowLength-1)/15);
                 Row writerow = sheet.getRow(writeflag);
                 Cell writecell = writerow.createCell(colLength);  
                 writecell.setCellValue("true");
                 writeflag =15;
             }
             // Create file output and prepare output table
            out =  new FileOutputStream(pathFile+"test2.xlsx");  
            workbook.write(out); 
            System.out.println("Data written successfully");  
            System.out.println("————————————————————————sheet"+ (s+ 1)+ " processing ends————————————————————————");
        }
         
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally{  
            try {  
                if(out != null){  
                    out.flush();  
                    out.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
	}
}
