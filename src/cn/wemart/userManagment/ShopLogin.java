package cn.wemart.userManagment;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.TestNG.Assertion;
import net.sf.json.JSONObject;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

@Listeners({com.TestNG.AssertionListener.class})
public class ShopLogin {

	public String response;
	public CloseableHttpClient httpClient = HttpClients.createDefault();

	/**
	 * 直接登录店铺
	 */
	@Test
	public CloseableHttpClient DirectEnterShop(String adminAcct, String password,String type, String objectId) {
		String url = LoadAPIInfo.url + LoadAPIInfo.userLoginAPI;

		Object[][] keyValueList = new Object[][] {
					{ "adminAcct", adminAcct },
					{ "password", password },
					{ "type", type },
					{ "objectId", objectId }
					};

		Reporter.log(getCurrent.Time());
		response = ExecutePost.getPostMethodResponse(httpClient, url,keyValueList);
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("登录店铺成功！"+ response);
			}
		else {
				Reporter.log("登录店铺失败！" + response);
			}
		Reporter.log(getCurrent.Time() + "\n");
		return httpClient;
	}
	
	/**
	 * 登录账号
	 */
	@Test
	public CloseableHttpClient Login(String adminAcct,String password){
		String url = LoadAPIInfo.url + LoadAPIInfo.userLoginAPI;
		
		Object[][] keyValueList = new Object[][] {
					{ "adminAcct", adminAcct },
					{ "password", password }
					};

		Reporter.log(getCurrent.Time());
		response = ExecutePost.getPostMethodResponse(httpClient, url,keyValueList);
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("登录账号成功！");
		}
	else {
			Reporter.log("登录账号失败！" + response);
		}
		Reporter.log(getCurrent.Time() + "\n");
		return httpClient;
	}
	
	/**
	 * 进入店铺
	 */
	@Test
	public CloseableHttpClient EnterShop(String mobile,String password,String sellId){
		httpClient = Login(mobile,password);
		String url = LoadAPIInfo.url + LoadAPIInfo.userLoginAPI;

		Object[][] keyValueList = new Object[][] {
					{ "sellId", sellId }
					};

		Reporter.log(getCurrent.Time());
		response = ExecutePost.getPostMethodResponse(httpClient, url,keyValueList);
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("进入店铺成功！");
		}
		else {
			Reporter.log("进入店铺失败！" + response);
		}
		Reporter.log(getCurrent.Time() + "\n");
		return httpClient;
	}
	
	/**
	 * 进入渠道
	 */
	@Test
	public CloseableHttpClient EnterChannel(String mobile,String password,String channelID){
		httpClient = Login(mobile,password);
		String url = LoadAPIInfo.url + LoadAPIInfo.userLoginAPI;

		Object[][] keyValueList = new Object[][] {
					{ "chanId", channelID }
					};

		Reporter.log(getCurrent.Time());
		response = ExecutePost.getPostMethodResponse(httpClient, url,keyValueList);
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("进入渠道成功！");
		}
		else {
			Reporter.log("进入渠道失败！" + response);
		}
		Reporter.log(getCurrent.Time() + "\n");
		return httpClient;
	}
	
	@Test
	public  void main() {
//		EnterShop("13818881111", "123456", "234");
		DirectEnterShop("13818881111", "654321", "3", "234");
	}
}
