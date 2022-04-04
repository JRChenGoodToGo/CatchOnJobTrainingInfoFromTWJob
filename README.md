# CatchOnJobTrainingInfoFromTWJob
To get required information as required with structure of web page like below:
![image](https://user-images.githubusercontent.com/88480246/161496419-2240e681-878f-454e-83f3-3f94c6b99307.png)

This programs aim to get information as below the Notion form demonstrate: 
![image](https://user-images.githubusercontent.com/88480246/161496676-2b421b8f-a354-48ae-9c0a-e33b03adb3be.png)


Currently this programs have two method to store these information:
1. OutputStreamWriter-
write informations as String to text(.txt) file -> could directly see contents
2. ObjectOutputStream-
write informations as Object to text(.txt) file -> means have to use programs to read contents 

Information that stored will show as data below: 
1. OutputStreamWriter-
TrainingSubjectBrowse {
	subject-課程名稱=IPMA國際專案管理實務應用實戰班(整班為遠距教學), 
	trainingStartEnd-預定訓練起訖日期=111/06/11 ~ 111/07/23, 
	trainingDateSpan-上課時間=每週六9:30-12:30；13:30-16:30上課, 
	locationTraining-縣 市 別=臺中市, 
	totalTrainingHours-訓練時數=36 小時, 
	cost-訓練費用=1,152/4,608, 
	registerStart-報名日期(起)=111/05/11 12:00 , 
	RegisterEnd-報名日期(迄)= 111/06/08 18:00 , 
	registerUrl-報名網址=https://ojt.wda.gov.tw/ClassSearch/Detail?OCID=142676&plantype=1
}
2. ObjectOutputStream-
秒 sr htmlTag.TrainingSubjectBrowse        
L RegisterEndt Ljava/lang/String;L costq ~ L jsonObjt Lorg/json/JSONObject;L locationTrainingq ~ L 
registerStartq ~ L registerUrlq ~ L subjectq ~ L totalTrainingHoursq ~ L trainingDateSpanq ~ L trainingStartEndq ~ xpt  111/05/01 18:00 t 1,766/7,064pt 	?箔葉撣 111/04/04 12:00 t @https://ojt.wda.gov.tw/ClassSearch/Detail?OCID=142446&plantype=1t 閫????祕?t 	64 撠?t 7瘥曹?19:00~21:00銝玨???曹?19:00~21:00銝玨t 111/05/04 ~ 111/08/24


