package cn.wemart.address;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.usermng.buyerLogin;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class getProvinceCityList {
	
	@Test
	public static void ProvinceList() {
		
		String scenId = "10";
		String buyerId = "123000";
		String scenType = "2";
		CloseableHttpClient httpclient = shopLogin.setHttpClient();//HttpClients.createDefault();
		
		Object[][] keyValueList = new Object[][]{
//				{"cityNo","197"},
//				{"provinceName","广东省"}
//				{"cityNo","197"}
				};
		
		String url = LoadAPIInfo.url + LoadAPIInfo.getProvinceCityAPI;
		
		System.out.println(getCurrent.Time());
		String response = ExecuteGet.getGetMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+response);
		System.out.println(getCurrent.Time());
		
		JSONObject responseobejct = JSONObject.fromObject(response);
		Integer returnValue = Integer.valueOf(responseobejct.getString("returnValue"));
		int count = 0;
		List<String> provinceList = new ArrayList<String>();
		if(0 == returnValue){
			JSONArray provincelist = JSONArray.fromObject(responseobejct.getString("data"));
			for(Object province :provincelist){
				JSONObject provinceName = JSONObject.fromObject(province.toString());
				provinceList.add(provinceName.getString("name"));
				System.out.println(province.toString());
				count += 1;
			}
			System.out.println("获取城市列表成功！"+count+"\n");
		}
		else{
			System.out.println("获取城市列表失败！"+response+"\n");
		}
		
	}

}
