package cn.wemart.mobile;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class getGoodsSKU {

	
	/**
	 * 对应30705号接口（获取商品SKU）
	 */
	@Test()
	public static void getGoodsSKUMobile() {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		String url = LoadAPIInfo.url+"/api/shopping/shop/goodssku";
		Object[][] keyValueList = new Object[][]{
				{"goodsId","gd2036"},
				{"sellerId","6"}
				};
		
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("获取商品SKU成功! \n");
		}
		else{
			System.out.println("获取商品SKU失败!"+responseEntiy+"\n");
		}
	}

}
