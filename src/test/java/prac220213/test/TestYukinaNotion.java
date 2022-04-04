package prac220213.test;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;

import org.jraf.klibnotion.*;

import tw.yukina.notion.sdk.client.NotionClient;
import tw.yukina.notion.sdk.client.NotionSession;
import tw.yukina.notion.sdk.client.NotionSessionImpl;
import tw.yukina.notion.sdk.client.api.ApiClient;
import tw.yukina.notion.sdk.client.api.ApiClientImpl;
import tw.yukina.notion.sdk.model.NotionObject;
import tw.yukina.notion.sdk.model.common.Icon;
import tw.yukina.notion.sdk.model.common.file.FileObject;
import tw.yukina.notion.sdk.model.common.file.NotionSourceFileObject;
import tw.yukina.notion.sdk.model.database.Database;
import tw.yukina.notion.sdk.model.endpoint.database.query.DatabaseQuery;
import tw.yukina.notion.sdk.model.page.Page;
import tw.yukina.notion.sdk.model.page.property.PageProperty;

//https://titansoft.com/tw/news/titansoft-JIRA
//https://github.com/YukinaMochizuki/notion-sdk-java
public class TestYukinaNotion {
	static String TOKEN="secret_SxNWmjqYfZIflQT6JJIilJAFNFXpmDkHHA779WjyScB";
	static String UUID="d268b6334adb48e285db82e42b0f84d8";
	public static void main(String[] args) {
		
		NotionClient client = new NotionClient(TOKEN);
		System.out.println(client);
//		NotionSession session = client.openSession();
//		System.out.println("session="+session);
		NotionSession session2 = new NotionSessionImpl(new ApiClientImpl(TOKEN));
		Page page = session2.getPageByUuid(UUID);
//		Page page = session.getPageByUuid(UUID);
//		System.out.println(page);
		Optional<String> opt = page.getTitle();
		System.out.println("opt.isEmpty="+opt.isEmpty());
		String url = page.getUrl();
		System.out.println("url="+url);
		
		
		System.out.println("id="+page.getId());
		Map<String, PageProperty> map = page.getPropertyMap();
		System.out.println("map="+map);
		System.out.println("keySet="+map.keySet());
		System.out.println("entrySet="+map.entrySet());
		FileObject fileObj = page.getCover();
		System.out.println("fileObj="+fileObj);
		System.out.println("isArchived="+page.isArchived());
		ZonedDateTime zoneDT = page.getLastEditedTime();
		System.out.println("zoneDT="+zoneDT);
		
//		session.flush();
//		session.close();
		
	}
}
