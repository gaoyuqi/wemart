package cn.wemart.userManagment;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Reporter;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteDelete;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class DeleteRuleAuth {
	public static String response;
	@Test
	public static String test(String mobile,String password, String type, String sllerId,String deleteAdminId) {
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.DirectEnterShop(mobile, password, type, sllerId);
		CloseableHttpClient httpclient = shopLogin.httpClient;
		String url = LoadAPIInfo.url + "/api/usermng/admin";
	
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("adminNo",deleteAdminId);
		
		Reporter.log(getCurrent.Time());
		response = ExecuteDelete.getDeleteMethodResponse(httpclient, url, keyValueList);
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		return returnValue;
	}

}
