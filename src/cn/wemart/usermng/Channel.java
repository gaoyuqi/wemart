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
import cn.wemart.util.LoadGoodsmngGroupInfo;
import cn.wemart.util.LoadUserInfo;
import cn.wemart.util.getCurrent;

public class Channel {

	private static String url;
	private static String responseEntiy;
	private static Object[][] keyValueList;
	private static Log log = LogFactory.getLog(Group.class);
	
	
	@Test
	public static void createChannelAPITest(){
		String[] name = LoadGoodsmngGroupInfo.groupName.split(",");
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		
		for(int i =50;i<110;i++){
		
//			keyValueList = new String[][]{{"logoUrl",x},{"name",x},{"brief",x},{"token",LoadUserInfo.token}};//创建渠道
//			keyValueList = new String[][]{{"chanId","99"},{"name","shishi"},{"token",LoadUserInfo.token}}; //修改渠道
			keyValueList = new Object[][]{{"chanId",i}}; //获取渠道列表
			url = LoadAPIInfo.url+LoadAPIInfo.createChannelAPI;
			System.out.println(getCurrent.Time()); 
			responseEntiy = ExecuteGet.getGetMethodResponse(httpclient,url, keyValueList);
//			responseEntiy = ExecutePut.getPutMethodResponse(url, keyValueList);
//			responseEntiy = ExecuteDelete.getDeleteMethodResponse(url, keyValueList);
//			responseEntiy = ExecutePost.getPostMethodResponse(url, keyValueList);
			System.out.println("response="+responseEntiy.trim());
			System.out.println(getCurrent.Time()); 
		
			JSONObject jsonobject = JSONObject.fromObject(responseEntiy);
			
			Integer loginStatus = Integer.parseInt(jsonobject.get("returnValue").toString());
			if( loginStatus != 0){
				log.info(responseEntiy);
			}
		}
	}

}
