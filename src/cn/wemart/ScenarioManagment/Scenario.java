package cn.wemart.ScenarioManagment;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import net.sf.json.JSONObject;
import cn.wemart.httppost.ExecuteDelete;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.userManagment.ShopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

@Listeners({com.TestNG.AssertionListener.class})
public class Scenario {

	public String response;
	public String method;
	public Scenario(String method){
		this.method = method;
	}

	@Test
	public String test(String mobile, String password, String sellerId, Object[][] keyValueList) {
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.EnterShop(mobile, password, sellerId);
		System.out.println(shopLogin.response);
		CloseableHttpClient httpClient = shopLogin.httpClient;
		String url = LoadAPIInfo.url + "/api/usermng/scenario";
		Reporter.log(getCurrent.Time());
		switch(method){
		case "post" :
			response = ExecutePost.getPostMethodResponse(httpClient, url,keyValueList);
			break;
		case "get" :
			response = ExecuteGet.getGetMethodResponse(httpClient, url,keyValueList);
			break;
		case "put" :
			response = ExecutePut.getPutMethodResponse(httpClient, url,keyValueList);
			break;
		case "delete" :
			response = ExecuteDelete.getDeleteMethodResponse(httpClient, url,keyValueList);
			break;
		}
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		return returnValue;
	}
}
