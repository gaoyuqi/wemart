package cn.wemart.userManagment;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Reporter;

import cn.wemart.httppost.ExecutePut;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class SaveUserInfo {
	public static String response;

	public static String test(String adminAcct, String password, String type,String objectId) {
		ShopLogin.DirectEnterShop(adminAcct, password, type, objectId);
		CloseableHttpClient httpclient = ShopLogin.httpClient;
		String url = LoadAPIInfo.url + "/api/usermng/admin";
		Object[][] keyValueList = new Object[][] {
				{ "mobileNo", "13818811111" },
				{ "nickName", "我爱北京天安门" },
				{ "qqAcct", "781615150" },
				{ "email", "sean@wemart.cn" }, };

		Reporter.log(getCurrent.Time());
		response = ExecutePut.getPutMethodResponse(httpclient, url,keyValueList);
		String returnValue = JSONObject.fromObject(response).getString("retunValue");
		return returnValue;
	}
}
