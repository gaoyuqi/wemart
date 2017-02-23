package cn.wemart.objectbase;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Reporter;

import cn.wemart.httppost.ExecuteDelete;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.userManagment.ShopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class ObjectBase {
	
	public String response;
	public int method;
	public String url;
	
	public void Init(String method,String url){
		Map<String,String> methodMap = new HashMap<String,String>();
		methodMap.put("post", "1");
		methodMap.put("get", "2");
		methodMap.put("put", "3");
		methodMap.put("delete", "4");
		this.method = Integer.valueOf(methodMap.get(method));
		this.url = url;
	}
	
	public String Test(CloseableHttpClient httpClient, Map<String,Object> keyValueList) {
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
