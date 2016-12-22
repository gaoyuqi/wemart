package cn.wemart.extensionFunction;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.userManagment.ShopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

@Listeners({com.TestNG.AssertionListener.class})
public class ExtensionFunction {
	
	public String response;
	public String url = LoadAPIInfo.url + "/api/usermng/seller/product";
	public int method;
	public ExtensionFunction(String method){
		Map<String,String> methodMap = new HashMap<String,String>();
		methodMap.put("get", "2");
		methodMap.put("put", "3");
		this.method = Integer.valueOf(methodMap.get(method));
	}
	
	public ExtensionFunction(String method,String url){
		Map<String,String> methodMap = new HashMap<String,String>();
		methodMap.put("get", "2");
		methodMap.put("put", "3");
		this.method = Integer.valueOf(methodMap.get(method));
		this.url = url;
	}

	public String test(String mobile, String password, String sellerId, Object[][] keyValueList) {
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.EnterShop(mobile, password, sellerId);
		System.out.println(shopLogin.response);
		CloseableHttpClient httpClient = shopLogin.httpClient;
		Reporter.log(getCurrent.Time());
		switch(method){
		case 2 :
			response = ExecuteGet.getGetMethodResponse(httpClient, url,keyValueList);
			break;
		case 3 :
			response = ExecutePut.getPutMethodResponse(httpClient, url,keyValueList);
			break;
		}
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		return returnValue;
	}

}
