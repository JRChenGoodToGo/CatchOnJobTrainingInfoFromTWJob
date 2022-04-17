package htmlTag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.chrome.ChromeDriver;

import htmlTagDao.Rent591ItemDao;
import htmlTagDao.Rent591ItemsDao;


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
//**jsoup**
//Selector-> https://jsoup.org/apidocs/org/jsoup/select/Selector.html
public class TestSeleniumRent591 {
	private static WebDriver driver;
//	private static String URL="https://rent.591.com.tw/?region=6&section=79,78&searchtype=1&kind=3&showMore=1&rentprice=5000,7000";
	private static String firstPageUrl="https://rent.591.com.tw/?region=6&section=79,78&searchtype=1&kind=3&showMore=1";
//							  "https://rent.591.com.tw/?region=6&section=79,78&searchtype=1&kind=3&showMore=1&rentprice=5000,7000&firstRow=30&totalRows=39"
//							  "https://rent.591.com.tw/?region=6&section=79,78&searchtype=1&kind=3&showMore=1"
//							  "https://rent.591.com.tw/?region=6&section=79,78&searchtype=1&kind=3&showMore=1&firstRow=30&totalRows=72"
//							  "https://rent.591.com.tw/?region=6&section=79,78&searchtype=1&kind=3&showMore=1&firstRow=60&totalRows=72"
	private static String PATH="D:\\JavaFramePrac\\eclipse-workspace\\test\\src\\test\\java\\htmlTag";
	private static String FILENAME="Rent591ItemsDao.txt";
	
//	private static String URL="http://example.com";
	private static String pageSource;
	private static Document doc;
	private static Elements eItemSections;
	
	private static List<String> dataFirstList, dataTotalList,pageList, 
								dataBindIdList, 
								itemListSubject, itemListSpace, 
								itemListAddress, itemListCost;
	private static List<String> itemListImg;
	private static TestSeleniumRent591 testSeleniumRent591;
	private static Rent591ItemsDao rent591ItemsDao; 
	private static Rent591ItemDao rent591ItemDao; 
//	private static String firstPageUrl;
	
