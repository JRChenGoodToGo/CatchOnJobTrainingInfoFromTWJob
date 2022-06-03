package htmlTag;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import fnTest.TestIniReader;
import iniReadDao.IniReaderDao;

// perform click onto RadioBtn-> https://stackoverflow.com/questions/44912203/selenium-web-driver-java-element-is-not-clickable-at-point-x-y-other-elem
// clickable link on JLabel-> https://www.codejava.net/java-se/swing/how-to-create-hyperlink-with-jlabel-in-java-swing

public class TestSeleniumWebDriver {
	private static String URL="https://www.surveycake.com/s/xvrnW";
	
	private static WebDriver driver;
	
	private static TestIniReader testIniReader;
	private static IniReaderDao iniReaderDao;
	private static String sectionDef="DefineProcedure";
	private static boolean doWait=true;
	
	public TestSeleniumWebDriver() {
	}
	public void showWebLink(final String browser, String[] exceptionMessage) {
		System.out.println("enter showWebLink()");
		JFrame frame = new JFrame();
		frame.setTitle("Need a updated version of web driver!");
		JLabel labelLink = new JLabel();
		labelLink.setForeground(Color.BLUE);
		labelLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelLink.setText("<html><body>"
							+ "click link here to download web driver<br>"
							+ exceptionMessage[1]
							+ "</body></html>");
		labelLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(browser.toLowerCase().equals("google")) {
						Desktop.getDesktop().browse(new URI("https://sites.google.com/chromium.org/driver/downloads?authuser=0"));
					} else if(browser.toLowerCase().equals("edge")) {
						Desktop.getDesktop().browse(new URI("https://msedgewebdriverstorage.z22.web.core.windows.net/"));
					}
				} catch(Exception eBrowsePage) {
					System.out.println("eBrowsePage="+eBrowsePage);
				}
			}
		});
		frame.setLayout(new FlowLayout());
		frame.setSize(800, 150);
		frame.getContentPane().add(labelLink);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public void showThisDir() {
		System.out.println("webDriver location="+iniReaderDao.getWebDriverLocation());
		String pathFileOfWebBrowserInStringType= iniReaderDao.getWebDriverLocation();
		
		try {
			File pathFileOfWebBrowser = new File(pathFileOfWebBrowserInStringType);
			// remove xxxxdriver.exe
			String pathOfExeFile = pathFileOfWebBrowserInStringType.substring(0, pathFileOfWebBrowserInStringType.indexOf(pathFileOfWebBrowser.getName()));
			System.out.println("pathOfExeFile="+pathOfExeFile);
			int answerOfOpenDirOrNot = JOptionPane.showConfirmDialog(null, "open this directory?");
			System.out.println("answerOfOpenDirOrNot="+answerOfOpenDirOrNot);
			if(answerOfOpenDirOrNot==0) {
				Desktop.getDesktop().open(new File(pathOfExeFile));;
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	public void setBrowser(String browser, String webDriverLocation) {
		try {
			if(browser.toLowerCase().equals("google")) {
				System.setProperty("webdriver.chrome.driver", webDriverLocation);
				driver = new ChromeDriver();
			} else if(browser.toLowerCase().equals("edge")) {
				System.setProperty("webdriver.edge.driver", webDriverLocation);
				driver = new EdgeDriver();	
			}
		} catch(SessionNotCreatedException e) {
			System.out.println("web driver encounter some problems ->");
			System.out.println("got message->\n");
			String[] splittedExceptionMessage = e.getMessage().split("[\n]");
			// check all Exception message in array
//			for(int loopEcpNum=0; loopEcpNum<splittedExceptionMessage.length; loopEcpNum++) {
//				System.out.println(splittedExceptionMessage[loopEcpNum]+", ");
//			}
			JOptionPane.showMessageDialog(null, "web driver encounter some problems!! \nPlease check the version\n"+splittedExceptionMessage[0]+", \n"+splittedExceptionMessage[1]);
			showThisDir();
			showWebLink(browser, splittedExceptionMessage);
//			e.printStackTrace();
		} catch(Exception eAll) {
			System.out.println("web driver encounter some problems, wrong position");
			JOptionPane.showMessageDialog(null, "web driver may have a wrong position ");
			eAll.getMessage();
		}
	}
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
		
		System.out.println("flag1");
		testSeleniumWebDriver.setBrowser(iniReaderDao.getBrowser(), iniReaderDao.getWebDriverLocation());
		System.out.println("flag2");
		
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
		waitAWhile(2000, true);
		
		//if no auto close page, detect if user already close page
		//if already close page, throw NoSuchSessionException if driver.getTitle encounter problem
		while(iniReaderDao.getAutoClosePage().toLowerCase().equals("false")) {
			// detect web page is closed
			try {
				System.out.println("driver.getCurrentUrl()="+driver.getCurrentUrl());
				System.out.println("driver.getTitle()="+driver.getTitle());
				waitAWhile(5000, true);
				
			} catch(UnhandledAlertException eUnhandledAlert) {
				System.out.println("eUnhandledAlert="+eUnhandledAlert);
				driver.close();
				driver.quit();
			} catch(NoSuchSessionException eNoSuchSession) {
				System.out.println("eNoSuchSession="+eNoSuchSession);
				driver.close();
				driver.quit();
			} catch(WebDriverException eWebDriver) {
				try {
					System.out.println("eWebDriver="+eWebDriver);
				} catch(Exception eTest) {
					System.out.println("eTest="+eTest);
				}
				System.out.println("is it webDriverException?");
				driver.quit();
			} catch(Exception eAll) {
				System.out.println("eAll="+eAll);
				driver.close();
				driver.quit();
			}
		}
		System.out.println("outside while loop");
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

