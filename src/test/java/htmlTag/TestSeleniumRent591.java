package htmlTag;

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
//**jsoup
//Selector-> https://jsoup.org/apidocs/org/jsoup/select/Selector.html
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
	private static Elements eItemSections;
	
	private static List<String> dataBindIdList, dataFirstList, dataTotalList, 
								dataBindIdTempList, pageList, 
								itemListSubject, itemListSpace, 
								itemListAddress, itemListCost;
	private static List<String> itemListImg;
	private static TestSeleniumRent591 testSeleniumRent591 = new TestSeleniumRent591();
	private static String firstPageUrl;
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
//			driver.get(URL);
//			System.out.println("before sleep");
//			Thread.sleep(3000);
//			System.out.println("after sleep");
//			pageSource = driver.getPageSource();
////			System.out.println("pageSource=\n"+pageSource);
////			Thread.sleep(3000);
//			
//			doc = Jsoup.parse(pageSource);
//			eItemSections = doc.select("section");
//			System.out.println("eItemSections="+eItemSections);
			
			eItemSections = testSeleniumRent591.connectDirectedUrlAndGetItemSections(URL);
			
//			String dataBind12235149 = eItemSections.attr("data-bind");
// get all rent id that at first page(MAX: 30)
//			dataBindIdList = eItemSections.eachAttr("data-bind");
			dataBindIdList = testSeleniumRent591.getDataBindIdListOfOnePage(eItemSections);
			// [12318860, 12255470, ..., 12347459]
			System.out.println("dataBindIdList="+dataBindIdList+", size="+dataBindIdList.size());
			// length=30
			
// get all required info at first page(MAX:30)
//// 取得所需資訊，裝到List內		
//// 須注意在List內的物件是位於哪個URL底下			
//			// 主題 subject
//			Elements eItemListSectionSubject = eItemSections.select("div[class='item-title']");
//			// <div class="item-title">南青路(近南崁路二段)挑高.採光佳套房 <!----></div>
////			System.out.println("eItemListSectionSubject="+eItemListSectionSubject);
//			itemListSubject = eItemListSectionSubject.eachText();
//			itemListSubject = testSeleniumRent591.getSubjectListOfOnePage(eItemSections);
//			System.out.println("itemListSubject="+itemListSubject+", size="+itemListSubject.size());
//			
//			// 坪數 space
//			Elements eItemListSectionSpaces = eItemSections.select("ul[class='item-style'] > li:eq(1)");
//			// <li>4坪</li>
////			System.out.println("eItemListSectionSpaces="+eItemListSectionSpaces);
//			itemListSpace = eItemListSectionSpaces.eachText();
//			itemListSpace = testSeleniumRent591.getSpaceListOfOnePage(eItemSections);
//			System.out.println("itemListSpace="+itemListSpace+", size="+itemListSpace.size());
//			
//			// 地址 address
//			Elements eItemListSectionAddresses = eItemSections.select("div[class='item-area'] > span");
//			// <span>蘆竹區-忠孝西路</span>
////			System.out.println("eItemListSectionAddresses="+eItemListSectionAddresses);
//			itemListAddress = eItemListSectionAddresses.eachText();
//			itemListAddress = testSeleniumRent591.getAddressListOfOnePage(eItemSections);
//			System.out.println("itemListAddress="+itemListAddress+", size="+itemListAddress.size());
//			
//			// 費用 cost
//			Elements eItemListSectionCosts = eItemSections.select("div[class='item-price-text'] > span");
//			// <span>7,000</span>
////			System.out.println("eItemListSectionCosts="+eItemListSectionCosts);
//			itemListCost = eItemListSectionCosts.eachText();
//			itemListCost = testSeleniumRent591.getCostListOfOnePage(eItemSections);
//			System.out.println("itemListCost="+itemListCost+", size="+itemListCost.size());
			
// 鎖定firstPage的換頁欄，取得所有ID			
//			// 先取得換頁數bar
//			// <div class="page-limit">
//			// 		<a class="pagePrev first">
//			// 			<span>上一頁</span>
//			// 		</a>
//			// 		<span class="pageCurrent">1</span>
//			// 		<a class="pageNum-form" href="javascript:;" data-first="30" data-total="38">2</a>
//			// ......
//			// </div>
//			Elements pageLimitBar = doc.select("section[class='vue-public-list-page'] > div[class='page-limit']");
////			System.out.println("pageLimitBar="+pageLimitBar);
//			// 取得各個頁數的標籤"a[class='pageNum-form'][href='javascript:;'], 
//			// <a class="pageNum-form" href="javascript:;" data-first="30" data-total="38">2</a>
//			Elements nextPageSection = pageLimitBar.select("a[class='pageNum-form'][href='javascript:;']");
			Elements nextPageSection = testSeleniumRent591.getPageBarOfCurrentPage(URL);
