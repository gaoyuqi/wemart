package cn.wemart.usermng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadGoodsmngGroupInfo;
import cn.wemart.util.getCurrent;

public class getSellerListDetailsInfo {
	
	/**
	 * 对应20515号接口（批量查询店铺详细信息）
	 */
	@Test
	public static void getSellerDetailsInfoTest(){
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String url = LoadAPIInfo.url+LoadAPIInfo.getSellerListAPI;
			
		//组装json
		List<Object> sellerIdList = new ArrayList<Object>();
		sellerIdList.add("1xx");
		sellerIdList.add("6xx");
					
		JSONArray sellerIdListObject = JSONArray.fromObject(sellerIdList);
				
		Object[][] keyValueList = new Object[][]{
				{"sellerIdList",sellerIdListObject}
			};
			
		System.out.println(getCurrent.Time()); 
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient,url, keyValueList);
		System.out.println("response="+responseEntiy.trim());
		System.out.println(getCurrent.Time()); 
		
		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (0 == returnValue) {
			JSONArray data = JSONArray.fromObject(responsejson.getString("data"));
			for(Object o:data){
				System.out.println(o.toString());
			}
			System.out.println("批量查询店铺详细信息成功！\n");
		}
		else{
			System.out.println("批量查询店铺详细信息失败！"+responseEntiy+"\n");
		}
			
	}

}
