package fnTest;

import java.io.File;
import java.util.Arrays;
import java.util.Set;

import org.ini4j.Ini;
import org.ini4j.IniPreferences;
import org.ini4j.Profile.Section;

import iniReadDao.IniReaderDao;

public class TestIniReader {
	private static TestIniReader testIniReader;
	private String IniFilePath="D:\\JavaFramePrac\\eclipse-workspace\\test\\src\\test\\java\\fnTest\\";
	private String IniFileName="autoFormSetting.ini";
	private static Ini iniRead;
	private static String sectionString="Questionnaire";
	
	private static IniReaderDao iniReaderDao;
	
	public TestIniReader() throws Exception{
		System.out.println("enter TestIniReader()");
//		setIniPath(PATH, FILE);
		new TestIniReader(IniFilePath, IniFileName);
//		iniRead = new Ini(new File(PATH+FILE));
//		iniReaderDao = getIniReaderDaoInstance();
	}
	public TestIniReader(String path, String fileName) throws Exception{
		System.out.println("enter TestIniReader(String path, String fileName)");
		System.out.println("iniPath="+path+", iniFileName="+fileName);
		setIniPath(path, fileName);
		iniRead = new Ini(new File(path+fileName));
		iniReaderDao = getIniReaderDaoInstance();
//		System.out.println("iniRead="+iniRead);
	}
	private void setIniPath(String path, String file) {
		this.IniFilePath=path; this.IniFileName=file;
	}
	public Ini getIniReaderInstance() {
		return iniRead;
	}
	public String setSectionString(String sectionStringTo) {
		sectionString=sectionStringTo;
		return sectionString;
	}
	private IniReaderDao getIniReaderDaoInstance() {
		return new IniReaderDao();
	}
	
