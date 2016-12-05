package cn.wemart.userManagment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteDelete;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

@Listeners({com.TestNG.AssertionListener.class})
public class SaveRuleAuth {

	@Test
	public static void saveRuleAuthTest(String mobile,String password, String type, String sllerId) {
		ShopLogin.DirectEnterShop(mobile, password, type, sllerId);
		CloseableHttpClient httpclient = ShopLogin.httpClient;
		String url = LoadAPIInfo.url + "/api/usermng/admin";
		
		Map<Object,Object> roleAuth = new HashMap<Object,Object>();
		roleAuth.put("goodsmng", 1);
		roleAuth.put("usermng", 1);
		roleAuth.put("marketmng", 1);
		
		JSONArray roleAutharray = JSONArray.fromObject(roleAuth);
		
		Object[][] keyValueList = new Object[][]{
				{"adminAcct","13818813333"}, // 手机号 (必填)
				{"adminNo","215"}, // 管理员编号
				{"roleName","sellercs"},    // 角色名 （必填）
				{"roleAuth",roleAutharray},    
				};
		
		System.out.println(getCurrent.Time());
		String response = ExecutePut.getPutMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+response);
		System.out.println(getCurrent.Time());
	}
	
	public static void main(String[] args) {
		saveRuleAuthTest("13818881111","123456","3","234");
	}

}
