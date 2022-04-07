package proj_TestStuff;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.chrome.ChromeDriver;


//Reference:
//**Selenium**
//CSDN-> https://blog.csdn.net/qq_22003641/article/details/79137327
//生活記事簿-> http://hklifenote.blogspot.com/2018/03/javaselenium.html
//IT邦-> https://ithelp.ithome.com.tw/articles/10230426
//**ChromeDriver**
//Download-> https://sites.google.com/chromium.org/driver/downloads?authuser=0
//**JBrowseDriver**
//JBrowserDriver-> https://github.com/MachinePublishers/jBrowserDriver
//**Thread**
//openhome.cc-> https://openhome.cc/Gossip/JavaGossip-V2/WaitNotify.htm
//IT人-> https://iter01.com/273752.html
//IT邦-> https://ithelp.ithome.com.tw/articles/10202999

public class TestSeleniumRent591 {
	private static WebDriver driver;
	private static String URL="https://rent.591.com.tw/?region=6&section=79,78&searchtype=1&kind=3&showMore=1&rentprice=5000,7000";
//							  "https://rent.591.com.tw/?region=6&section=79,78&searchtype=1&kind=3&showMore=1&rentprice=5000,7000&firstRow=30&totalRows=39"

//							  "https://rent.591.com.tw/?region=6&section=79,78&searchtype=1&kind=3&showMore=1"
//							  "https://rent.591.com.tw/?region=6&section=79,78&searchtype=1&kind=3&showMore=1&firstRow=30&totalRows=72"
//							  "https://rent.591.com.tw/?region=6&section=79,78&searchtype=1&kind=3&showMore=1&firstRow=60&totalRows=72"
	
//	private static String URL="http://example.com";
	private static String pageSource;
	private static Document doc;
	private static Elements eItemListSection;
	
