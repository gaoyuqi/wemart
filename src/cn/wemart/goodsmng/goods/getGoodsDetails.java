package cn.wemart.goodsmng.goods;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class getGoodsDetails {

	/**
	 * 对应30206号接口（获取商品详情）
	 */
	@Test
	public static void EditStockTest() {
		CloseableHttpClient httpclient = shopLogin.setHttpClient();

		String url = LoadAPIInfo.url + LoadAPIInfo.getGoodsDetailsAPI;
		
		Object[][] keyValueList = new Object[][] {
				{ "goodsId", "gd2036" } 
				};
		
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient, url,keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time() + "\n");
		
		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (0 == returnValue) {
			String data = responsejson.getString("data");
			System.out.println("获取商品详情成功！商品详情为："+data+"\n");
		}
		else{
			System.out.println("获取商品详情失败！"+responseEntiy+"\n");
		}

	}

}
