package htmlTag;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;

// Reference:
// **Selenium**
// CSDN-> https://blog.csdn.net/qq_22003641/article/details/79137327
// 生活記事簿-> http://hklifenote.blogspot.com/2018/03/javaselenium.html
// IT邦-> https://ithelp.ithome.com.tw/articles/10230426
// **ChromeDriver**
// Download-> https://sites.google.com/chromium.org/driver/downloads?authuser=0
// **JBrowseDriver**
// JBrowserDriver-> https://github.com/MachinePublishers/jBrowserDriver
// **Thread**
// openhome.cc-> https://openhome.cc/Gossip/JavaGossip-V2/WaitNotify.htm
// IT人-> https://iter01.com/273752.html
// IT邦-> https://ithelp.ithome.com.tw/articles/10202999

public class TestSeleniumRent591 {
	private static String URL="https://rent.591.com.tw/?region=6&section=79,78&searchtype=1&kind=3&showMore=1&rentprice=5000,7000";
//							  "https://rent.591.com.tw/?region=6&section=79,78&searchtype=1&kind=3&showMore=1&rentprice=5000,7000&firstRow=30&totalRows=39"

//							  "https://rent.591.com.tw/?region=6&section=79,78&searchtype=1&kind=3&showMore=1"
//							  "https://rent.591.com.tw/?region=6&section=79,78&searchtype=1&kind=3&showMore=1&firstRow=30&totalRows=72"
//							  "https://rent.591.com.tw/?region=6&section=79,78&searchtype=1&kind=3&showMore=1&firstRow=60&totalRows=72"
	
//	private static String URL="http://example.com";
	public static void main(String[] args) {
// use JBrowserDriver failed
//		JBrowserDriver driver = new JBrowserDriver(Settings.builder().timezone(Timezone.ASIA_SHANGHAI).build());
//		driver.get(URL);
//		System.out.println(driver.getStatusCode());
//		System.out.println(driver.getPageSource());
//		driver.quit();

		
// setProperty of chrome driver		
		String propertySet = System.setProperty("webdriver.chrome.driver","D:\\JavaFramePrac\\myPlugins\\chromedriver.exe");
		System.out.println("propertySet="+propertySet);
		WebDriver driver = new ChromeDriver();
		driver.get(URL);
		
		try {
			System.out.println("before sleep");
			Thread.sleep(2000);
			System.out.println("after sleep");
			String pageSource = driver.getPageSource();
//			System.out.println("pageSource=\n"+pageSource);
//			Thread.sleep(3000);
			
			Document doc = Jsoup.parse(pageSource);
			Elements eSection = doc.select("section");
//			System.out.println("eSection="+eSection);
			
//			String dataBind12235149 = eSection.attr("data-bind");
			// get all rent id at one page (30)
			List<String> eachDataBind = eSection.eachAttr("data-bind");
			System.out.println("eachDataBind="+eachDataBind);
			System.out.println("eachDataBind.size()="+eachDataBind.size());

			// 先取得換頁數bar
			Elements pageLimitBar = doc.select("section[class='vue-public-list-page'] > div[class='page-limit']");
			System.out.println("pageLimitBar="+pageLimitBar);
			// 取得各個頁數的標籤"a[class='pageNum-form'][href='javascript:;'], 
			// List<String> -> eachAttr("data-first")得到各頁面的dataFirst值
			// List<String> ->用eachText()得到最大數為最後頁數，即連接網頁-1(currentPage)次數
			// for(<list.size()) List裡 get(i)取dataFirst值, 每次連接新網頁
			// connect("https...&firstRow={dataFirst}&totalRows={dataTotal}")
			String dataFirst = pageLimitBar.select("a[class='pageNum-form'][href='javascript:;']").attr("data-first");
			String dataTotal = pageLimitBar.select("a[class='pageNum-form'][href='javascript:;']").attr("data-total");
			System.out.println("dataFirst="+dataFirst);
			System.out.println("dataTotal="+dataTotal);
			
			driver.close();
			driver.quit();
		} catch(Exception e) {
			e.getMessage();
		}
		
	}
}
