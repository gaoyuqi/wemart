package cn.wemart.shelfManagement;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Reporter;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteDelete;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.userManagment.ShopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class ShelfManagement {
	
	public String response;
	public int method;
	public String url = LoadAPIInfo.url + "/api/usermng/seller/decoration/shelf";
	
	public ShelfManagement(String method){
		Map<String,String> methodMap = new HashMap<String,String>();
		methodMap.put("post", "1");
		methodMap.put("get", "2");
		methodMap.put("put", "3");
		methodMap.put("delete", "4");
		this.method = Integer.valueOf(methodMap.get(method));
	}
	
	public ShelfManagement(String method,String url){
		Map<String,String> methodMap = new HashMap<String,String>();
		methodMap.put("post", "1");
		methodMap.put("get", "2");
		methodMap.put("put", "3");
		methodMap.put("delete", "4");
		this.method = Integer.valueOf(methodMap.get(method));
		this.url = url;
	}
	
	@Test
	public String test(String mobile, String password, String sellerId, Object[][] keyValueList) {
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.EnterShop(mobile, password, sellerId);
		System.out.println(shopLogin.response);
		CloseableHttpClient httpClient = shopLogin.httpClient;
		
		Reporter.log(getCurrent.Time());
		switch(method){
		case 1 :
			response = ExecutePost.getPostMethodResponse(httpClient, url,keyValueList);
			break;
		case 2 :
			response = ExecuteGet.getGetMethodResponse(httpClient, url,keyValueList);
			break;
		case 3 :
			response = ExecutePut.getPutMethodResponse(httpClient, url,keyValueList);
			break;
		case 4 :
			response = ExecuteDelete.getDeleteMethodResponse(httpClient, url,keyValueList);
			break;
		}
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		return returnValue;
	}

}