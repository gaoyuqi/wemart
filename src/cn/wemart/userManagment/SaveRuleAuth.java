package cn.wemart.userManagment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecutePut;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

@Listeners({com.TestNG.AssertionListener.class})
public class SaveRuleAuth {

	public String response;
	@Test
	public void saveRuleAuthTest(String mobile,String password, String type, String sllerId) {
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.DirectEnterShop(mobile, password, type, sllerId);
		CloseableHttpClient httpclient = shopLogin.httpClient;
		String url = LoadAPIInfo.url + "/api/usermng/admin";
		
		Map<Object,Object> roleAuth = new HashMap<Object,Object>();
		roleAuth.put("goodsmng", 1);
		roleAuth.put("usermng", 1);
		roleAuth.put("marketmng", 1);
		roleAuth.put("fundsmng", 1);
		roleAuth.put("ordermng", 1);
		JSONArray roleAutharray = JSONArray.fromObject(roleAuth);
		JSONArray excludeRoleAuth = JSONArray.fromObject("[]");
		
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("adminAcct","13818813333");
		keyValueList.put("adminNo","215");
		keyValueList.put("roleName","管理员");
		keyValueList.put("roleAuth",roleAutharray);
		keyValueList.put("excludeRoleAuth",excludeRoleAuth);
		
		
		System.out.println(getCurrent.Time());
		response = ExecutePut.getPutMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+response);
		System.out.println(getCurrent.Time());
	}
	
}
