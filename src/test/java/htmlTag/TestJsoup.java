package htmlTag;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import htmlTagDao.Rent591ItemsDao;

//https://jsoup.org/cookbook/extracting-data/attributes-text-html
// https://zenscrape.com/java-web-scraping-comprehensive-tutorial/

// 產業人才投資方案的介面
public class TestJsoup {
	private static String URL="https://ojt.wda.gov.tw/ClassSearch/Detail?OCID=142810&plantype=1";
//	private static String URL="https://ojt.wda.gov.tw/ClassSearch/Detail?OCID=142446&plantype=1";
	private static String PATH="D:\\JavaFramePrac\\eclipse-workspace\\test\\src\\test\\java\\htmlTag";
	private static String FILENAME="test4.txt";
	
	private static Map<String, String> gotMapListOfThTd;
	private static java.util.List<Entry<String, String>> listOfEntryOfThTd;
	public static void main(String[] args) {
		Connection conn = Jsoup.connect(URL);
		System.out.println("conn="+conn);

		try {
			// get all raw page source
			Document doc = conn.get();
//			System.out.println("doc="+doc);
			
			TestJsoup testJsoup = new TestJsoup();
			
			// get table rows of course contents
			Elements courseContentsTable = testJsoup.getAllTableRowOfSpecificIndexOfTable(doc, 1);	
			gotMapListOfThTd = testJsoup.putThTdOfSpecificTableRowToMap(courseContentsTable, 3);
			System.out.println("gotMapListOfThTd="+gotMapListOfThTd);
//			gotMapListOfThTd.clear();
			System.out.println("--------------------------");
			
			// get table rows of county
			gotMapListOfThTd = testJsoup.putThTdOfSpecificTableRowToMap(courseContentsTable, 6);
			System.out.println("gotMapListOfThTd="+gotMapListOfThTd);
			System.out.println("--------------------------");
			
			// cost
			gotMapListOfThTd = testJsoup.putThTdOfSpecificTableRowToMap(courseContentsTable, 11);
			System.out.println("gotMapListOfThTd="+gotMapListOfThTd);
			System.out.println("--------------------------");
			
			// register date span
			gotMapListOfThTd = testJsoup.putThTdOfSpecificTableRowToMap(courseContentsTable, 13);
			System.out.println("gotMapListOfThTd="+gotMapListOfThTd);
			System.out.println("--------------------------");
			
			// register start/end
			gotMapListOfThTd = testJsoup.putThTdOfSpecificTableRowToMap(courseContentsTable, 14);
			System.out.println("gotMapListOfThTd="+gotMapListOfThTd);
			System.out.println("--------------------------");
			
			// training date
			gotMapListOfThTd = testJsoup.putThTdOfSpecificTableRowToMap(courseContentsTable, 15);
			System.out.println("gotMapListOfThTd="+gotMapListOfThTd);
			System.out.println("total mapList size="+gotMapListOfThTd.size());
			System.out.println("--------------------------");
			
			JSONObject jsonObj = testJsoup.loopToGetRequiredInfosUnderCombinedKV(courseContentsTable, gotMapListOfThTd, "kv");
			jsonObj.put("報名網址", URL);
			System.out.println("jsonObj="+jsonObj);
			
//			Iterator<String> jsonObjIter = jsonObj.keys();
//			Set<String> jsonObjSet = jsonObj.keySet();
//			JSONArray jsonObjArr = jsonObj.names();
//			System.out.println("jsonObjIter"+jsonObjIter); // java.util.HashMap$KeyIterator@27447a52
//			System.out.println("jsonObjSet"+jsonObjSet); // [訓練費用, 縣 市 別, ...]
//			System.out.println("jsonObjArr"+jsonObjArr); // ["訓練費用","縣 市 別",...]

			
//// prepare to write in normal txt 			
//			TrainingSubjectBrowse trainingSubjectBrowse = new TrainingSubjectBrowse(jsonObj);
////			System.out.println("trainingSubjectBrowse="+trainingSubjectBrowse.getJsonObj());
////			System.out.println("trainingSubjectBrowse.toString="+trainingSubjectBrowse.toString());
//			
//// prepare write to file in objectOutputStream
////			TrainingSubjectBrowse trainingSubjectBrowse = new TrainingSubjectBrowse();
////			trainingSubjectBrowse.setSubject(jsonObj.getString("課程名稱"));
////			trainingSubjectBrowse.setTrainingStartEnd(jsonObj.getString("預定訓練起訖日期"));
////			trainingSubjectBrowse.setTrainingDateSpan(jsonObj.getString("上課時間"));
////			trainingSubjectBrowse.setLocationTraining(jsonObj.getString("縣 市 別"));
////			trainingSubjectBrowse.setTotalTrainingHours(jsonObj.getString("訓練時數"));
////			trainingSubjectBrowse.setCost(jsonObj.getString("訓練費用"));
////			trainingSubjectBrowse.setRegisterStart(jsonObj.getString("報名日期 ").split("[~]", 2)[0]);
////			trainingSubjectBrowse.setRegisterEnd(jsonObj.getString("報名日期 ").split("[~]", 2)[1].split("[(]")[0]);
////			trainingSubjectBrowse.setRegisterUrl(jsonObj.getString("報名網址"));
////
//// write data to file			
//			File file = new File(PATH);
//			System.out.println("path="+file.getAbsolutePath());
//			if(file.isDirectory()) {
//				System.out.println("start write to "+file.getAbsolutePath());
//
////https://mkyong.com/java/how-to-read-and-write-java-object-to-a-file/
//				// D:\xxx\xxx + \xxx.txt
//				FileOutputStream fos = new FileOutputStream(file+FILENAME, true);
//// write object to file in normal txt				
//				OutputStreamWriter w = new OutputStreamWriter(fos);
//				w.write("\n"+trainingSubjectBrowse.toString());
//// write object to file in ObjectOutputStream
////				ObjectOutputStream w = new ObjectOutputStream(fos);
////				w.writeObject(trainingSubjectBrowse);
//				
//				System.out.println("write success");
//				w.flush();
//				w.close();
//				fos.flush();
//				fos.close();
//			}
			
//// read object into	programs	
//// objectInputStream
//			File file = new File(PATH);
//			TrainingSubjectBrowse trainingBrowse=null;
//			if(file.isDirectory()) {
//				FileInputStream fis = new FileInputStream(file+"\\"+FILENAME);
//				ObjectInputStream ois = new ObjectInputStream(fis);
//				trainingBrowse = (TrainingSubjectBrowse) ois.readObject();
//				System.out.println(trainingBrowse);
////				System.out.println(trainingBrowse.toString());
//				ois.close();
//				fis.close();
//			}
//			System.out.println("trainingBrowse.getSubject()"+trainingBrowse.getSubject());
//			System.out.println("trainingBrowse.getRegisterUrl()"+trainingBrowse.getRegisterUrl());
//			System.out.println("trainingBrowse.getTrainingDateSpan()"+trainingBrowse.getTrainingDateSpan());
			
			File filePath =new File(PATH);
// replace write-out code above by method			
			testJsoup.writeInfosInDirectedMethod(filePath, jsonObj, "string");
//			testJsoup.writeInfosInDirectedMethod(filePath, jsonObj, "object");			
// replace read-into code above by method	
//			TrainingSubjectBrowse trainingBrowse = testJsoup.readInfosUnderObjectInputStream(filePath);
//			System.out.println("trainingBrowse=\n"+trainingBrowse);
			
		} catch(Exception e) {
			System.out.println("Exception Message="+e);
		}
	}
	public Elements getAllTableRowOfSpecificIndexOfTable(Document doc, Integer indexOfTables) {
		// // find all elements with <body> tag
		Elements ele = doc.select("body");
//			System.out.println("ele=\n"+ele);
		
		// find all elements with <table> tag
		Elements eleTable = ele.select("table");
//			System.out.println("eleTable="+eleTable);
		
		Elements courseContentsTable = eleTable.eq(indexOfTables);
//			System.out.println("courseContentsTable=\n"+courseContentsTable);
		
		Elements elecourseContentsTable = courseContentsTable.select("tr");
		return elecourseContentsTable;
	}
//	private Map<String, String> mapOfThTd = new HashMap<String, String>();
	private Map<String, String> linkedMapOfThTd = new LinkedHashMap<String, String>();
	public Map<String, String> putThTdOfSpecificTableRowToMap(Elements specificTableForPutToMap, Integer indexOfTr) {
		Elements specificRow = specificTableForPutToMap.eq(indexOfTr);
		System.out.println("specificRow=\n"+specificRow);
		
		String thTextInSpecificRow = specificRow.select("th").text();
		// 課程名稱：
//		System.out.println("thTextInSpecificRow="+thTextInSpecificRow);
		String tdTextInSpecificRow = specificRow.select("td").text();
		// IPMA國際專案管理實務應用實戰班(整班為遠距教學)
//		System.out.println("tdTextInSpecificRow="+tdTextInSpecificRow);
		String textInSpecificRow = specificRow.text();
		// 課程名稱： IPMA國際專案管理實務應用實戰班(整班為遠距教學)
//		System.out.println("textInSpecificRow="+textInSpecificRow);
		
		linkedMapOfThTd.put(thTextInSpecificRow, tdTextInSpecificRow);
//		System.out.println("linkedMapOfThTd="+linkedMapOfThTd);
		
		return linkedMapOfThTd;
	}
	public String getThTdTextOfSpecificTableRow(Map<String, String> mapListOfThTd, Integer indexOfMap, String getKeyOrValue){
		// entrySet
		Set<Entry<String, String>> entrySetOfMapList = gotMapListOfThTd.entrySet();
//		System.out.println("entrySetOfMapList="+entrySetOfMapList);
		// convert map to list by ArrayList<Entry>
		listOfEntryOfThTd = new ArrayList<Entry<String,String>>(entrySetOfMapList);
		System.out.println("listOfEntryOfThTd="+listOfEntryOfThTd);
		Entry<String, String> gotSpecificEntry = listOfEntryOfThTd.get(indexOfMap);
		System.out.println("gotSpecificEntry="+gotSpecificEntry);
			// get key & value
//		System.out.println("gotSpecificEntry.getKey()="+gotSpecificEntry.getKey());
//		System.out.println("gotSpecificEntry.getValue()="+gotSpecificEntry.getValue());
//		// convert map to list by stream
//		Stream<Entry<String, String>> streamEntrySet = entrySetOfMapList.stream();
//		System.out.println("streamEntrySet="+streamEntrySet);
//		System.out.println("streamEntrySet.toArray()[0]="+streamEntrySet.toArray()[0]);
		
//		// keySet
//		Set<String> keySetOfMapList = gotMapListOfThTd.keySet();
//		System.out.println("keySetOfMapList="+keySetOfMapList);
//		// convert map to list by ArrayList<String>
//		java.util.List<String> listOfKeyOfTh = new ArrayList<String>(keySetOfMapList);
//		System.out.println("listOfKeyOfTh="+listOfKeyOfTh);
//		System.out.println("listOfKeyOfTh.get(0)="+listOfKeyOfTh.get(0));
//		// convert map to list by stream
//		Stream<String> streamKeySet = keySetOfMapList.stream();
////		System.out.println("keySetArray="+Arrays.toString(streamKeySet.toArray()));
////		System.out.println("streamKeySet.toArray().length="+streamKeySet.toArray().length);
//		System.out.println("streamKeySet="+streamKeySet);
//		System.out.println("streamKeySet.toArray()[0]="+streamKeySet.toArray()[0]);
		
		if(getKeyOrValue.toLowerCase().equals("key")) {
			return gotSpecificEntry.getKey();
		} else if(getKeyOrValue.toLowerCase().equals("value")) {
			return gotSpecificEntry.getValue();
		} else {
			return gotSpecificEntry.getKey()+gotSpecificEntry.getValue();
		}
	}
	public JSONObject loopToGetRequiredInfosUnderCombinedKV(Elements courseContentsTable, Map<String, String> gotMapListOfThTd, String kv) {
		JSONObject jsonObj = new JSONObject();
		for(int indexOfMapList=0; indexOfMapList<gotMapListOfThTd.size() ;indexOfMapList++) {
//			String gotKey = testJsoup.getThTdTextOfSpecificTableRow(gotMapListOfThTd, indexOfMapList, "key");
//			String gotVal = testJsoup.getThTdTextOfSpecificTableRow(gotMapListOfThTd, indexOfMapList, "value");
//			System.out.println("---> "+gotKey+gotVal);
			
			String gotKV = getThTdTextOfSpecificTableRow(gotMapListOfThTd, indexOfMapList, kv);
//			System.out.println("gotKV="+gotKV);
			// before cut one time-> 訓練費用：=學員負擔：1,152 政府負擔：4,608 每班人數：24 人 訓練時數：36 小時
			String[] gotKVSplittedArr = gotKV.split("[：]", 2);
			System.out.println("gotKVSplittedArr="+Arrays.toString(gotKVSplittedArr));
			// after cut one time-> [訓練費用, 學員負擔：1,152 政府負擔：4,608 每班人數：24 人 訓練時數：36 小時]

			if(gotKVSplittedArr[0].equals("訓練費用")) {
				// cost
				// training hour
				String[] tdTrainingCostSplit;
				if((tdTrainingCostSplit = gotKVSplittedArr[1].split("[： ]")).length==10) {
					// method1-arr==10 [學員負擔, 1,152, 政府負擔, 4,608, 每班人數, 24, 人, 訓練時數, 36, 小時]
					System.out.println("arr="+Arrays.toString(tdTrainingCostSplit));
					System.out.println("學員負擔/政府負擔arr->\n"
									+ "---------->"+gotKVSplittedArr[0]+":"+tdTrainingCostSplit[1]+"/"+tdTrainingCostSplit[3]);
					// 訓練費用:1,152/4,608
					jsonObj.put(gotKVSplittedArr[0], tdTrainingCostSplit[1]+"/"+tdTrainingCostSplit[3]);
					System.out.println("訓練時數arr->\n"
									+ "---------->"+tdTrainingCostSplit[7]+":"+tdTrainingCostSplit[8]+" 小時");
					// 訓練時數:36 小時
					jsonObj.put(tdTrainingCostSplit[7], tdTrainingCostSplit[8]+" 小時");
				} else {
					// method2-catch html tag
					Elements trTrainingCost = courseContentsTable.eq(11);
					Elements liTrainingCost = trTrainingCost.select("li");
					// <li>學員負擔：<span class="text-primary"><b>1,152</b></span></li>
					// <li>政府負擔：<span class="text-primary"><b>4,608</b></span></li>
                    // <li>每班人數：<span class="text-primary"><b>24 人</b></span></li>
					// <li>訓練時數：<span class="text-primary"><b>36 小時</b></span></li>
					String memberPay = liTrainingCost.eq(0).text();
					String govPay = liTrainingCost.eq(1).text();
					String trainingHour = liTrainingCost.eq(3).text();
					System.out.println("學員負擔/政府負擔li->\n"
									+ "---------->"+gotKVSplittedArr[0]+":"+memberPay.split("[：]")[1]+"/"+govPay.split("[：]")[1]);
					// 訓練費用:1,152/4,608
					jsonObj.put(gotKVSplittedArr[0], memberPay.split("[：]")[1]+"/"+govPay.split("[：]")[1]);
					System.out.println("訓練時數li->\n"
									+ "---------->"+trainingHour.split("[：]")[0]+":"+trainingHour.split("[：]")[1]);
					// 訓練時數:36 小時
					jsonObj.put(trainingHour.split("[：]")[0], trainingHour.split("[：]")[1]);
				}
				
			} else {
				System.out.println("gotKVSplittedArr.length="+gotKVSplittedArr.length+"\n"
									+ "----------> "+gotKVSplittedArr[0]+":"+gotKVSplittedArr[1]);
				// 報名日期 :111/05/11 12:00 ~ 111/06/08 18:00 (若開放報名時間超過1個月以上，表示本班曾變更訓練期間)
				jsonObj.put(gotKVSplittedArr[0], gotKVSplittedArr[1]);
			}
		}
		System.out.println("------end for loop------");
		return jsonObj;
	}
	
