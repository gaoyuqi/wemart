package cn.wemart.userManagment;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

@Listeners({com.TestNG.AssertionListener.class})
public class ChangePassword {

	public static String response;
	@Test
	public static String test(String mobile,String password, String type, String sllerId){
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.DirectEnterShop(mobile, password, type, sllerId);
		CloseableHttpClient httpClient = shopLogin.httpClient;
		String url = LoadAPIInfo.url+"/api/usermng/admin/password";
		Object[][] keyValueList = new Object[][]{
				{"oldPassword",password},//旧密码（必填）
			    {"password",password}, // 新密码 （必填）
			    {"confirmPassword",password}// 确认密码 （必填）
				};
		Reporter.log(getCurrent.Time());
		response = ExecutePut.getPutMethodResponse(httpClient, url, keyValueList);
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		return returnValue;
	}
}
