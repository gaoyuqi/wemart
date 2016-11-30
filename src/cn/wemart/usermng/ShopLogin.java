package cn.wemart.usermng;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.reporters.jq.ReporterPanel;
import org.uncommons.reportng.ReportNGException;
import org.uncommons.reportng.ReportNGUtils;

import com.TestNG.Assertion;

import net.sf.json.JSONObject;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadUserInfo;
import cn.wemart.util.getCurrent;

/**
 * 
 * @author sean
 *
 */
public class ShopLogin {

	private static Log log = LogFactory.getLog(ShopLogin.class);
	public static CloseableHttpClient httpclient = HttpClients.createDefault();

	/**
	 * 直接登录店铺
	 */
	@Test
	public static CloseableHttpClient DirectEnterShop(String adminAcct, String password,String type, String objectId) {
		String url = LoadAPIInfo.url + LoadAPIInfo.userLoginAPI;

		Object[][] keyValueList = new Object[][] {
					{ "adminAcct", adminAcct },
					{ "password", password },
					{ "type", type },
					{ "objectId", objectId }
					};

		Reporter.log(getCurrent.Time());
		String response = ExecutePost.getPostMethodResponse(httpclient, url,keyValueList);
		JSONObject responsejson = JSONObject.fromObject(response);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (Assertion.verifyEqual(returnValue, 0)) {
				Reporter.log("登录店铺成功！");
			}
		else {
				Reporter.log("登录店铺失败！" + response);
			}
		Reporter.log(getCurrent.Time() + "\n");
		return httpclient;
	}
	
	/**
	 * 登录账号
	 */
	@Test
	public static CloseableHttpClient Login(String adminAcct,String password){
		String url = LoadAPIInfo.url + LoadAPIInfo.userLoginAPI;
		
		Object[][] keyValueList = new Object[][] {
					{ "adminAcct", adminAcct },
					{ "password", password }
					};

		Reporter.log(getCurrent.Time());
		String response = ExecutePost.getPostMethodResponse(httpclient, url,keyValueList);
		JSONObject responsejson = JSONObject.fromObject(response);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (Assertion.verifyEqual(returnValue, 0)) {
			Reporter.log("登录账号成功！");
		}
	else {
			Reporter.log("登录账号失败！" + response);
		}
		Reporter.log(getCurrent.Time() + "\n");
		return httpclient;
	}
	
	/**
	 * 进入店铺
	 */
	@Test
	public static CloseableHttpClient EnterShop(String mobile,String password,String sellId){
		httpclient = Login(mobile,password);
		String url = LoadAPIInfo.url + LoadAPIInfo.userLoginAPI;

		Object[][] keyValueList = new Object[][] {
					{ "sellId", sellId }
					};

		Reporter.log(getCurrent.Time());
		String response = ExecutePost.getPostMethodResponse(httpclient, url,keyValueList);
		JSONObject responsejson = JSONObject.fromObject(response);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (Assertion.verifyEqual(returnValue, 0)) {
			Reporter.log("进入店铺成功！");
		}
	else {
			Reporter.log("进入店铺失败！" + response);
		}
		Reporter.log(getCurrent.Time() + "\n");
		return httpclient;
	}
	
	@Test
	public static void main() {
		EnterShop("13818881111", "123456", "234");
	}
}
