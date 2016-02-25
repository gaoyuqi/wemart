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

public class GoodsAddToGroup {

	/**
	 * 对应30208号接口（将商品加入到分组中）
	 */
	@Test
	public static void GoodsAddGroup() {
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.goodsAddToGroupAPI;
		
		//组装json
		Map<Object ,Object> groupNoList1map = new HashMap<Object,Object>();
		groupNoList1map.put("groupNo", "850");
		groupNoList1map.put("ingrpSortno", 1);
		
		Map<Object ,Object> groupNoList2map = new HashMap<Object,Object>();
		groupNoList2map.put("groupNo", "852");
		groupNoList2map.put("ingrpSortno", 1);
		
		List<Object> goodsIDlist = new ArrayList<Object>();
		goodsIDlist.add("gd2036");
		goodsIDlist.add("gd2037");
		
		List<Object> groupNoList = new ArrayList<Object>();
		groupNoList.add(groupNoList1map);
//		groupNoList.add(groupNoList2map);
		
		JSONArray goodsIdObject = JSONArray.fromObject(goodsIDlist);
		JSONArray groupNoListObject = JSONArray.fromObject(groupNoList);
		
		Object[][] keyValueList = new Object[][]{
				{"goodsIdList",goodsIdObject},
				{"groupList",groupNoListObject}
				};
		
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecutePost.getPostMethodResponse(httpclient, url, keyValueList);
		System.out.println("response"+responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (0 == returnValue) {
			System.out.println("将商品加入分组中成功！\n");
		}
		else{
			System.out.println("将商品加入分组中失败！"+responseEntiy+"\n");
		}
	}

	
	/**
	 * 对应30209号接口（商品移除分组）
	 */
//	@Test
	public static void GoodsDeleteGroup() {
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.goodsAddToGroupAPI;
		
		//组装json
		Map<Object ,Object> goodsIDlist1map = new HashMap<Object,Object>();
		goodsIDlist1map.put("groupNo", "811");
		
		Map<Object ,Object> goodsIDlist2map = new HashMap<Object,Object>();
		goodsIDlist2map.put("groupNo", "815");
		
		List<Object> goodsIDlist = new ArrayList<Object>();
		goodsIDlist.add(goodsIDlist1map);
		goodsIDlist.add(goodsIDlist2map);
		
		JSONArray goodsIdObject = JSONArray.fromObject(goodsIDlist);
		
		Object[][] keyValueList = new Object[][]{
				{"goodsId","gd1987"},
				{"groupList",goodsIdObject}
				};
		
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteDelete.getDeleteMethodResponse(httpclient, url, keyValueList);
		System.out.println("response"+responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (0 == returnValue) {
			System.out.println("商品移除分组成功！\n");
		}
		else{
			System.out.println("商品移除分组失败！"+responseEntiy+"\n");
		}
	}
}
