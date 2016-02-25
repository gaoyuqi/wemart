package cn.wemart.usermng;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import net.sf.json.JSONObject;
import cn.wemart.goodsmng.group.Group;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadUserInfo;
import cn.wemart.util.getCurrent;
/**
 * 
 * @author sean
 *
 */
public class shopLogin {
	
	private static Log log = LogFactory.getLog(shopLogin.class);

	/**
	 * 对应20508号接口（商铺登录）
	 */
	@Test
	public static void shopLoginTest() {
		String[] adminAcct = LoadUserInfo.adminAcct.split(",");
		String[] password = LoadUserInfo.password.split(",");
		String[] type = LoadUserInfo.type.split(",");
		String[] objectId = LoadUserInfo.objetcId.split(",");
		CloseableHttpClient httpclient = HttpClients.createDefault();

		for (int i = 0;i<adminAcct.length;i++) {

			Object[][] keyVlaueList = new Object[][] { 
					{ "adminAcct",  adminAcct[i]},
					{ "password", password[i] },
					{ "type", type[i] },
					{ "objectId", objectId[i] }
					};
			String url = LoadAPIInfo.url+LoadAPIInfo.userLoginAPI;
			System.out.println(getCurrent.Time()); 
			String responseEntiy = ExecutePost.getPostMethodResponse(httpclient,url, keyVlaueList);
			System.out.println("response="+responseEntiy);
			System.out.println(getCurrent.Time()); 
			
			JSONObject responsejson = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
			if (0 == returnValue) {
				System.out.println("登录店铺成功！\n");
			}
			else{
				System.out.println("登录店铺失败！"+responseEntiy+"\n");
			}
		}
	}
	
	
	
	public static CloseableHttpClient setHttpClient(){
		Object[][] loginkeyValueList = new Object[][] { 
				{ "adminAcct",  "13818815872"},
				{ "password", "123456" },
				{ "type", "3" },
				{ "objectId", "6" }
				};
		
		String loginurl = LoadAPIInfo.url+LoadAPIInfo.userLoginAPI;
		CloseableHttpClient httpclient = cn.wemart.httppost.getHttpClient.Login(loginurl, loginkeyValueList);
		return httpclient;
	}

}