	public TestSeleniumRent591() {
	}
	public TestSeleniumRent591(String driverType, String driverPath) {
//		testSeleniumRent591= new TestSeleniumRent591();
		String propertySet = System.setProperty(driverType,driverPath);
		driver = new ChromeDriver();
		rent591ItemsDao = new Rent591ItemsDao();
		rent591ItemDao = new Rent591ItemDao();
	}
	public static void main(String[] args) {
//use JBrowserDriver failed
//		JBrowserDriver driver = new JBrowserDriver(Settings.builder().timezone(Timezone.ASIA_SHANGHAI).build());
//		driver.get(URL);
//		System.out.println(driver.getStatusCode());
//		System.out.println(driver.getPageSource());
//		driver.quit();

		
//setProperty of chrome driver		
//		String propertySet = System.setProperty("webdriver.chrome.driver","D:\\JavaFramePrac\\myPlugins\\chromedriver.exe");
//		System.out.println("propertySet="+propertySet);
//		driver = new ChromeDriver();
		testSeleniumRent591 = new TestSeleniumRent591("webdriver.chrome.driver","D:\\JavaFramePrac\\myPlugins\\chromedriver.exe");
		
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
			eItemSections = testSeleniumRent591.connectDirectedUrlAndGetItemSections(firstPageUrl);
			
// get all required info at first page(MAX:30)
//			String dataBind12235149 = eItemSections.attr("data-bind");
			// 唯一 Id
//			dataBindIdList = eItemSections.eachAttr("data-bind");
			dataBindIdList = testSeleniumRent591.getDataBindIdListOfOnePage(eItemSections);
			// [12318860, 12255470, ..., 12347459]
//			System.out.println("dataBindIdList="+dataBindIdList+", size="+dataBindIdList.size());
			// length=30
			
//// 取得所需資訊，裝到List內		
//// 須注意在List內的物件是位於哪個URL底下			
//			// 主題 subject
//			Elements eItemListSectionSubject = eItemSections.select("div[class='item-title']");
//			// <div class="item-title">南青路(近南崁路二段)挑高.採光佳套房 <!----></div>
////			System.out.println("eItemListSectionSubject="+eItemListSectionSubject);
//			itemListSubject = eItemListSectionSubject.eachText();
			itemListSubject = testSeleniumRent591.getSubjectListOfOnePage(eItemSections);
//			System.out.println("itemListSubject="+itemListSubject+", size="+itemListSubject.size());
//			
//			// 坪數 space
//			Elements eItemListSectionSpaces = eItemSections.select("ul[class='item-style'] > li:eq(1)");
//			// <li>4坪</li>
////			System.out.println("eItemListSectionSpaces="+eItemListSectionSpaces);
//			itemListSpace = eItemListSectionSpaces.eachText();
			itemListSpace = testSeleniumRent591.getSpaceListOfOnePage(eItemSections);
//			System.out.println("itemListSpace="+itemListSpace+", size="+itemListSpace.size());
//			
//			// 地址 address
//			Elements eItemListSectionAddresses = eItemSections.select("div[class='item-area'] > span");
//			// <span>蘆竹區-忠孝西路</span>
////			System.out.println("eItemListSectionAddresses="+eItemListSectionAddresses);
//			itemListAddress = eItemListSectionAddresses.eachText();
			itemListAddress = testSeleniumRent591.getAddressListOfOnePage(eItemSections);
//			System.out.println("itemListAddress="+itemListAddress+", size="+itemListAddress.size());
//			
//			// 費用 cost
//			Elements eItemListSectionCosts = eItemSections.select("div[class='item-price-text'] > span");
//			// <span>7,000</span>
////			System.out.println("eItemListSectionCosts="+eItemListSectionCosts);
//			itemListCost = eItemListSectionCosts.eachText();
			itemListCost = testSeleniumRent591.getCostListOfOnePage(eItemSections);
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
			Elements nextPageSection = testSeleniumRent591.getPageBarOfCurrentPage(firstPageUrl);
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
//			firstPageUrl=driver.getCurrentUrl();
			if(dataFirstList.size()==0) {
				// only one page
				
			} else {
				// > one page
				// go to next page and get tempList to add
				for(int loopPageNum=0; loopPageNum<dataFirstList.size(); loopPageNum++) {
					nextPageUrl = firstPageUrl+"&firstRow="+dataFirstList.get(loopPageNum)+"&totalRows="+dataTotalList.get(loopPageNum);
//					System.out.println("nextPageUrl="+nextPageUrl);
					List<String> dataBindIdTempList = testSeleniumRent591.goToNextPageAndGetDataBindIds(nextPageUrl);
					// put list of resources of nextPage to the original List 
					// [12318860, 12255470, ..., 12347459] addAll [6813041, 12335890, ...]
					dataBindIdList.addAll(dataBindIdTempList);
					// [12318860, 12255470, ..., 12347459, 6813041, 12335890, ...]
					
//					List<String> itemListSubjectTemp = testSeleniumRent591.goToNextPageAndGetSubjectList(nextPageUrl);
					List<String> itemListSubjectTemp = testSeleniumRent591.getSubjectListOfOnePage(eItemSections);
					itemListSubject.addAll(itemListSubjectTemp);
					
					List<String> itemListSpaceTemp = testSeleniumRent591.getSpaceListOfOnePage(eItemSections);
					itemListSpace.addAll(itemListSpaceTemp);
					
					List<String> itemListAddressTemp = testSeleniumRent591.getAddressListOfOnePage(eItemSections);
					itemListAddress.addAll(itemListAddressTemp);
					
					List<String> itemListCostTemp = testSeleniumRent591.getCostListOfOnePage(eItemSections);
					itemListCost.addAll(itemListCostTemp);
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
			
			
			System.out.println("dataBindIdList="+dataBindIdList+"\n, size()="+dataBindIdList.size());
			System.out.println("itemListSubject="+itemListSubject+"\n, size()="+itemListSubject.size());
			System.out.println("itemListSpace="+itemListSpace+"\n, size()="+itemListSpace.size());
			System.out.println("itemListAddress="+itemListAddress+"\n, size()="+itemListAddress.size());
			System.out.println("itemListCost="+itemListCost+"\n, size()="+itemListCost.size());

			
//	put list of Imgs by each dataBindId to the List
			List<List<String>> itemListImgList = new ArrayList<List<String>>();
			for(int loopDataBindIdNum=0; loopDataBindIdNum<dataBindIdList.size(); loopDataBindIdNum++) {
				itemListImg = testSeleniumRent591.getListOfImgUrls(dataBindIdList, dataBindIdList.get(loopDataBindIdNum));
				itemListImgList.add(itemListImg);
			}
			System.out.println("itemListImgList.get(0)="+itemListImgList.get(0)+"\n, size="+itemListImgList.size());
			// test getListOfImgUrls()
//			List<String> testList = testSeleniumRent591.getListOfImgUrls(dataBindIdList, dataBindIdList.get(0));
//			System.out.println("testList="+testList);
			
			rent591ItemsDao.setDataBindIdList(dataBindIdList);
			rent591ItemsDao.setItemSubjectList(itemListSubject);
			rent591ItemsDao.setItemSpaceList(itemListSpace);
			rent591ItemsDao.setItemAddressList(itemListAddress);
			rent591ItemsDao.setItemCostList(itemListCost);
			rent591ItemsDao.setItemListImgList(itemListImgList);
			System.out.println("rent591ItemsDao=>\n"+rent591ItemsDao);
			JSONObject jsonObj = new JSONObject(rent591ItemsDao);
			
// 測試給標題找到所有資訊並儲存狀態
//			String testSubject="🎀大園🎀分租套房🎀不收服務費";// [@青埔秒殺分租套房4間任選@ 優選好屋, 💛大套房-近華泰名品城/捷運站10分鐘]
//		// test findIndexOfObjectFromObjectList()
//			int gotIndexNum = testSeleniumRent591.findIndexOfObjectFromObjectList(itemListSubject, testSubject);
//			System.out.println("gotIndexNum of '"+testSubject+"'="+gotIndexNum);
//			System.out.println("index:"+gotIndexNum+" from dataBindIdList="+dataBindIdList.get(gotIndexNum));
//			//				-->	index:11 from dataBindIdList=12384618
//			System.out.println("index:"+gotIndexNum+" from itemListSubject="+itemListSubject.get(gotIndexNum));
//			//				-->	index:11 from itemListSubject=🎀大園🎀分租套房🎀不收服務費
//			System.out.println("index:"+gotIndexNum+" from itemListSpace="+itemListSpace.get(gotIndexNum));
//			//				-->	index:11 from itemListSpace=5坪
//			System.out.println("index:"+gotIndexNum+" from itemListAddress="+itemListAddress.get(gotIndexNum));
//			//				-->	index:11 from itemListAddress=大園區-埔心街
//			System.out.println("index:"+gotIndexNum+" from itemListCost="+itemListCost.get(gotIndexNum));
//			//				-->	index:11 from itemListCost=5,000
//		// 照片 img
//		// test getListOfImgUrls()
//			itemListImg = testSeleniumRent591.getListOfImgUrls(dataBindIdList, dataBindIdList.get(gotIndexNum));
//			System.out.println("itemListImg="+itemListImg+"\n, size="+itemListImg.size());
//
//			rent591ItemDao.setDataBindId(dataBindIdList.get(gotIndexNum));
//			rent591ItemDao.setItemSubject(itemListSubject.get(gotIndexNum));
//			rent591ItemDao.setItemSpace(itemListSpace.get(gotIndexNum));
//			rent591ItemDao.setItemAddress(itemListAddress.get(gotIndexNum));
//			rent591ItemDao.setItemCost(itemListCost.get(gotIndexNum));
//			rent591ItemDao.setItemListImg(itemListImg);
	// replace code above by method
//			rent591ItemDao = testSeleniumRent591.getDirectedItemDaoBySubject(
//					"🎀大園🎀分租套房🎀不收服務費", dataBindIdList, 
//					itemListSubject, itemListSpace, 
//					itemListAddress, itemListCost);
//			System.out.println("rent591ItemDao=>\n"+rent591ItemDao);
	// replace method above by calling Rent591ItemsDao
			rent591ItemDao = testSeleniumRent591.getDirectedItemDaoBySubject(
					"🎀大園🎀分租套房🎀不收服務費", rent591ItemsDao.getDataBindIdList(), 
					rent591ItemsDao.getItemSubjectList(), rent591ItemsDao.getItemSpaceList(), 
					rent591ItemsDao.getItemAddressList(), rent591ItemsDao.getItemCostList());
//			System.out.println("rent591ItemDao=>\n"+rent591ItemDao);

			// ObjectStream to store Dao
			testSeleniumRent591.writeInfosInDirectedMutualMethod(new File(PATH), rent591ItemsDao, "object");
			Object readObject = testSeleniumRent591.readInfosUnderObjectInputStream(new File(PATH));
			if(readObject instanceof Rent591ItemsDao) {
				Rent591ItemsDao showIdentity = (Rent591ItemsDao) readObject;
				System.out.println("showIdentity.getItemListImgList().get(0)="+showIdentity.getItemListImgList().get(0));
			} else if(readObject instanceof Rent591ItemDao) {
				Rent591ItemDao showIdentity = (Rent591ItemDao) readObject;
				System.out.println("showIdentity="+showIdentity);
			}
			
			driver.close();
			driver.quit();
			
		} catch(Exception e) {
			e.getMessage();
		}
		
	}
	Document currentPageDoc=null;
	String currentPageUrl=null;
	public Document connectDirectedUrlAndGetDoc(String url) {
		System.out.println("enter connectDirectedUrlAndGetDoc(String url)");
		driver.get(url);
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
			System.out.println("Exception in Thread is "+e);
		}
		String pageSource = driver.getPageSource();
		Document doc = Jsoup.parse(pageSource);
		currentPageDoc=doc; currentPageUrl=url;
		System.out.println("currentPageDoc is from url="+url);
		return doc;
	}
	public Elements getItemSectionsFromDirectedDoc(Document doc) {
		System.out.println("enter getItemSectionsFromDirectedDoc(Document doc)");
		Elements eItemSections = doc.select("section");
		return eItemSections;
	}
	Elements currentPageItemSections=null;
	public Elements connectDirectedUrlAndGetItemSections(String url) throws Exception {
		System.out.println("enter connectDirectedUrlAndGetItemSections(String url)");
//		driver.get(url);
//		Thread.sleep(3000);
//		String pageSource = driver.getPageSource();
//		Document doc = Jsoup.parse(pageSource);
		Document doc = connectDirectedUrlAndGetDoc(url);
//		eItemSections = doc.select("section");
		Elements eItemSections = getItemSectionsFromDirectedDoc(doc);
		currentPageItemSections=eItemSections;
		return eItemSections;
	}
	public List<String> getDataBindIdListOfOnePage(Elements eItemSections) {
		List<String> dataBindIdList = eItemSections.eachAttr("data-bind");
		return dataBindIdList;
	}
// 取得所需資訊，裝到List內		
	// 須注意在List內的物件是位於哪個URL底下			
	public List<String> getSubjectListOfOnePage(Elements eItemSections) {
		Elements eItemListSectionSubject=null;
		// use given itemSections
		// 主題 subject
		eItemListSectionSubject = eItemSections.select("div[class='item-title']");
		// <div class="item-title">南青路(近南崁路二段)挑高.採光佳套房 <!----></div>
//			System.out.println("eItemListSectionSubject="+eItemListSectionSubject);
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
	// could omit this method if already connect to next page
	public List<String> goToNextPageAndGetSubjectList(String nextPageUrl) throws Exception{
		// get total xxList - method1
		//if(nextPageUrl.equals(currentPageUrl)) {System.out.println("yyyessssssss, it's the same url!!!!!!!!!!!");}
		// if same url, getItemSection from currentPageDoc with getItemSectionsFromDirectedDoc()
		// have corresponding method for each xxItem
		
		// get total xxList - method2
		// if(nextPageUrl.equals(currentPageUrl){}
//		// if(!currentPageItemSections.equals(null)) {System.out.println("yyyessssssss, it's not null!!!!!!!!!!!");};
		// if url same with currentPageUrl
		//  and if currentPageItemSections not null, get xxList by currentPageItemSections
		// could call getSubjectListOfOnePage() directed without this method? --> yes
		Elements eItemSections=null;
		List<String> itemListSubjectTemp=null;
		if(nextPageUrl.equals(currentPageUrl)) {
			// same url, same doc
			if(!currentPageItemSections.equals(null)) {
				// same itemSection, directly call method that use current itemSection
				itemListSubjectTemp = getSubjectListOfOnePage(currentPageItemSections);
			} else {
				// only get doc without get itemSection, so call to get itemSection
				eItemSections = getItemSectionsFromDirectedDoc(currentPageDoc);
				itemListSubjectTemp = getSubjectListOfOnePage(eItemSections);
			}
		} else {
			// not the same url, initially connect url and get doc
			eItemSections = connectDirectedUrlAndGetItemSections(nextPageUrl);
			itemListSubjectTemp = getSubjectListOfOnePage(eItemSections);
		}
		
		System.out.println("current itemListSubjectTemp="+itemListSubjectTemp);
		// length=8
		System.out.println("current itemListSubjectTemp.size()="+itemListSubjectTemp.size());
		return itemListSubjectTemp;
	}
	public String getWhichPageUrl(List<String> dataBindIdList, String dataBindId) {
		// 先檢查是否有找到此Id的index值
		Integer indexOfId = findIndexOfObjectFromObjectList(dataBindIdList, dataBindId);
		System.out.println("indexOfId:"+dataBindId+"="+indexOfId);
		// 沒找到的話，顯示值為index外的值。例如size:33, 資料0-32, 皆沒找到, indexOfId=33

		int findPageNumInIndex=-1; // 0-> page:2; -1-> page:1
//		System.out.println("dataFirstList.size()="+dataFirstList.size()); // 若只有1頁，size=0
		if(indexOfId==dataBindIdList.size()) {
			// 無此Id in dataBindIdList
			findPageNumInIndex=-2;
		} else if(indexOfId<dataBindIdList.size() && dataFirstList.size()==0) {
			// 只有1頁
			findPageNumInIndex=-1;
		} else {
			// 有2頁以上
			// 須注意所蒐尋的id與連結網址的關聯性
			// 先找出id位於List的index值，若在其他頁數(index > dataFirstList[0])，須連接不同網址來得到圖片
			// dataFirstList-> [30, 60, ...]
			// pageList-> [2, 3]
			// 若indexOfId為30(含)以上，Id位置在第二個網頁連結
			for(int loopDataFirstListNum=0; loopDataFirstListNum<dataFirstList.size(); loopDataFirstListNum++) {
				if(indexOfId >= Integer.parseInt(dataFirstList.get(loopDataFirstListNum))) {
					// indexOfId>=dataFirstList.(0)=> 30 -> page=pageList[0]=> 2
					// indexOfId>=dataFirstList.(1)=> 60 -> page=pageList[1]=> 3
					findPageNumInIndex=findPageNumInIndex+1;
				} 
			}
		}
		// pageList-> [2, 3]
		// findPageNumInIndex=0 -> dataFirstList.(0)=>30 -> 頁數為第二頁
		
		String whichPageUrl="";
//		System.err.println("whichPageUrl=\"\"="+whichPageUrl.isEmpty());// true
		// 1. 找到第二頁連結String內容
		// ID在第二頁時-> firstPageUrl+"&firstRow="+dataFirstList.get(0)+"&totalRows="+dataTotalList.get(0)
		// ID在第三頁時-> firstPageUrl+"&firstRow="+dataFirstList.get(1)+"&totalRows="+dataTotalList.get(1)
		if(findPageNumInIndex>=0) {
			// ID位於第二頁之後
			System.out.println("current findPageNumInIndex="+findPageNumInIndex+", page="+pageList.get(findPageNumInIndex));
			whichPageUrl = firstPageUrl+"&firstRow="+dataFirstList.get(findPageNumInIndex)+"&totalRows="+dataTotalList.get(findPageNumInIndex);
		} else if(findPageNumInIndex == -1) {
			// ID位於第一頁
			whichPageUrl=firstPageUrl;
			System.out.println("current findPageNumInIndex="+findPageNumInIndex+", page=1");
		} else {
			System.out.println("no such id");
		}
		return whichPageUrl;
	}
	// https://img1.591.com.tw/house/2022/03/23/164803576654140403.jpg!510x400.jpg
	// https://rent.591.com.tw/home/ + id
	public List<String> getListOfImgUrls(List<String> dataBindIdList, String dataBindId) {
		String whichPageUrl = getWhichPageUrl(dataBindIdList, dataBindId);
//		System.out.println("whichPageUrl="+whichPageUrl);
		
		if(!whichPageUrl.isEmpty()) {
			// 2. connect 第二頁連結，取得doc
			Document doc;
			Elements directedIdSection;
			if(whichPageUrl.equals(currentPageUrl)) {
				// 與目前連接到的網頁連結相同
				doc = currentPageDoc;
				directedIdSection = doc.select("section[data-bind="+dataBindId+"]");
			} else {
				// 與目前連接到的網頁連結不同
				// 重新連結
				doc = connectDirectedUrlAndGetDoc(whichPageUrl);
				directedIdSection = doc.select("section[data-bind="+dataBindId+"]");
			}
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
		} else {
			return null;
		}
	}
	public Integer findIndexOfObjectFromObjectList(List<String> dataBindIdList, String dataBindId) {
		int loopNum=0;
		for(loopNum=0; loopNum<dataBindIdList.size(); loopNum++) {
			if(dataBindIdList.get(loopNum).equals(dataBindId)){
				break;
			}
		}
		return loopNum==0? 0: loopNum;
	}
	public Rent591ItemDao getDirectedItemDaoBySubject(String subject, 
										List<String> dataBindIdList, 
										List<String> itemListSubject, 
										List<String> itemListSpace, 
										List<String> itemListAddress, 
										List<String> itemListCost) {
		System.out.println("enter getDirectedItemDaoBySubject(String subject, ....., List<String> itemListCost)");
		// 測試給標題找到所有資訊並儲存狀態
//		String testSubject="🎀大園🎀分租套房🎀不收服務費";// [@青埔秒殺分租套房4間任選@ 優選好屋, 💛大套房-近華泰名品城/捷運站10分鐘]
	// test findIndexOfObjectFromObjectList()
		if(subject!=null && !subject.isEmpty() && !itemListSubject.isEmpty()) {
			int gotIndexNum = findIndexOfObjectFromObjectList(itemListSubject, subject);
			if(gotIndexNum < itemListSubject.size()) {
				// have the right number, else get the same number with size()
				System.out.println("gotIndexNum of '"+subject+"'="+gotIndexNum);
				System.out.println("index:"+gotIndexNum+" from dataBindIdList="+dataBindIdList.get(gotIndexNum));
				//				-->	index:11 from dataBindIdList=12384618
				System.out.println("index:"+gotIndexNum+" from itemListSubject="+itemListSubject.get(gotIndexNum));
				//				-->	index:11 from itemListSubject=🎀大園🎀分租套房🎀不收服務費
				System.out.println("index:"+gotIndexNum+" from itemListSpace="+itemListSpace.get(gotIndexNum));
				//				-->	index:11 from itemListSpace=5坪
				System.out.println("index:"+gotIndexNum+" from itemListAddress="+itemListAddress.get(gotIndexNum));
				//				-->	index:11 from itemListAddress=大園區-埔心街
				System.out.println("index:"+gotIndexNum+" from itemListCost="+itemListCost.get(gotIndexNum));
				//				-->	index:11 from itemListCost=5,000
				// 照片 img
				// test getListOfImgUrls()
				itemListImg = getListOfImgUrls(dataBindIdList, dataBindIdList.get(gotIndexNum));
				System.out.println("itemListImg="+itemListImg+"\n, size="+itemListImg.size());
				
				//		rent591ItemDao = new Rent591ItemDao();
				rent591ItemDao.setDataBindId(dataBindIdList.get(gotIndexNum));
				rent591ItemDao.setItemSubject(itemListSubject.get(gotIndexNum));
				rent591ItemDao.setItemSpace(itemListSpace.get(gotIndexNum));
				rent591ItemDao.setItemAddress(itemListAddress.get(gotIndexNum));
				rent591ItemDao.setItemCost(itemListCost.get(gotIndexNum));
				rent591ItemDao.setItemListImg(itemListImg);
				return rent591ItemDao;
			}
		}
		return null;
	}
	public void writeInfosInDirectedMutualMethod(File filePath, Object dao, String writeByObjectOrString) throws Exception {
// prepare file to write data		
//		File file = new File(PATH);
//		System.out.println("path="+file.getAbsolutePath());
		if(filePath.isDirectory()) {
			System.out.println("start write to "+filePath.getAbsolutePath()+"\\");
//https://mkyong.com/java/how-to-read-and-write-java-object-to-a-file/
			// D:\xxx\xxx + \xxx.txt
			FileOutputStream fos = null;
			if(writeByObjectOrString.toLowerCase().equals("string")) {
// write object to file in normal txt				
				fos = new FileOutputStream(filePath+"\\"+FILENAME, true);
				OutputStreamWriter w = new OutputStreamWriter(fos);
				if(dao instanceof JSONObject) {
					System.out.println("dao instanceof JSONObject");
					w.write("\n"+((JSONObject)dao).toString());
				} else {
					System.out.println("dao instanceof not JSONObject");
					w.write(dao.toString());
				}
				w.flush();
				w.close();
			} else if(writeByObjectOrString.toLowerCase().equals("object")) {
// write object to file in ObjectOutputStream
				fos = new FileOutputStream(filePath+"\\"+FILENAME);
				ObjectOutputStream w = new ObjectOutputStream(fos);
				if(dao instanceof JSONObject) {
					System.out.println("could not Serialize JSONObject");
				} else {
					w.writeObject(dao);
				}
				w.flush();
				w.close();
			}
			
			System.out.println("write to file:"+FILENAME+" success!");

			fos.flush();
			fos.close();
		} else {
			System.out.println("no suchh dir, create one");
			if(filePath.mkdirs()) {
				System.out.println("create path:"+filePath+" success");
			}
		}
	}
	public Object readInfosUnderObjectInputStream(File filePath) throws Exception{
		// objectInputStream
		Rent591ItemsDao rent591ItemsDao=null;
		Rent591ItemDao rent591ItemDao=null;
//		File file = new File(PATH);
		Object object=null;
		if(filePath.isDirectory()) {
			FileInputStream fis = new FileInputStream(filePath+"\\"+FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			object = ois.readObject();
			if(object instanceof Rent591ItemsDao) {
				rent591ItemsDao = (Rent591ItemsDao) object;
			} else if(object instanceof Rent591ItemDao) {
				rent591ItemDao = (Rent591ItemDao) object;
			}

			ois.close();
			fis.close();
		}
		return (object instanceof Rent591ItemsDao)? rent591ItemsDao: rent591ItemDao;
	}
}
