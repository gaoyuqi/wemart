package cn.wemart.usermng;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import net.sf.json.JSONObject;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadUserInfo;
import cn.wemart.util.getCurrent;

public class UserRegister {

	private static Log log = LogFactory.getLog(getVertifyCode.class);

	/**
	 * 对应20502号接口（商铺注册）
	 */
	@Test
	public static void UserRegisterTest() {
		String[] adminAcct = LoadUserInfo.adminAcct.split(",");
		String[] password = LoadUserInfo.password.split(",");
		String[] vertifyCode = LoadUserInfo.vertifycode.split(",");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String url = LoadAPIInfo.url + LoadAPIInfo.shopRegisterAPI;
		// for(int i =0;i<adminAcct.length;i++){
		Object[][] keyValueList = {
				{ "adminAcct", "13512312312" },
				{ "password", "123456" },
				{ "confirmPassword", "123456" },
				{ "vertifyCode", "979706" } 
				};
		
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecutePost.getPostMethodResponse(httpclient,url, keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (0 == returnValue) {
			System.out.println("注册成功！\n");
		}
		else{
			System.out.println("注册失败！"+responseEntiy+"\n");
		}
//	}
	}

	/**
	 * 对应20509号接口（检测手机号是否注册）
	 */
//	@Test
	public static void CheckUserRegisterTest() {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String url = LoadAPIInfo.url + LoadAPIInfo.checkPhoneNoRegisterAPI;
		String[] adminAcct = LoadUserInfo.adminAcct.split(",");
		for (int i = 0; i < adminAcct.length; i++) {
			
			Object[][] keyValueList = { 
					{ "adminAcct", adminAcct[i] } 
					};

			System.out.println(getCurrent.Time()); 
			String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient,url, keyValueList);
			System.out.println("response="+responseEntiy);
			System.out.println(getCurrent.Time()); 
			
			JSONObject responsejson = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
			if (0 == returnValue) {
				System.out.println("该号码未注册！\n");
			}
			else{
				System.out.println("该号码已注册！"+responseEntiy+"\n");
			}
		}

	}

}
