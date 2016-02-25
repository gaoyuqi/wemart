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
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class getGoodsList {

	/**
	 * 对应30702号接口（查询商品列表）
	 */
	@Test
	public static void getGoodsListMobile() {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		List<Object> goodsIDlist = new ArrayList<Object>();
		goodsIDlist.add("gd2036");
//		goodsIDlist.add("gd2");
//		goodsIDlist.add("gd3");
		
		JSONArray goodsIdObject = JSONArray.fromObject(goodsIDlist);
		
		String url = LoadAPIInfo.url+"/api/shopping/shop/goodslist";
		Object[][] keyValueList = new Object[][]{
				{"sellerId","6"},
				{"goodsIdList",goodsIdObject},
//				{"groupNo","849"},
//				{"pageIndex","849"},
//				{"pageSize","849"}
				};
		
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONArray responseGoodList = JSONArray.fromObject(JSONObject.fromObject(responseEntiy).getString("data"));
		int i=0;
		for(Object x:responseGoodList){
			System.out.println(x.toString());
			i += 1;
		}
		System.out.println("商品数量是："+i);

	}

}
