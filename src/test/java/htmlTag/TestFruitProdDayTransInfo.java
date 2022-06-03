package htmlTag;

import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestFruitProdDayTransInfo {
	private static TestFruitProdDayTransInfo testFruitProdDayTransInfo;
	public TestFruitProdDayTransInfo() {
	}

	private static String URL="https://amis.afa.gov.tw/fruit/FruitProdDayTransInfo.aspx";
	
	private static WebDriver driver;
	private static String driverLocation="D:\\JavaFramePrac\\myPlugins\\chromedriver.exe";
	public void setBrowser(String browser, String webDriverLocation) {
		try {
			if(browser.toLowerCase().equals("google")) {
				System.setProperty("webdriver.chrome.driver", webDriverLocation);
				driver = new ChromeDriver();
			} else if(browser.toLowerCase().equals("edge")) {
				System.setProperty("webdriver.edge.driver", webDriverLocation);
				driver = new EdgeDriver();	
			}
		} catch(Exception eAll) {
			System.out.println("web driver encounter some problems, wrong position");
			JOptionPane.showMessageDialog(null, "web driver may have a wrong position ");
			eAll.getMessage();
		}
	}
	public static void main(String[] args) {
		testFruitProdDayTransInfo = new TestFruitProdDayTransInfo();
		testFruitProdDayTransInfo.setBrowser("google", driverLocation);
		
		driver.get(URL);
		driver.findElement(By.cssSelector("textarea[id='ctl00_contentPlaceHolder_txtMarket']")).click();
		
		WebElement popupMenu = driver.findElement(By.cssSelector("div[aria-describedby='divDialog']"));
		System.out.println("popupMenu="+popupMenu);
		
		WebElement popupMenuDiv2 = popupMenu.findElement(By.cssSelector("div[id='divDialog']"));
		System.out.println("popupMenuDiv2="+popupMenuDiv2);
		
		WebElement popupMenuIframe = popupMenuDiv2.findElement(By.tagName("iframe"));
		System.out.println("popupMenuIframe="+popupMenuIframe);
		
		WebDriver selectFrameDriver = driver.switchTo().frame(popupMenuIframe);
		System.out.println("selectFrameDriver="+selectFrameDriver);
		
		WebElement popupMenuForm = selectFrameDriver.findElement(By.tagName("form"));
		System.out.println("popupMenuForm="+popupMenuForm);
		
		WebElement table = popupMenuForm.findElement(By.cssSelector("table[align='center'] "));
		System.out.println("table="+table);
		
		WebElement tr2 = table.findElement(By.cssSelector("tr:nth-of-type(1)"));
		System.out.println("tr2="+tr2);
		
		WebElement td = tr2.findElement(By.cssSelector("td"));
		System.out.println("td="+td);
		
		Select select = new Select(td.findElement(By.xpath("//select"))); //By.xpath("//select"))
		System.out.println("select="+select);

		select.selectByValue("109");
	}
}
