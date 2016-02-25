package cn.wemart.usermng;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadUserInfo;
import cn.wemart.util.getCurrent;

public class saveUserInfo {

	private static Log log = LogFactory.getLog(getShopInfo.class);
	
	/**
	 * 对应20504号接口（保存账户信息）
	 */
	@Test
	public static void saveUserInfoTest() {
		
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.saveUserAPI;
		
		String[] adminAcct = LoadUserInfo.adminAcct.split(",");
		Object[][] keyValueList = new Object[][] { 
					{ "mobileNo", "13818815872" },
					{ "nickName","abcd"},
					{ "qqAcct","781615150"},
					{ "email","sean@wemart.cn"}
					};
			
			System.out.println(getCurrent.Time()); 
			String responseEntiy = ExecutePut.getPutMethodResponse(httpclient,url,keyValueList);
			System.out.println(getCurrent.Time()); 
			JSONObject object = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(object.getString("returnValue"));
			if (returnValue == 0){
				System.out.println("信息保存成功！\n");
			}
			else{
				System.out.println("信息保存失败！"+responseEntiy+"\n");
			}
	}
	
	
	/**
	 * 对应20503/20201号接口（获取账户信息）
	 */
	@Test
	public static void getUserInfoTest(){
		
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String getUserInfoUrl =  LoadAPIInfo.url+LoadAPIInfo.saveUserAPI;
		
		Object[][] keyValueList = new Object[][] { 
				{ "adminAcct" , "13818815872"}
				};
		
		System.out.println(getCurrent.Time()); 
		String getUserInfo = ExecuteGet.getGetMethodResponse(httpclient,getUserInfoUrl, keyValueList);
		System.out.println(getUserInfo);
		System.out.println(getCurrent.Time()); 
		
		JSONObject getUserInfoObject = JSONObject.fromObject(getUserInfo);
		String getNickName = getUserInfoObject.getString("data");
		if(getNickName.contains("asd")){
			System.out.println("昵称匹配成功！\n");
		}
		else{
			System.out.println("昵称不匹配！"+getUserInfo+"\n");
		}
	}
	

}
