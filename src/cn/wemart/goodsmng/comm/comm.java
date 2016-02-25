package cn.wemart.goodsmng.comm;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class comm {

	private static Log log = LogFactory.getLog(comm.class);

	/**
	 * 对应30506号接口（创建分销关系）
	 */
//	@Test
	public static void createMmktTest() {

		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url + LoadAPIInfo.commAPI;

		Object[][] keyValueList = new Object[][] {
				{ "goodsId", "gd1960" },
				{ "invoiceFlag", 1 } 
				};

		System.out.println(getCurrent.Time());
		String responseEntiy = ExecutePost.getPostMethodResponse(httpclient,url, keyValueList);
		System.out.println("response=" + responseEntiy);
		System.out.println(getCurrent.Time());

		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (0 == returnValue) {
			System.out.println("创建分销关系成功！\n");
		}
		else{
			System.out.println("创建分销关系失败！"+responseEntiy+"\n");
		}
	}
	
	/**
	 * 对应30507号接口（修改分销商品）
	 */
	@Test
	public static void EditMmktTest() {

		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url + LoadAPIInfo.commAPI;

		Object[][] keyValueList = new Object[][] {
				{"goodsId", "gd2071" },
				{"redefineFlag",0},
				{"goodsName","new goods name"},
				{"goodsBrief","new goods Brief"},
				{"invoiceFlag",0},
				{"onshelf",1},
				{"mainpicNo","2"},
				{"picNos","2,3,4,5,6"},
				{"detail","test new detail"}
				};

		System.out.println(getCurrent.Time());
		String responseEntiy = ExecutePut.getPutMethodResponse(httpclient,url, keyValueList);
		System.out.println("response=" + responseEntiy);
		System.out.println(getCurrent.Time());

		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (0 == returnValue) {
			System.out.println("修改分销商品成功！\n");
		}
		else{
			System.out.println("修改分销商品失败！"+responseEntiy+"\n");
		}
	}
}
