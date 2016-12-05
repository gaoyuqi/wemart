package cn.wemart.userManagment;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

import com.TestNG.Assertion;

@Listeners({com.TestNG.AssertionListener.class})
public class IsRegistered {

	/**
	 * 检查手机是否注册
	 */
	@Test
	public static void isRegistered(String mobile){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String url = LoadAPIInfo.url + "/api/authmng";
		Object[][] keyValueList = new Object[][]{
				{"adminAcct",mobile}
				};
		Reporter.log(getCurrent.Time());
		String response = ExecuteGet.getGetMethodResponse(httpClient, url, keyValueList);
		String returnMsg = JSONObject.fromObject(response).getString("returnMsg");
		Assertion.verifyEquals(returnMsg, "1", "判断是否注册");
		if(Assertion.verifyEqual(returnMsg, "1")){
			Reporter.log("该手机号码已注册！");
		}
		else{
			Reporter.log("该手机号码未注册！");
		}
		Reporter.log(getCurrent.Time());
	}
}
