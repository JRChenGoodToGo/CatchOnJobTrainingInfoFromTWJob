package htmlTag;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import fnTest.TestIniReader;
import iniReadDao.IniReaderDao;

// perform click onto RadioBtn-> https://stackoverflow.com/questions/44912203/selenium-web-driver-java-element-is-not-clickable-at-point-x-y-other-elem
public class TestSeleniumWebDriver {
	private static String URL="https://www.surveycake.com/s/xvrnW";
	
	private static WebDriver driver;
	
	private static TestIniReader testIniReader;
	private static IniReaderDao iniReaderDao;
	private static String sectionDef="DefineProcedure";
	private static boolean doWait=true;
	
	public TestSeleniumWebDriver() {
	}
	public static void setBrowser(String browser, String webDriverLocation) {
		if(browser.toLowerCase().equals("google")) {
			System.setProperty("webdriver.chrome.driver", webDriverLocation);
			driver = new ChromeDriver();
		} else if(browser.toLowerCase().equals("edge")) {
			
		}
	}
//	public TestSeleniumWebDriver(String browser) {
//		if(browser.toLowerCase().equals("google")) {
//			System.setProperty("webdriver.chrome.driver", webDriverLocation);
//			driver = new ChromeDriver();
//		} else if(browser.toLowerCase().equals("edge")) {
//			
//		}
//	}
	private TestIniReader getTestIniReaderInstance() {
		try {
			testIniReader=new TestIniReader();
			return testIniReader;
		} catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	private TestIniReader getTestIniReaderInstance(String path, String fileName) {
		try {
			return new TestIniReader(path, fileName);
		} catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static void main(String[] args) {
		TestSeleniumWebDriver testSeleniumWebDriver = new TestSeleniumWebDriver();
		testIniReader = testSeleniumWebDriver.getTestIniReaderInstance();
//		System.out.println("testIniReader in TestSeleniumWebDriver="+testIniReader);
		
		System.out.println("testIniReader.getIniReaderInstance()="+testIniReader.getIniReaderInstance());
		Set<String> gotSetOfDefPro = testIniReader.getKeySetOfDirectedSection(testIniReader.getIniReaderInstance(), sectionDef);
		System.out.println("gotSetOfDefPro="+gotSetOfDefPro);
		// 彙整此Section的資訊到DAO
		testIniReader.setIntactIniReaderDao(gotSetOfDefPro, sectionDef);
		// 彙整表單需填的預設資訊到DAO
		iniReaderDao = testIniReader.getIniReaderDaoOfFillInInfo();
		System.out.println("iniReaderDao in TestSeleniumWebDriver=\n"+iniReaderDao);
		
		setBrowser(iniReaderDao.getBrowser(), iniReaderDao.getWebDriverLocation());
		
		driver.get(URL);
		waitAWhile(3000, doWait);
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String q1A = sdf.format(date);
		System.out.println("q1A="+q1A);
// handle Calendar
//		WebElement eCalendarBar = driver.findElement(By.cssSelector("div[class='css-nfqlwp']"));
//		System.out.println("eCalendarBar="+eCalendarBar);
//		WebElement svgTag = eCalendarBar.findElement(By.cssSelector("svg"));
//		System.out.println("svgTag="+svgTag);
		WebElement svgTag = testSeleniumWebDriver.findCalendarIcon();
		svgTag.click();

//		WebElement eHidedCalendar = driver.findElement(By.cssSelector("div[class='css-ejxeg0']"));
//		System.out.println("eHidedCalendar="+eHidedCalendar);
//		WebElement today = eHidedCalendar.findElement(By.cssSelector("table tbody td[class='css-8omhdy'"));
//		System.out.println("today="+today);
		waitAWhile(500, doWait);
		WebElement today = testSeleniumWebDriver.findTodayGrid();
		today.click();

		if(iniReaderDao.getFillInFastOrSlow().toLowerCase().equals("fast")) {
			doWait=false;
		}

// handle textBox		
		List<WebElement> qTextBox = driver.findElements(By.className("css-1qu0lca"));
		//[[ChromeDriver: chrome on WINDOWS (e8f8541e08f0028f16a405eaccc02d22)] -> class name: css-1qu0lca]
//		System.out.println("qTextBox="+qTextBox);
//		System.out.println("qTextBox.size()="+qTextBox.size());
		// q2 - EmployeeNum
		qTextBox.get(1).sendKeys(iniReaderDao.getEmployeeNum());
		waitAWhile(1000, doWait);
		// q3 - TotalPurchasedTestsById
		qTextBox.get(2).sendKeys(iniReaderDao.getTotalPurchasedTestsById());
		waitAWhile(1000, doWait);
		// q4 - TotalGivenTestsRest
		WebElement q4Text = driver.findElement(By.cssSelector("div[data-subject-id='20568906']"));
		scrollToElementPosition(q4Text);
		waitAWhile(1000, doWait);
		qTextBox.get(3).sendKeys(iniReaderDao.getTotalGivenTestsRest());	
		waitAWhile(1000, doWait);
// handle Radio Btn
//		WebElement q5Id = driver.findElement(By.cssSelector("div[class='css-1kxnczn'][data-subject-option-id='40239006']"));
//		System.out.println("q5Id="+q5Id);
//		
//		WebElement q5RadioBtn = q5Id.findElement(By.cssSelector("span[class='css-j2bck'] div[class='Radio_border css-tqw1rw']"));
//		System.out.println("q5RadioBtn="+q5RadioBtn);
//		System.out.println("q5RadioBtn.isSelected()="+q5RadioBtn.isSelected());
		WebElement q5RadioBtn = testSeleniumWebDriver.findFRadioBtnById("40239006");
		
//		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", q5RadioBtn);
//		scrollToElementPosition(q5RadioBtn);
		Actions action = new Actions(driver);
		action.moveToElement(q5RadioBtn).click().build().perform();
		
		waitAWhile(1000, doWait);
		
//		WebElement q6Id = driver.findElement(By.cssSelector("div[class='css-1kxnczn'][data-subject-option-id='40239014']"));
//		System.out.println("q6Id="+q6Id);
//		WebElement q6RadioBtn = q6Id.findElement(By.cssSelector("span[class='css-j2bck'] div[class='Radio_border css-tqw1rw']"));
//		System.out.println("q6RadioBtn="+q6RadioBtn);
//		System.out.println("q6RadioBtn.isSelected()="+q6RadioBtn.isSelected());
		WebElement q6RadioBtn = testSeleniumWebDriver.findFRadioBtnById("40239014");

//		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", q6RadioBtn);
		WebElement q6Text = driver.findElement(By.cssSelector("div[data-subject-id='20554891']"));
		scrollToElementPosition(q6Text);
		action.moveToElement(q6RadioBtn).click().build().perform();
		
		waitAWhile(1000, doWait);
		
		WebElement q7RadioBtn = testSeleniumWebDriver.findFRadioBtnById("40239016");
//		scrollToElementPosition(q7RadioBtn);
		action.moveToElement(q7RadioBtn).click().build().perform();
		
		waitAWhile(1000, doWait);
		
		WebElement q8RadioBtn = testSeleniumWebDriver.findFRadioBtnById("40239018");
		WebElement q8Text = driver.findElement(By.cssSelector("div[data-subject-id='20554898']"));
		scrollToElementPosition(q8Text);
		action.moveToElement(q8RadioBtn).click().build().perform();
		
		waitAWhile(1000, doWait);
		
		WebElement submitBtn = driver.findElement(By.cssSelector("button[class='css-wj5s4x']"));
		scrollToElementPosition(submitBtn);
		if(iniReaderDao.getAutoSubmit().toLowerCase().equals("true")) {
			// 自動提交
			System.out.println("auto submit");
//			submitBtn.submit();
			action.moveToElement(submitBtn).click().build().perform();
		}
		if(iniReaderDao.getAutoClosePage().toLowerCase().equals("true")) {
			// 自動關閉網頁
			System.out.println("auto close page");
			waitAWhile(2000, true);
			driver.close();
			driver.quit();
		}
	}
	public static void waitAWhile(long timeMil, boolean doWait) {
		if(doWait) {
			try {
				Thread.sleep(timeMil);
			} catch(Exception e) {
				System.out.println(e);
			}
		}
	}
	public static void scrollToElementPosition(WebElement qxElement) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", qxElement);
	}
	public WebElement findFRadioBtnById(String id) {
		WebElement qxId = driver.findElement(By.cssSelector("div[class='css-1kxnczn'][data-subject-option-id='"+id+"']"));
//		System.out.println("qxId="+qxId);
		
		WebElement qxRadioBtn = qxId.findElement(By.cssSelector("span[class='css-j2bck'] div[class='Radio_border css-tqw1rw']"));
//		System.out.println("qxRadioBtn="+qxRadioBtn);
//		System.out.println("qxRadioBtn.isSelected()="+qxRadioBtn.isSelected());
		return qxRadioBtn;
	}
	public WebElement findCalendarIcon() {
		WebElement eCalendarBar = driver.findElement(By.cssSelector("div[class='css-nfqlwp']"));
//		System.out.println("eCalendarBar="+eCalendarBar);
		WebElement svgTag = eCalendarBar.findElement(By.cssSelector("svg"));
//		System.out.println("svgTag="+svgTag);
		return svgTag;
	}
	public WebElement findTodayGrid() {
		WebElement eHidedCalendar = driver.findElement(By.cssSelector("div[class='css-ejxeg0']"));
//		System.out.println("eHidedCalendar="+eHidedCalendar);
		WebElement todayGrid = eHidedCalendar.findElement(By.cssSelector("table tbody td[class='css-8omhdy'"));
//		System.out.println("todayGrid="+todayGrid);
		return todayGrid;
	}
}
