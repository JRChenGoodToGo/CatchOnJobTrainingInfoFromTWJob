package htmlTag;

import java.io.Serializable;

import org.json.JSONObject;

//https://jsoup.org/cookbook/extracting-data/attributes-text-html
//產業人才投資方案的介面
public class TrainingSubjectBrowse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// {"訓練費用":"1,152/4,608"
	// 	,"縣 市 別":"臺中市"
	// 	,"預定訓練起訖日期":"111/06/11 ~ 111/07/23"
	// 	,"上課時間":"每週六9:30-12:30；13:30-16:30上課"
	// 	,"報名日期 ":"111/05/11 12:00 ~ 111/06/08 18:00 (若開放報名時間超過1個月以上，表示本班曾變更訓練期間)"
	// 	,"課程名稱":"IPMA國際專案管理實務應用實戰班(整班為遠距教學)"
	// 	,"訓練時數":"36 小時"}
	public JSONObject jsonObj;
	// "課程名稱"
	private String subject;
	// "預定訓練起訖日期"
	private String trainingStartEnd;
	// "上課時間"
	private String trainingDateSpan;
	// "縣 市 別"
	private String locationTraining;
	// "訓練時數"
	private String totalTrainingHours;
	// "訓練費用"
	private String cost;
	// "報名日期 "
	private String registerStart;
	// "報名日期 "
	private String RegisterEnd;
	// 報名網址
	private String registerUrl;
	
	public TrainingSubjectBrowse(){
		
	}
	public TrainingSubjectBrowse(JSONObject jsonObj){
		this.jsonObj=jsonObj;
		this.subject=jsonObj.getString("課程名稱");
		this.trainingStartEnd=jsonObj.getString("預定訓練起訖日期");
		this.trainingDateSpan=jsonObj.getString("上課時間");
		this.locationTraining=jsonObj.getString("縣 市 別");
		this.totalTrainingHours=jsonObj.getString("訓練時數");
		this.cost=jsonObj.getString("訓練費用");
		this.registerStart=jsonObj.getString("報名日期 ").split("[~]", 2)[0];
		this.RegisterEnd=jsonObj.getString("報名日期 ").split("[~]", 2)[1].split("[(]")[0];
//		System.out.println("報名日期-->"+jsonObj.getString("報名日期 ")); // 111/05/11 12:00 ~ 111/06/08 18:00 (若開放報名時間超過1個月以上，表示本班曾變更訓練期間)
//		System.out.println("報名日期(起)-->"+jsonObj.getString("報名日期 ").split("[~]", 2)[0]); // 111/05/11 12:00
//		System.out.println("報名日期(迄)-->"+jsonObj.getString("報名日期 ").split("[~]", 2)[1].split("[(]")[0]); // 111/06/08 18:00
		this.registerUrl=jsonObj.getString("報名網址");
	}

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTrainingStartEnd() {
		return trainingStartEnd;
	}
	public void setTrainingStartEnd(String trainingStartEnd) {
		this.trainingStartEnd = trainingStartEnd;
	}
	public String getTrainingDateSpan() {
		return trainingDateSpan;
	}
	public void setTrainingDateSpan(String trainingDateSpan) {
		this.trainingDateSpan = trainingDateSpan;
	}
	public String getLocationTraining() {
		return locationTraining;
	}
	public void setLocationTraining(String locationTraining) {
		this.locationTraining = locationTraining;
	}
	public String getTotalTrainingHours() {
		return totalTrainingHours;
	}
	public void setTotalTrainingHours(String totalTrainingHours) {
		this.totalTrainingHours = totalTrainingHours;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getRegisterStart() {
		return registerStart;
	}
	public void setRegisterStart(String registerStart) {
		this.registerStart = registerStart;
	}
	public String getRegisterEnd() {
		return RegisterEnd;
	}
	public void setRegisterEnd(String registerEnd) {
		RegisterEnd = registerEnd;
	}
	public String getRegisterUrl() {
		return registerUrl;
	}
	public void setRegisterUrl(String registerUrl) {
		this.registerUrl = registerUrl;
	}
	public JSONObject getJsonObj() {
		return this.jsonObj;
	}
	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}

	@Override
	public String toString() {
		return "TrainingSubjectBrowse {\n\tsubject-課程名稱=" + subject 
				+ ", \n\ttrainingStartEnd-預定訓練起訖日期=" + trainingStartEnd
				+ ", \n\ttrainingDateSpan-上課時間=" + trainingDateSpan 
				+ ", \n\tlocationTraining-縣 市 別=" + locationTraining
				+ ", \n\ttotalTrainingHours-訓練時數=" + totalTrainingHours 
				+ ", \n\tcost-訓練費用=" + cost 
				+ ", \n\tregisterStart-報名日期(起)=" + registerStart
				+ ", \n\tRegisterEnd-報名日期(迄)=" + RegisterEnd 
				+ ", \n\tregisterUrl-報名網址=" + registerUrl + "\n}";
	}
	// {"":"1,152/4,608"
	// 	,"":"臺中市"
	// 	,"":"111/06/11 ~ 111/07/23"
	// 	,"":"每週六9:30-12:30；13:30-16:30上課"
	// 	," ":"111/05/11 12:00 ~ 111/06/08 18:00 (若開放報名時間超過1個月以上，表示本班曾變更訓練期間)"
	// 	,"":"IPMA國際專案管理實務應用實戰班(整班為遠距教學)"
	// 	,"":"36 小時"}
}