	public static void main(String[] args) {
		
		try {
			testIniReader = new TestIniReader();
//			iniReaderDao = testIniReader.getIniReaderDaoInstance();
			
//			iniRead = new Ini(new File(PATH+FILE));
//			System.out.println("iniRead="+iniRead);
//			System.out.println("iniRead.entrySet()="+iniRead.entrySet());//[org.ini4j.BasicMultiMap$ShadowEntry@b3d680d0]
//			System.out.println("iniRead.keySet()="+iniRead.keySet()); //[Questionnaire]
//			IniPreferences iniReadPref = new IniPreferences(iniRead);
//			System.out.println("iniReadPref="+iniReadPref);//System Preference Node: /
			
//			Set<String> setOfKeys = testIniReader.getKeySetOfDirectedSection(iniRead, sectionString);
			
//			for(String key: setOfKeys) {
//				System.out.println("key="+key);
//				Section gotSection = iniRead.get(SectionString);
//				System.out.println("gotSection.get(key)="+gotSection.get(key));
//				//行代
//				if(key.equals("EmployeeNum")) {
//					System.out.println("--> key="+key+", hit successfully");
//					iniReaderDao.setEmployeeNum(gotSection.get(key));
//				}
//				//目前為止累計已購買"實名制"快篩劑之數量
//				if(key.equals("TotalPurchasedTestsById")) {
//					System.out.println("--> key="+key+", hit successfully");
//					iniReaderDao.setTotalPurchasedTestsById(gotSection.get(key));
//				}
//				//目前為止公司分配給你的快篩劑"未"使用之數量
//				if(key.equals("TotalGivenTestsRest")) {
//					System.out.println("--> key="+key+", hit successfully");
//					iniReaderDao.setTotalGivenTestsRest(gotSection.get(key));
//				}
//			}
//			iniReaderDao = testIniReader.getIntactIniReaderDao(setOfKeys, sectionString);
//			System.out.println("iniReaderDao=\n"+iniReaderDao);

//彙整方法
//			String gotSectionString = testIniReader.setSectionString("Questionnaire");
//			IniReaderDao gotIniReaderDao = testIniReader.getIniReaderDaoOfFillInInfo(gotSectionString);
//			System.out.println("iniRead="+iniRead);
//			System.out.println("testIniReader="+testIniReader);
			
			Set<String> defineProSet = testIniReader.getKeySetOfDirectedSection(iniRead, "DefineProcedure");
			System.out.println("defineProSet="+defineProSet);
			IniReaderDao iniReaderDaoDefPro = testIniReader.setIntactIniReaderDao(defineProSet, "DefineProcedure");
			System.out.println("iniReaderDaoDefPro=\n"+iniReaderDaoDefPro);
			
			IniReaderDao gotIniReaderDao = testIniReader.getIniReaderDaoOfFillInInfo();
			System.out.println("gotIniReaderDao=\n"+gotIniReaderDao);
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	public Set<String> getKeySetOfDirectedSection(Ini iniRead, String section) {
		System.out.println("enter getKeySetOfDirectedSection()");
//		System.out.println("iniRead="+iniRead);
//		System.out.println("section="+section);
		for(String loopSection: iniRead.keySet()) {
//			System.out.println("loopSection="+loopSection);//Questionnaire
//			System.out.println("iniRead.get(loopSection)="+iniRead.get(loopSection));//{EmployeeNum=[010995], TotalPurchasedTestsById=[0], TotalGivenTestsRest=[1]}
			if(loopSection.equals(section)) {
				Section gotSection = iniRead.get(loopSection);
				Set<String> setOfKeys = gotSection.keySet();
//				System.out.println("setOfKeys="+setOfKeys);
				return setOfKeys;
			}
		}
		return null;
	}
	public IniReaderDao setIntactIniReaderDao(Set<String> keySetOfDirectedSection, String directedSection) {
		System.out.println("enter setIntactIniReaderDao()");
		for(String key: keySetOfDirectedSection) {
			System.out.println("key="+key);
			Section gotSection = iniRead.get(directedSection);
			System.out.println("gotSection.get(key)="+gotSection.get(key));
	// [DefineProcedure] 			
			// Browser
			if(key.equals("Browser")) {
//				System.out.println("--> key="+key+", hit successfully");
				iniReaderDao.setBrowser(gotSection.get(key));
			}
			// WebDriverLocation
			if(key.equals("WebDriverLocation")) {
//				System.out.println("--> key="+key+", hit successfully");
				iniReaderDao.setWebDriverLocation(gotSection.get(key));
			}
			// FillInFastOrSlow
			if(key.equals("FillInFastOrSlow")) {
//				System.out.println("--> key="+key+", hit successfully");
				iniReaderDao.setFillInFastOrSlow(gotSection.get(key));
			}
			// AutoSubmit
			if(key.equals("AutoSubmit")) {
//				System.out.println("--> key="+key+", hit successfully");
				iniReaderDao.setAutoSubmit(gotSection.get(key));
			}
			if(key.equals("AutoClosePage")) {
//				System.out.println("--> key="+key+", hit successfully");
				iniReaderDao.setAutoClosePage(gotSection.get(key));
			}
	// [Questionnaire]
			//行代
			if(key.equals("EmployeeNum")) {
//				System.out.println("--> key="+key+", hit successfully");
				iniReaderDao.setEmployeeNum(gotSection.get(key));
			}
			//目前為止累計已購買"實名制"快篩劑之數量
			if(key.equals("TotalPurchasedTestsById")) {
//				System.out.println("--> key="+key+", hit successfully");
				iniReaderDao.setTotalPurchasedTestsById(gotSection.get(key));
			}
			//目前為止公司分配給你的快篩劑"未"使用之數量
			if(key.equals("TotalGivenTestsRest")) {
//				System.out.println("--> key="+key+", hit successfully");
				iniReaderDao.setTotalGivenTestsRest(gotSection.get(key));
			}
		}
		return iniReaderDao;
	}
	public IniReaderDao getIniReaderDaoOfFillInInfo() {
		System.out.println("enter getIniReaderDaoOfFillInInfo()");
		System.out.println("def sectionString="+sectionString);
		Set<String> setOfKeys = getKeySetOfDirectedSection(iniRead, sectionString);
		
		iniReaderDao = setIntactIniReaderDao(setOfKeys, sectionString);
//		System.out.println("iniReaderDao="+iniReaderDao);
		return iniReaderDao;
	}
}
