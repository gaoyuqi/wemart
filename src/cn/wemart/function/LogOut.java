package cn.wemart.function;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Reporter;
import org.testng.annotations.Test;

import bsh.org.objectweb.asm.Type;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.usermng.ShopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

import com.TestNG.Assertion;

public class LogOut {

	/**
	 * 退出用户
	 */
	@Test
	public static String logOut(String mobile,String password, String type, String sllerId){
		ShopLogin.DirectEnterShop(mobile, password, type, sllerId);
		CloseableHttpClient httpClient = ShopLogin.httpclient;
		
		String url = LoadAPIInfo.url+"/api/authmng/quit";
		Object[][] keyValueList = new Object[][]{};

		Reporter.log(getCurrent.Time());
		String response = ExecutePost.getPostMethodResponse(httpClient, url, keyValueList);
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		return returnValue;
		
	}
	
	@Test
	public static void test(String mobile,String password, String type, String sllerId){
		String returnValue = logOut("13818881111","123456","3","234");
		Assertion.verifyEquals(returnValue, "0", "判断是否正确退出账号");
		Reporter.log(getCurrent.Time());
	}
}