////			System.out.println("nextPageSection="+nextPageSection);
			
			// List<String> -> eachAttr("data-first")得到各頁面的dataFirst值
			dataFirstList = nextPageSection.eachAttr("data-first");
			// [30] zero-base, 0-29為第一頁的資料
			// 若3頁以上-> [30, 60, ...]
			// index 0-29:第一頁; 30-59:第二頁
			System.out.println("dataFirstList="+dataFirstList);
			
			// List<String> -> eachAttr("data-total")得到各頁面的dataTotal值
			dataTotalList = nextPageSection.eachAttr("data-total");
			// [38] zero-base, 30-37為第二頁的資料
			// [30] -> [38] => 第二頁有8筆
			System.out.println("dataTotalList="+dataTotalList);
			// List<String> ->用eachText()得到最大數為最後頁數，即連接網頁-1(currentPage)次數
			// [2]
			pageList = nextPageSection.eachText();
			System.out.println("pageList="+pageList);
	
// 取得所有頁數裡的資料-ID			
			// for(<list.size()) List裡 get(i)取dataFirst值, 每次連接新網頁
			// connect("https...&firstRow={dataFirst}&totalRows={dataTotal}")
			// 取得下一個頁數的URL攜帶條件
			String nextPageUrl="";
			firstPageUrl=driver.getCurrentUrl();
			if(dataFirstList.size()==0) {
				// only one page
				
				
			} else {
				// > one page
				// 
				for(int loopPageNum=0; loopPageNum<dataFirstList.size(); loopPageNum++) {
					nextPageUrl = firstPageUrl+"&firstRow="+dataFirstList.get(loopPageNum)+"&totalRows="+dataTotalList.get(loopPageNum);
					System.out.println("nextPageUrl="+nextPageUrl);
					dataBindIdTempList = testSeleniumRent591.goToNextPageAndGetDataBindIds(nextPageUrl);
					// put list of resources of nextPage to the original List 
					// [12318860, 12255470, ..., 12347459] addAll [6813041, 12335890, ...]
					dataBindIdList.addAll(dataBindIdTempList);
					// [12318860, 12255470, ..., 12347459, 6813041, 12335890, ...]
					
//					List<String> itemListSubjectTemp = testSeleniumRent591.goToNextPageAndGetSubjectList(nextPageUrl);
//					itemListSubject.addAll(itemListSubjectTemp);
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
	
			
			System.out.println("dataBindIdList="+dataBindIdList);
			// length=38
			System.out.println("dataBindIdList.size()="+dataBindIdList.size());
			
			String testId="12335813";
			int gotIndexNum = testSeleniumRent591.findIndexFromTotalDataBindId(dataBindIdList, testId);
			System.out.println("gotIndexNum of '"+testId+"'="+gotIndexNum);
			
//			System.out.println("itemListSubject="+itemListSubject);
//			// length=
//			System.out.println("itemListSubject.size()="+itemListSubject.size());
			
			// 照片 img
			itemListImg = testSeleniumRent591.getListOfImgUrls(dataBindIdList, testId);
			System.out.println("itemListImg="+itemListImg+", size="+itemListImg.size());

			
			driver.close();
			driver.quit();
			
		} catch(Exception e) {
			e.getMessage();
		}
		
	}
	public Elements connectDirectedUrlAndGetItemSections(String url) throws Exception {
		System.out.println("enter connectDirectedUrlAndGetItemSections()");
//		driver.get(url);
//		Thread.sleep(3000);
//		String pageSource = driver.getPageSource();
//		Document doc = Jsoup.parse(pageSource);
		Document doc = connectDirectedUrlAndGetDoc(url);
//		eItemSections = doc.select("section");
		Elements eItemSections = getItemSectionsFromDirectedDoc(doc);
		return eItemSections;
	}
	Document currentPageDoc=null;
	public Document connectDirectedUrlAndGetDoc(String url) {
		System.out.println("enter connectDirectedUrlAndGetDoc()");
		driver.get(url);
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
			System.out.println("Exception in Thread is "+e);
		}
		String pageSource = driver.getPageSource();
		Document doc = Jsoup.parse(pageSource);
		currentPageDoc=doc;
		return doc;
	}
	public Elements getItemSectionsFromDirectedDoc(Document doc) {
		Elements eItemSections = doc.select("section");
		return eItemSections;
	}
	public List<String> getDataBindIdListOfOnePage(Elements eItemSections) {
		List<String> dataBindIdList = eItemSections.eachAttr("data-bind");
		return dataBindIdList;
	}
	// 取得所需資訊，裝到List內		
	// 須注意在List內的物件是位於哪個URL底下			
	public List<String> getSubjectListOfOnePage(Elements eItemSections) {
		// 主題 subject
		Elements eItemListSectionSubject = eItemSections.select("div[class='item-title']");
		// <div class="item-title">南青路(近南崁路二段)挑高.採光佳套房 <!----></div>
//					System.out.println("eItemListSectionSubject="+eItemListSectionSubject);
		List<String> itemListSubject = eItemListSectionSubject.eachText();
//		System.out.println("itemListSubject="+itemListSubject+", length="+itemListSubject.size());
		return itemListSubject;
	}
	public List<String> getSpaceListOfOnePage(Elements eItemSections) {
		// 坪數 space
		Elements eItemListSectionSpaces = eItemSections.select("ul[class='item-style'] > li:eq(1)");
		// <li>4坪</li>
//					System.out.println("eItemListSectionSpaces="+eItemListSectionSpaces);
		List<String> itemListSpace = eItemListSectionSpaces.eachText();
//		System.out.println("itemListSpace="+itemListSpace+", length="+itemListSpace.size());
		return itemListSpace;
	}
	public List<String> getAddressListOfOnePage(Elements eItemSections) {
		// 地址 address
		Elements eItemListSectionAddresses = eItemSections.select("div[class='item-area'] > span");
		// <span>蘆竹區-忠孝西路</span>
//					System.out.println("eItemListSectionAddresses="+eItemListSectionAddresses);
		List<String> itemListAddress = eItemListSectionAddresses.eachText();
//		System.out.println("itemListAddress="+itemListAddress+", length="+itemListAddress.size());
		return itemListAddress;
	}
	public List<String> getCostListOfOnePage(Elements eItemSections) {
		// 費用 cost
		Elements eItemListSectionCosts = eItemSections.select("div[class='item-price-text'] > span");
		// <span>7,000</span>
//					System.out.println("eItemListSectionCosts="+eItemListSectionCosts);
		List<String> itemListCost = eItemListSectionCosts.eachText();
//		System.out.println("itemListCost="+itemListCost+", length="+itemListCost.size());
		return itemListCost;
	}
	public Elements getPageBarOfCurrentPage(String url)  {
		// 先取得換頁數bar
		// <div class="page-limit">
		// 		<a class="pagePrev first">
		// 			<span>上一頁</span>
		// 		</a>
		// 		<span class="pageCurrent">1</span>
		// 		<a class="pageNum-form" href="javascript:;" data-first="30" data-total="38">2</a>
		// 		......
		// </div>
//		Document doc = connectDirectedUrlAndGetDoc(url);
		Elements pageLimitBar = currentPageDoc.select("section[class='vue-public-list-page'] > div[class='page-limit']");
//		System.out.println("pageLimitBar="+pageLimitBar);
		// 取得各個頁數的標籤"a[class='pageNum-form'][href='javascript:;'], 
		// <a class="pageNum-form" href="javascript:;" data-first="30" data-total="38">2</a>
		Elements nextPageSection = pageLimitBar.select("a[class='pageNum-form'][href='javascript:;']");
//		System.out.println("nextPageSection="+nextPageSection);
		return nextPageSection;
	}
	public List<String> goToNextPageAndGetDataBindIds(String nextPageUrl) throws Exception{
		
//		driver.get(nextPageUrl);
//		
//		Thread.sleep(3000);
//		pageSource = driver.getPageSource();
//		
//		// 原本的pageSource直接被覆蓋
//		doc = Jsoup.parse(pageSource);
//		eItemSections = doc.select("section");
//		System.out.println("eItemSections="+eItemSections);
		
		eItemSections = connectDirectedUrlAndGetItemSections(nextPageUrl);
		
//		String dataBind12235149 = eItemSections.attr("data-bind");
		// get all rent id that at one page (8)
//		dataBindIdTempList = eItemSections.eachAttr("data-bind");
		List<String> dataBindIdTempList = getDataBindIdListOfOnePage(eItemSections);
		// [6813041, 12335890, ...]
		System.out.println("current dataBindIdTempList="+dataBindIdTempList);
		// length=8
		System.out.println("current dataBindIdTempList.size()="+dataBindIdTempList.size());
		return dataBindIdTempList;
	}
	public List<String> goToNextPageAndGetSubjectList(String nextPageUrl) throws Exception{
		
		Elements eItemSections = connectDirectedUrlAndGetItemSections(nextPageUrl);
		List<String> itemListSubjectTemp = getSubjectListOfOnePage(eItemSections);
		System.out.println("current itemListSubjectTemp="+itemListSubjectTemp);
		// length=8
		System.out.println("current itemListSubjectTemp.size()="+itemListSubjectTemp.size());
		return itemListSubjectTemp;
	}
	public List<String> getListOfImgUrls(List<String> dataBindIdList, String dataBindId) {
		// 先檢查是否有找到此Id的index值
		Integer indexOfId = findIndexFromTotalDataBindId(dataBindIdList, dataBindId);
		System.out.println("indexOfId="+indexOfId);
		// 沒找到的話，顯示值為index外的值。例如size:33, 資料0-32, 皆沒找到, indexOfId=33

		int findPageNumInIndex=0; // 0-> page:2
		if(indexOfId==dataBindIdList.size()) {
			// 無此Id in dataBindIdList
		} else {
			// 須注意所蒐尋的id與連結網址的關聯性
			// 先找出id位於List的index值，若在其他頁數(index > dataFirstList[0])，須連接不同網址來得到圖片
			// dataFirstList-> [30, 60, ...]
			// pageList-> [2, 3]
			// 若indexOfId為30(含)以上，Id位置在第二個網頁連結
			
			for(int loopDataFirstListNum=0; loopDataFirstListNum<dataFirstList.size(); loopDataFirstListNum++) {
				if(indexOfId >= Integer.parseInt(dataFirstList.get(0))) {
					// indexOfId>=dataFirstList.(0)=> 30 -> page=pageList[0]=> 2
					// indexOfId>=dataFirstList.(1)=> 60 -> page=pageList[1]=> 3
					findPageNumInIndex=loopDataFirstListNum+1;
				} else {
					// page=1
					findPageNumInIndex=-1;
				}
			}
			// dataFirstList-> [30]
			// 若沒比對成功，findPageNumInIndex=1
		}
		// pageList-> [2, 3]
		// findPageNumInIndex=0 -> dataFirstList.(0)=>30 -> 頁數為第二頁
		System.out.println("current findPageNumInIndex="+findPageNumInIndex);
		
		String whichPageUrl="";
		// 1. 找到第二頁連結String內容
		// ID在第二頁時-> firstPageUrl+"&firstRow="+dataFirstList.get(0)+"&totalRows="+dataTotalList.get(0)
		// ID在第三頁時-> firstPageUrl+"&firstRow="+dataFirstList.get(1)+"&totalRows="+dataTotalList.get(1)
		if(findPageNumInIndex>=0) {
			// ID位於第二頁之後
			whichPageUrl = firstPageUrl+"&firstRow="+dataFirstList.get(findPageNumInIndex)+"&totalRows="+dataTotalList.get(findPageNumInIndex);
		} else {
			// ID位於第一頁
			whichPageUrl=URL;
		}
		System.out.println("whichPageUrl="+whichPageUrl);
		// 2. connect 第二頁連結，取得doc
		Document doc = connectDirectedUrlAndGetDoc(whichPageUrl);
		Elements directedIdSection = doc.select("section[data-bind="+dataBindId+"]");
//		System.out.println("directedIdSection=\n"+directedIdSection);
		// 照片 img
		// <ul class="carousel-list" style="left: 0px; width: 3060px;">
		// 		<li>
		//   		<img data-original="https://img1.591.com.tw/house/2021/11/30/163825287516577206.jpg!510x400.jpg" 
		//		  					src="https://img1.591.com.tw/house/2021/11/30/163825287516577206.jpg!510x400.jpg" 
		// 							alt="南崁忠孝西路電梯套房-全含租限女生" class="obsever-lazyimg">
		// 		</li>
		// 		...
		// </ul>
		Elements eItemListSectionImgs = directedIdSection.select("ul[class='carousel-list'] img");
//		System.out.println("eItemListSectionImgs="+eItemListSectionImgs);
		List<String> imgUrls = eItemListSectionImgs.eachAttr("data-original");
//		System.out.println("imgUrls="+imgUrls);
		return imgUrls; 
	}
	public Integer findIndexFromTotalDataBindId(List<String> dataBindIdList, String dataBindId) {
		int loopNum=0;
		for(loopNum=0; loopNum<dataBindIdList.size(); loopNum++) {
			if(dataBindIdList.get(loopNum).equals(dataBindId)){
				break;
			}
		}
		return loopNum==0? 0: loopNum;
	}
}
