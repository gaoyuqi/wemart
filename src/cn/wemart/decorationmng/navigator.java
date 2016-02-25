package cn.wemart.decorationmng;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class navigator {

	private static Log log = LogFactory.getLog(shelf.class);

	/**
	 * 对应607号接口（创建导航）
	 */
	// @Test
	public static void addNavigatorTest() {

		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url + LoadAPIInfo.shopNavigatorAPI;

		// 组装json
		JSONArray Empty = JSONArray.fromObject("[]");

		Map<Object, Object> shelfExt1map = new HashMap<Object, Object>();
		shelfExt1map.put("navStyle", 1);
		shelfExt1map.put("navBgColor", "#000000");
		shelfExt1map.put("navHome", true);
		shelfExt1map.put("navGroup", true);
		shelfExt1map.put("navCustom", 1);
		shelfExt1map.put("navCustomList", Empty);
		shelfExt1map.put("data", Empty);
		shelfExt1map.put("shelfName", "三级导航");
		shelfExt1map.put("shelfNo", 215);
		shelfExt1map.put("shopName", "sean");
		shelfExt1map.put("template", "2");
		JSONObject shelfExt1mapObject = JSONObject.fromObject(shelfExt1map);

		Map<Object, Object> navigatorContentmap = new HashMap<Object, Object>();
		navigatorContentmap.put("shelfExt1", shelfExt1mapObject);
		JSONObject navigatorContentdata = JSONObject.fromObject(navigatorContentmap);

		Object[][] keyValueList = new Object[][] {
				{ "navigatorContent",navigatorContentdata } 
				};// 添加导航

		System.out.println(getCurrent.Time());
		String responseEntiy = ExecutePost.getPostMethodResponse(httpclient,url, keyValueList);
		System.out.println("response=" + responseEntiy);
		System.out.println(getCurrent.Time());

		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if (0 == returnValue) {
			System.out.println("添加导航成功! \n");
		} else {
			System.out.println("添加导航失败!" + responseEntiy + "\n");
		}
	}

	/**
	 * 对应609号接口（查询导航）
	 */
	@Test
	public static void getNavigatorTest() {

		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url + LoadAPIInfo.shopNavigatorAPI;

		Object[][] keyValueList = new Object[][] { {"sellerId",6} };// 查询导航

		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient, url,keyValueList);
		System.out.println("response=" + responseEntiy);
		System.out.println(getCurrent.Time());

		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if (0 == returnValue) {
			System.out.println("查询导航成功! \n");
		} else {
			System.out.println("查询导航失败!" + responseEntiy + "\n");
		}
		
	}

}
