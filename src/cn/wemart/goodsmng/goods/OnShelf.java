package cn.wemart.goodsmng.goods;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class OnShelf {

	/**
	 * 对应30207号接口（商品上架下架）
	 */
	@Test
	public static void OnShelfTest() {
		CloseableHttpClient httpclient = shopLogin.setHttpClient();

		// 组装json
		List<Object> goodsIDlist = new ArrayList<Object>();
		goodsIDlist.add("gd2036");
//		goodsIDlist.add("gd1988");

		JSONArray goodsIdArray = JSONArray.fromObject(goodsIDlist);

		String url = LoadAPIInfo.url + LoadAPIInfo.onShelfAPI;
		Object[][] keyValueList = new Object[][] { 
				{"goodsIdList", goodsIdArray},
				{"onshelf",1}
				};
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecutePut.getPutMethodResponse(httpclient,url, keyValueList);
		System.out.println("response=" + responseEntiy);
		System.out.println(getCurrent.Time());

		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		
		if(0 == returnValue){
			System.out.println("商品上架成功! \n");
		}
		else{
			System.out.println("商品上架失败!"+responseEntiy+"\n");
		}

	}

}
