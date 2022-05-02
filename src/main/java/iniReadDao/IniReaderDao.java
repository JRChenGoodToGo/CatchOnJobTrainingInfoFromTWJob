package iniReadDao;

public class IniReaderDao {
	private String Browser;
	private String WebDriverLocation;
	private String FillInFastOrSlow;
	private String AutoSubmit;
	private String AutoClosePage;
	
	private String EmployeeNum;
	private String TotalPurchasedTestsById;
	private String TotalGivenTestsRest;
	public IniReaderDao() {
	}
	public String getBrowser() {
		return Browser;
	}
	public void setBrowser(String browser) {
		Browser = browser;
	}
	public String getWebDriverLocation() {
		return WebDriverLocation;
	}
	public void setWebDriverLocation(String webDriverLocation) {
		WebDriverLocation = webDriverLocation;
	}
	public String getFillInFastOrSlow() {
		return FillInFastOrSlow;
	}
	public void setFillInFastOrSlow(String fillInFastOrSlow) {
		FillInFastOrSlow = fillInFastOrSlow;
	}
	public String getAutoSubmit() {
		return AutoSubmit;
	}
	public void setAutoSubmit(String autoSubmit) {
		AutoSubmit = autoSubmit;
	}
	public String getAutoClosePage() {
		return AutoClosePage;
	}
	public void setAutoClosePage(String autoClosePage) {
		AutoClosePage = autoClosePage;
	}
	public String getEmployeeNum() {
		return EmployeeNum;
	}
	public void setEmployeeNum(String employeeNum) {
		EmployeeNum = employeeNum;
	}
	public String getTotalPurchasedTestsById() {
		return TotalPurchasedTestsById;
	}
	public void setTotalPurchasedTestsById(String totalPurchasedTestsById) {
		TotalPurchasedTestsById = totalPurchasedTestsById;
	}
	public String getTotalGivenTestsRest() {
		return TotalGivenTestsRest;
	}
	public void setTotalGivenTestsRest(String totalGivenTestsRest) {
		TotalGivenTestsRest = totalGivenTestsRest;
	}
	@Override
	public String toString() {
		return "IniReaderDao [Browser=" + Browser + ", WebDriverLocation=" + WebDriverLocation + ", FillInFastOrSlow="
				+ FillInFastOrSlow + ", AutoSubmit=" + AutoSubmit + ", AutoClosePage=" + AutoClosePage
				+ ", EmployeeNum=" + EmployeeNum + ", TotalPurchasedTestsById=" + TotalPurchasedTestsById
				+ ", TotalGivenTestsRest=" + TotalGivenTestsRest + "]";
	}
	
}
