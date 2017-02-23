package cn.wemart.userManagment;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.TestNG.Assertion;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class IsLogin {
	
	
	/**
	 * 未登录情况判断是否登录
	 */
	@Test
	public void NoLogin(){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String loginFlag = determineLogin(httpClient);
		if(Assertion.verifyEqual(loginFlag, "1")){
			Reporter.log("该用户已登录！");
			System.out.println("该用户已登录！");
		}
		else{
			Reporter.log("该用户未登录！");
			System.out.println("该用户未登录！");
		}
		Reporter.log(getCurrent.Time());
	}
	
	
	/**
	 * 已登录的情况判断是否登录
	 */
	@Test
	public void Login(String mobile,String password, String type, String sllerId){
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.DirectEnterShop(mobile, password, type, sllerId);
		CloseableHttpClient httpClient = shopLogin.httpClient;
		String loginFlag = determineLogin(httpClient);
		if(Assertion.verifyEqual(loginFlag, "1")){
			Reporter.log("该用户已登录！");
		}
		else{
			Reporter.log("该用户未登录！");
		}
		Reporter.log(getCurrent.Time());

	}
	
	/**
	 * 是否登录方法
	 * @param httpClient
	 */
	public String determineLogin(CloseableHttpClient httpClient){
		String url = LoadAPIInfo.url + "/api/authmng/login";
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		
		Reporter.log(getCurrent.Time());
		String response = ExecuteGet.getGetMethodResponse(httpClient, url, keyValueList);
		JSONObject json = JSONObject.fromObject(response);
		String loginFlag = json.getJSONObject("data").getString("loginFlag");
		return loginFlag;
		
	}

}
