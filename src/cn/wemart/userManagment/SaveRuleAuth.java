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
		
		Map<Object,Object> goodsmng = new HashMap<Object,Object>();
		goodsmng.put("goodsmng", 1);
		
		Map<Object,Object> usermng = new HashMap<Object,Object>();
		usermng.put("usermng", 1);
		
		Map<Object,Object> marketmng = new HashMap<Object,Object>();
		marketmng.put("marketmng", 1);
		
		Map<Object,Object> fundsmng = new HashMap<Object,Object>();
		fundsmng.put("fundsmng", 1);
		
		Map<Object,Object> ordermng = new HashMap<Object,Object>();
		ordermng.put("ordermng", 1);
		
		List<Object> roleAuth = new ArrayList<Object>();
		roleAuth.add(goodsmng);
		roleAuth.add(usermng);
		roleAuth.add(marketmng);
		roleAuth.add(fundsmng);
		roleAuth.add(ordermng);
		
		JSONArray roleAutharray = JSONArray.fromObject(roleAuth);
		JSONArray excludeRoleAuth = JSONArray.fromObject("[]");
		
		Object[][] keyValueList = new Object[][]{
				{"adminAcct","13818813333"}, // 手机号 (必填)
				{"adminNo","215"}, // 管理员编号
				{"roleName","管理员"},    // 角色名 （必填）
				{"roleAuth",roleAutharray}, 
				{"excludeRoleAuth",excludeRoleAuth}
				};
		
		
		System.out.println(getCurrent.Time());
		response = ExecutePut.getPutMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+response);
		System.out.println(getCurrent.Time());
	}
	
}
