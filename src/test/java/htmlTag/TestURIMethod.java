package htmlTag;

import java.io.File;
import java.net.URI;

public class TestURIMethod {
	public static void main(String[] args) {
		
		try{
			URI uri = new URI("https://ojt.wda.gov.tw/ClassSearch/Detail?OCID=142676&plantype=1");
			System.out.println("getHost="+uri.getHost()); // ojt.wda.gov.tw
			System.out.println("getFragment="+uri.getFragment()); // null
			System.out.println("getRawFragment="+uri.getRawFragment());
			System.out.println("getPath="+uri.getPath()); // /ClassSearch/Detail
			System.out.println("getRawPath="+uri.getRawPath()); // /ClassSearch/Detail
			System.out.println("getPort="+uri.getPort()); // -1
			System.out.println("getQuery="+uri.getQuery()); // OCID=142676&plantype=1
			System.out.println("getRawQuery="+uri.getRawQuery()); // OCID=142676&plantype=1
			System.out.println("getAuthority="+uri.getAuthority()); // ojt.wda.gov.tw
			System.out.println("getRawAuthority="+uri.getRawAuthority()); // ojt.wda.gov.tw
			
			// "//"+getHost()+getPath()+"?"+getQuery()
			System.out.println("getSchemeSpecificPart="+uri.getSchemeSpecificPart()); // //ojt.wda.gov.tw/ClassSearch/Detail?OCID=142676&plantype=1
			System.out.println("getRawSchemeSpecificPart="+uri.getRawSchemeSpecificPart()); // //ojt.wda.gov.tw/ClassSearch/Detail?OCID=142676&plantype=1
			System.out.println("getUserInfo="+uri.getUserInfo()); // null
			System.out.println("getRawUserInfo="+uri.getRawUserInfo()); // null
			System.out.println("getScheme="+uri.getScheme()); // https
			
		} catch(Exception e) {
			System.out.println("e="+e);
		}
	}
}
