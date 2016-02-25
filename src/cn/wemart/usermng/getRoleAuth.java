package cn.wemart.usermng;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.goodsmng.group.Group;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadUserInfo;
import cn.wemart.util.getCurrent;

public class getRoleAuth {
	
	private static Log log = LogFactory.getLog(getRoleAuth.class);
	
	
	/**
	 * 对应20202号接口（获取角色对应权限树）
	 */
	@Test
	public static void getRoleAuthTest(){
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.getRoleAPI;
		
		Object[][] keyValueList = new Object[][]{};
		
		System.out.println(getCurrent.Time()); 
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient,url, keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time()); 
		
		JSONObject responseobject = JSONObject.fromObject(responseEntiy);
			
		Integer returnValue = Integer.parseInt(responseobject.getString("returnValue"));
		if( 0 == returnValue){
			System.out.println("获取角色权限树成功！\n");
		}
		else{
			System.out.println("获取角色权限树失败！"+responseEntiy+"\n");
		}
	}

}
