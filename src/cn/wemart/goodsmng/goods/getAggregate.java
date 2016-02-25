package cn.wemart.goodsmng.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class getAggregate {

	public static void main(String[] args) {
		CloseableHttpClient httpclient = shopLogin.setHttpClient();

		// 组装json
		List<Object> goodsIDlist = new ArrayList<Object>();
		goodsIDlist.add("gd2036");
//		goodsIDlist.add("gd1988");

		JSONArray goodsIdArray = JSONArray.fromObject(goodsIDlist);

		String url = LoadAPIInfo.url + "/api/goodsmng/goods/aggregate";
		Object[][] keyValueList = new Object[][] { 
				{ "goodsIdList", goodsIdArray }
				};
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient,url, keyValueList);
		System.out.println("response=" + responseEntiy);

		System.out.println(getCurrent.Time());

		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		String xxx = responseObject.getString("returnValue");

	}

}
