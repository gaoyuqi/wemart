package cn.wemart.userManagment;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

import com.TestNG.Assertion;

@Listeners({com.TestNG.AssertionListener.class})
public class LogOut {

	/**
	 * 退出用户
	 */
	@Test
	public static String logOut(String mobile,String password, String type, String sllerId){
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.DirectEnterShop(mobile, password, type, sllerId);
		CloseableHttpClient httpClient = shopLogin.httpClient;
		
		String url = LoadAPIInfo.url+"/api/authmng/quit";
		Object[][] keyValueList = new Object[][]{};

		Reporter.log(getCurrent.Time());
		String response = ExecutePost.getPostMethodResponse(httpClient, url, keyValueList);
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		return returnValue;
		
	}
	
	@Test
	public void test(String mobile,String password, String type, String sllerId){
		String returnValue = logOut("13818881111","123456","3","234");
		if(Assertion.verifyEqual(returnValue, "0")){
			Reporter.log("退出账号成功");
		}
		else{
			Reporter.log("退出账号失败");
		}
		Reporter.log(getCurrent.Time());
	}
}