	private static List<String> eachDataBind, dataFirstList, dataTotalList, 
								eachDataBindTemp, pageList;
	public static void main(String[] args) {
//use JBrowserDriver failed
//		JBrowserDriver driver = new JBrowserDriver(Settings.builder().timezone(Timezone.ASIA_SHANGHAI).build());
//		driver.get(URL);
//		System.out.println(driver.getStatusCode());
//		System.out.println(driver.getPageSource());
//		driver.quit();

		
//setProperty of chrome driver		
		String propertySet = System.setProperty("webdriver.chrome.driver","D:\\JavaFrame\\myPlugins\\chromedriver.exe");
		System.out.println("propertySet="+propertySet);
		driver = new ChromeDriver();
		
		try {
			driver.get(URL);
			System.out.println("before sleep");
			Thread.sleep(3000);
			System.out.println("after sleep");
			pageSource = driver.getPageSource();
//			System.out.println("pageSource=\n"+pageSource);
//			Thread.sleep(3000);
			
			doc = Jsoup.parse(pageSource);
			eItemListSection = doc.select("section");
//			System.out.println("eItemListSection="+eItemListSection);
			
//			String dataBind12235149 = eItemListSection.attr("data-bind");
			// get all rent id that at first page (MAX: 30)
			eachDataBind = eItemListSection.eachAttr("data-bind");
			// [12318860, 12255470, ..., 12347459]
			System.out.println("eachDataBind="+eachDataBind);
			// length=30
			System.out.println("eachDataBind.size()="+eachDataBind.size());

			Elements eItemListSectionCosts = doc.select("section[data-browse-stated][class='vue-list-rent-item'] ");
			System.out.println("eItemListSectionCosts="+eItemListSectionCosts);
			
			
// 換頁條件			
			// 先取得換頁數bar
			Elements pageLimitBar = doc.select("section[class='vue-public-list-page'] > div[class='page-limit']");
			// <div class="page-limit">
			// 		<a class="pagePrev first">
			// 			<span>上一頁</span>
			// 		</a>
			// 		<span class="pageCurrent">1</span>
			// 		<a class="pageNum-form" href="javascript:;" data-first="30" data-total="38">2</a>
			// ......
			// </div>
			System.out.println("pageLimitBar="+pageLimitBar);
			// 取得各個頁數的標籤"a[class='pageNum-form'][href='javascript:;'], 
			// <a class="pageNum-form" href="javascript:;" data-first="30" data-total="38">2</a>
			Elements nextPageSection = pageLimitBar.select("a[class='pageNum-form'][href='javascript:;']");
			System.out.println("nextPageSection="+nextPageSection);
			// List<String> -> eachAttr("data-first")得到各頁面的dataFirst值
			// [30]
			dataFirstList = nextPageSection.eachAttr("data-first");
			System.out.println("dataFirstList="+dataFirstList);
			// List<String> -> eachAttr("data-total")得到各頁面的dataTotal值
			// [38]
			dataTotalList = nextPageSection.eachAttr("data-total");
			System.out.println("dataTotalList="+dataTotalList);
			// List<String> ->用eachText()得到最大數為最後頁數，即連接網頁-1(currentPage)次數
			// [2]
			pageList = nextPageSection.eachText();
			System.out.println("pageList="+pageList);
			
			// for(<list.size()) List裡 get(i)取dataFirst值, 每次連接新網頁
			// connect("https...&firstRow={dataFirst}&totalRows={dataTotal}")
			// 取得下一個頁數的URL攜帶條件
			TestSeleniumRent591 testSeleniumRent591 = new TestSeleniumRent591();
			String nextPageUrl="";
			String currentUrl=driver.getCurrentUrl();
			if(dataFirstList.size()==0) {
				// only one page
				nextPageUrl = currentUrl+"&firstRow="+dataFirstList.get(0)+"&totalRows="+dataTotalList.get(0);
				System.out.println("nextPageUrl="+nextPageUrl);
				
			} else {
				// > one page
				// 
				for(int loopPageNum=0; loopPageNum<dataFirstList.size(); loopPageNum++) {
					nextPageUrl = currentUrl+"&firstRow="+dataFirstList.get(loopPageNum)+"&totalRows="+dataTotalList.get(loopPageNum);
					System.out.println("nextPageUrl="+nextPageUrl);
					eachDataBindTemp = testSeleniumRent591.goToNextPageAndGetDataBindIds(nextPageUrl);
					// put list of resources of nextPage to the original List 
					// [12318860, 12255470, ..., 12347459] addAll [6813041, 12335890, ...]
					eachDataBind.addAll(eachDataBindTemp);
					// [12318860, 12255470, ..., 12347459, 6813041, 12335890, ...]

				}
				
			}
			
			System.out.println("------------");
			
// switch to new tab-method1
//			Set<String> windowHandles = driver.getWindowHandles();
//			// [CDwindow-D68E5E3BAA069824CEDEB7056D15FC59]
//			System.out.println("windowHandle="+windowHandles);
//			// length=1
//			System.out.println("windowHandles.toArray().length="+windowHandles.toArray().length);
//			// CDwindow-3641EF5ACAB4C454C90DA1BEE391DBCA
//			System.out.println("windowHandles.toArray()[0]="+windowHandles.toArray()[0]);
			
// switch to new tab-method2			
//			ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
//			System.out.println("windowHandles="+windowHandles);
//			System.out.println("windowHandles.get(0)="+windowHandles.get(0));
//			
//			WebDriver driver2 = driver.switchTo().window((String)windowHandles.get(0));
	
			
			System.out.println("eachDataBind="+eachDataBind);
			// length=38
			System.out.println("eachDataBind.size()="+eachDataBind.size());
			
			driver.close();
			driver.quit();
			
		} catch(Exception e) {
			e.getMessage();
		}
		
	}
	public List<String> goToNextPageAndGetDataBindIds(String nextPageUrl) throws Exception{
		driver.get(nextPageUrl);
		
		Thread.sleep(3000);
		pageSource = driver.getPageSource();
//		System.out.println("pageSource2=\n"+pageSource2);
//		Thread.sleep(3000);
		
		// 原本的pageSource直接被覆蓋
		doc = Jsoup.parse(pageSource);
		eItemListSection = doc.select("section");
//		System.out.println("eItemListSection="+eItemListSection);
		
//		String dataBind12235149 = eItemListSection.attr("data-bind");
		// get all rent id that at one page (8)
		eachDataBindTemp = eItemListSection.eachAttr("data-bind");
		// [6813041, 12335890, ...]
		System.out.println("eachDataBindTemp="+eachDataBindTemp);
		// length=8
		System.out.println("eachDataBindTemp.size()="+eachDataBindTemp.size());
		return eachDataBindTemp;
	}
}
