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

public class getGoodsPriceInfo {

	/**
	 * 对应30703号接口（查询商品SKU信息）
	 */
	@Test
	public static void getGoodsSKUInfoMobile() {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		List<Object> goodsIDlist = new ArrayList<Object>();
		goodsIDlist.add("gd1927");
		goodsIDlist.add("gd1960");
		
		JSONArray goodsIdObject = JSONArray.fromObject(goodsIDlist);
		
		String url = LoadAPIInfo.url+"/api/shopping/shop/goodslist/aggregate";
		Object[][] keyValueList = new Object[][]{
				{"sellerId","38"},
				{"goodsIdList",goodsIdObject}
				};
		
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			JSONArray data = JSONArray.fromObject(responseObject.getString("data"));
			for(Object o : data){
				System.out.println(o.toString());
			}
			System.out.println("获取商品SKU信息成功! \n");
		}
		else{
			System.out.println("获取商品SKU信息失败!"+responseEntiy+"\n");
		}

	}

}
