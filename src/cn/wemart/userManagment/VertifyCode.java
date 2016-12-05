package cn.wemart.userManagment;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.TestNG.Assertion;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class VertifyCode {

	/**
	 * 获取手机验证码
	 */
	@Test
	public static void getVertifyCode(String mobile){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String url = LoadAPIInfo.url + "/api/authmng/vertifycode";
		Object[][] keyValueList = new Object[][]{
				{"mobileNo",mobile}
				};
		Reporter.log(getCurrent.Time());
		String response = ExecuteGet.getGetMethodResponse(httpClient, url, keyValueList);
		String returnMsg = JSONObject.fromObject(response).getString("returnMsg");
		Assertion.verifyEquals(returnMsg, "ok", "判断是否收到短信验证码");
		if(Assertion.verifyEqual(returnMsg, "ok")){
			Reporter.log("服务端已发送短信");
		}
		else{
			Reporter.log("服务端未发送短信");
		}
		Reporter.log(getCurrent.Time());
	}
}
