package cn.wemart.usermng;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.goodsmng.group.Group;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadGoodsmngGroupInfo;
import cn.wemart.util.getCurrent;

public class createScenarioAPI {
	
	private static Log log = LogFactory.getLog(Group.class);
	
	
	/**
	 * 对应20101号接口（创建场景）
	 */
//	@Test
	public static void createScenarioAPITest(){
		
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.createScenarioAPI;
		
		//组装json
		Map<Object ,Object> parasmap = new HashMap<Object,Object>();
		parasmap.put("para1", "shopName1");
		parasmap.put("para2", "shopId");
		JSONObject paraObject = JSONObject.fromObject(parasmap);
		
		String[] scenType= LoadGoodsmngGroupInfo.groupName.split(",");
		for (String x:scenType){
			Object[][] keyValueList = new Object[][]{
						{"scenType",x},
						{"name","abcdef"},
						{"params",paraObject},
						};
			
			System.out.println(getCurrent.Time()); 
			String responseEntiy = ExecutePost.getPostMethodResponse(httpclient,url, keyValueList);
			System.out.println("response="+responseEntiy.trim());
			System.out.println(getCurrent.Time()); 
		
			JSONObject responsejson = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
			if (0 == returnValue) {
				System.out.println("创建成功！\n");
			}
			else{
				System.out.println("创建失败！"+responseEntiy+"\n");
			}
		}
	}
	
	
	/**
	 * 对应20102号接口（修改场景）
	 */
	@Test
	public static void editScenarioAPITest(){
		
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.createScenarioAPI;
		
		//组装json
		Map<Object ,Object> parasmap = new HashMap<Object,Object>();
		parasmap.put("para1", "shopName1");
		parasmap.put("para2", "shopId");
		JSONObject paraObject = JSONObject.fromObject(parasmap);
		
		String[] scenType= LoadGoodsmngGroupInfo.groupName.split(",");
		for (String x:scenType){
			
			Object[][] keyValueList = new Object[][]{
						{"scenType","2"},
						{"name",x},
						{"params",paraObject},
						{"scenId","12"}
						};
			
			System.out.println(getCurrent.Time()); 
			String responseEntiy = ExecutePut.getPutMethodResponse(httpclient,url, keyValueList);
			System.out.println("response="+responseEntiy.trim());
			System.out.println(getCurrent.Time()); 
		
			JSONObject responsejson = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
			if (0 == returnValue) {
				System.out.println("修改渠道成功！\n");
			}
			else{
				System.out.println("修改渠道失败！"+responseEntiy+"\n");
			}
			
		}
	}

}
