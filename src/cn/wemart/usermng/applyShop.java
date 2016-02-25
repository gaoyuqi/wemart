package cn.wemart.usermng;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import net.sf.json.JSONObject;
import cn.wemart.httppost.ExecuteDelete;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadGoodsmngGroupInfo;
import cn.wemart.util.LoadUserInfo;
import cn.wemart.util.getCurrent;

public class applyShop {
	private static Log log = LogFactory.getLog(getShopInfo.class);
	
	/**
	 * 对应20506号接口(申请店铺）
	 */
	@Test
	public static void applyShopTest() {
		
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.applyShopAPI;
		
		Object[][] keyValueList = new String[][] {
					{ "chanId","98" },
					{ "sellName","sean apply shop" },
					{ "logoUrl","http://www.xxx.jpg"}, // Logo图片地址 （选填）
					{ "applyRemark" , "please check!"}     // 申请备注 （选填）
					}; 
		
		System.out.println(getCurrent.Time()); 
		String responseEntiy = ExecutePost.getPostMethodResponse(httpclient,url,keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time()); 
		
		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		
		if (0 == returnValue){
			System.out.println("申请店铺成功！\n");
		}
		else{
			System.out.println("申请店铺失败！"+responseEntiy+"\n");
		}
	}
	
	/**
	 * 对应20301/20507号接口（获取我的店铺列表）
	 */
//	@Test
	public static void getMyShopList() {
		
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.applyShopAPI;
		String[] shopType = {"seller","apply"};
		for(String x : shopType){
			Object[][] keyValueList = new Object[][] { 
					{ "type", x }
					};
			
			System.out.println(getCurrent.Time()); 
			String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient,url,keyValueList);
			System.out.println("response="+responseEntiy);
			System.out.println(getCurrent.Time()); 
			
			JSONObject responsejson = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
			
			if (0 == returnValue){
				System.out.println("获取我的店铺成功！\n");
			}
			else{
				System.out.println("获取我的店铺失败！"+responseEntiy+"\n");
			}
		}
	}
}
