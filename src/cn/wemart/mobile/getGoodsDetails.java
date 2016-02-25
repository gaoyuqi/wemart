package cn.wemart.mobile;

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
import cn.wemart.util.getCurrent;

public class getGoodsDetails {

	
	/**
	 * 对应30704号接口（查询商品详情页）
	 */
	@Test
	public static void getGoodsDetailsMobile() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		String url = LoadAPIInfo.url+"/api/shopping/shop/goodsdetail";
		Object[][] keyValueList = new Object[][]{
				{"goodsId","gd2050"},
				{"sellerId","6"}};
		
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("获取商品详情页成功! \n");
		}
		else{
			System.out.println("获取商品详情页失败!"+responseEntiy+"\n");
		}

	}
}
