package cn.wemart.usermng;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.goodsmng.group.Group;
import cn.wemart.httppost.ExecuteDelete;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadUserInfo;
import cn.wemart.util.getCurrent;

public class getMenu {

	private static Log log = LogFactory.getLog(getMenu.class);
	
	/**
	 * 对应20204号接口（获取菜单列表）
	 */
	@Test
	public static void getMenuTest(){
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.getMenuAPI;
		
		Object[][] keyValueList = new Object[][]{};
		
		System.out.println(getCurrent.Time()); 
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient,url, keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time()); 
		
		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if( 0 == returnValue){
			System.out.println("获取菜单列表成功！\n");
		}
		else{
			System.out.println("获取菜单列表失败！"+responseEntiy+"\n");
		}
	}
}