	// jsonObj is not Serializable
	public void writeInfosInDirectedMutualMethod(File filePath, JSONObject jsonObj,
			Rent591ItemsDao rent591ItemsDao, String writeByObjectOrString) throws Exception {
// prepare file to write data		
//		File file = new File(PATH);
//		System.out.println("path="+file.getAbsolutePath());
		if(filePath.isDirectory()) {
			System.out.println("start write to "+filePath.getAbsolutePath()+"\\");
//			if(object instanceof JSONObject) {
//				System.out.println("object instanceof JSONObject");
//				System.out.println("jsonObj.toString(1)="+ ((JSONObject)object).toString(2)); 
//				
//			} else if(object instanceof Rent591ItemsDao) {
//				System.out.println("object instanceof Rent591ItemsDao");
//				
//			}
//https://mkyong.com/java/how-to-read-and-write-java-object-to-a-file/
			// D:\xxx\xxx + \xxx.txt
			FileOutputStream fos = new FileOutputStream(filePath+"\\"+FILENAME, true);
			if(writeByObjectOrString.toLowerCase().equals("string")) {
// write object to file in normal txt				
				OutputStreamWriter w = new OutputStreamWriter(fos);
				w.write("\n"+(jsonObj).toString());
				w.flush();
				w.close();
			} else if(writeByObjectOrString.toLowerCase().equals("object")) {
// write object to file in ObjectOutputStream
				ObjectOutputStream w = new ObjectOutputStream(fos);
				w.writeObject(rent591ItemsDao);
				System.out.println("---------flag--------");
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

	public void writeInfosInDirectedMethod(File filePath, JSONObject jsonObj, String writeByObjectOrString) throws Exception {
		TrainingSubjectBrowse trainingSubjectBrowse = null;
		if(writeByObjectOrString.toLowerCase().equals("string")) {
// prepare to write in normal txt 			
			trainingSubjectBrowse = new TrainingSubjectBrowse(jsonObj);
//					System.out.println("trainingSubjectBrowse="+trainingSubjectBrowse.getJsonObj());
//					System.out.println("trainingSubjectBrowse.toString="+trainingSubjectBrowse.toString());
			
		} else if(writeByObjectOrString.toLowerCase().equals("object")) {
// prepare write to file in objectOutputStream
			trainingSubjectBrowse = new TrainingSubjectBrowse();
			trainingSubjectBrowse.setSubject(jsonObj.getString("課程名稱"));
			trainingSubjectBrowse.setTrainingStartEnd(jsonObj.getString("預定訓練起訖日期"));
			trainingSubjectBrowse.setTrainingDateSpan(jsonObj.getString("上課時間"));
			trainingSubjectBrowse.setLocationTraining(jsonObj.getString("縣 市 別"));
			trainingSubjectBrowse.setTotalTrainingHours(jsonObj.getString("訓練時數"));
			trainingSubjectBrowse.setCost(jsonObj.getString("訓練費用"));
			trainingSubjectBrowse.setRegisterStart(jsonObj.getString("報名日期 ").split("[~]", 2)[0]);
			trainingSubjectBrowse.setRegisterEnd(jsonObj.getString("報名日期 ").split("[~]", 2)[1].split("[(]")[0]);
			trainingSubjectBrowse.setRegisterUrl(jsonObj.getString("報名網址"));
		}
		
// prepare file to write data		
//		File file = new File(PATH);
//		System.out.println("path="+file.getAbsolutePath());
		if(filePath.isDirectory()) {
			System.out.println("start write to "+filePath.getAbsolutePath()+"\\");
			System.out.println("jsonObj.toString(1)="+jsonObj.toString(2)); 
//https://mkyong.com/java/how-to-read-and-write-java-object-to-a-file/
			// D:\xxx\xxx + \xxx.txt
			FileOutputStream fos = new FileOutputStream(filePath+"\\"+FILENAME, true);
			if(writeByObjectOrString.toLowerCase().equals("string")) {
// write object to file in normal txt				
				OutputStreamWriter w = new OutputStreamWriter(fos);
				w.write("\n"+trainingSubjectBrowse.toString());
				w.flush();
				w.close();
			} else if(writeByObjectOrString.toLowerCase().equals("object")) {
// write object to file in ObjectOutputStream
				ObjectOutputStream w = new ObjectOutputStream(fos);
				w.writeObject(trainingSubjectBrowse);
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
	public TrainingSubjectBrowse readInfosUnderObjectInputStream(File filePath) throws Exception{
// objectInputStream
		TrainingSubjectBrowse trainingBrowse=null;
//		File file = new File(PATH);
		if(filePath.isDirectory()) {
			FileInputStream fis = new FileInputStream(filePath+"\\"+FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			trainingBrowse = (TrainingSubjectBrowse) ois.readObject();
//			System.out.println(trainingBrowse);
//			System.out.println(trainingBrowse.toString());
			ois.close();
			fis.close();
		}
//		System.out.println("trainingBrowse.getSubject()"+trainingBrowse.getSubject());
//		System.out.println("trainingBrowse.getRegisterUrl()"+trainingBrowse.getRegisterUrl());
//		System.out.println("trainingBrowse.getTrainingDateSpan()"+trainingBrowse.getTrainingDateSpan());
		return trainingBrowse;
	}
}
