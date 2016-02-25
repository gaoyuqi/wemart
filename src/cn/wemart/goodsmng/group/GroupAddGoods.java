package cn.wemart.goodsmng.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;









import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.wemart.httppost.ExecuteDelete;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class GroupAddGoods {

	/**
	 * 对应30405号接口（分组中加入商品）
	 */
	@Test
	public static void groupAddGoodsTest() {
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.groupAddGoodsAPI;
		
		//组装json
		Map<Object ,Object> goodsIDlist1map = new HashMap<Object,Object>();
		goodsIDlist1map.put("goodsId", "gd382");
		goodsIDlist1map.put("ingrpSortno", 1);
		
		Map<Object ,Object> goodsIDlist2map = new HashMap<Object,Object>();
		goodsIDlist2map.put("goodsId", "gd383");
		goodsIDlist2map.put("ingrpSortno", 2);
		
		Map<Object ,Object> goodsIDlist3map = new HashMap<Object,Object>();
		goodsIDlist3map.put("goodsId", "gd384");
		goodsIDlist3map.put("ingrpSortno", 3);
		
		Map<Object ,Object> goodsIDlist4map = new HashMap<Object,Object>();
		goodsIDlist4map.put("goodsId", "gd385");
		goodsIDlist4map.put("ingrpSortno", 4);
		
		Map<Object ,Object> goodsIDlist5map = new HashMap<Object,Object>();
		goodsIDlist5map.put("goodsId", "gd386");
		goodsIDlist5map.put("ingrpSortno", 5);
		
		Map<Object ,Object> goodsIDlist6map = new HashMap<Object,Object>();
		goodsIDlist6map.put("goodsId", "gd387");
		goodsIDlist6map.put("ingrpSortno", 6);
		
		
		List<Object> goodsIDlist = new ArrayList<Object>();
		goodsIDlist.add(goodsIDlist1map);
		goodsIDlist.add(goodsIDlist2map);
		goodsIDlist.add(goodsIDlist3map);
		goodsIDlist.add(goodsIDlist4map);
		goodsIDlist.add(goodsIDlist5map);
		goodsIDlist.add(goodsIDlist6map);
		
		JSONArray goodsIdObject = JSONArray.fromObject(goodsIDlist);
		
		Object[][] keyVlaueList = new Object[][]{
				{"groupNo","975"},
				{"goodsList",goodsIdObject}
				};
		
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecutePost.getPostMethodResponse(httpclient, url, keyVlaueList);
		System.out.println("response"+responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (0 == returnValue) {
			System.out.println("分组中加入商品成功！\n");
		}
		else{
			System.out.println("分组中加入商品失败！"+responseEntiy+"\n");
		}

	}
	
	
	/**
	 * 对应30406号接口（从分组中删除商品）
	 */
//	@Test
	public static void groupDeleteGoodsTest() {
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.groupAddGoodsAPI;
		
		//组装json
		Map<Object ,Object> goodsIDlist1map = new HashMap<Object,Object>();
		goodsIDlist1map.put("goodsId", "gd198x");
		
		Map<Object ,Object> goodsIDlist2map = new HashMap<Object,Object>();
		goodsIDlist2map.put("goodsId", "gd248");
		
		List<Object> goodsIDlist = new ArrayList<Object>();
		goodsIDlist.add(goodsIDlist1map);
		goodsIDlist.add(goodsIDlist2map);
		
		JSONArray goodsIdObject = JSONArray.fromObject(goodsIDlist);
		
		Object[][] keyVlaueList = new Object[][]{
				{"groupNo","940"},
				{"goodsList",goodsIdObject}
				};
		
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteDelete.getDeleteMethodResponse(httpclient, url, keyVlaueList);
		System.out.println("response"+responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (0 == returnValue) {
			System.out.println("从分组中删除商品成功！\n");
		}
		else{
			System.out.println("从分组中删除商品失败！"+responseEntiy+"\n");
		}

	}

}
